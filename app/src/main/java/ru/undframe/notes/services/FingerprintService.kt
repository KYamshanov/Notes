package ru.undframe.notes.services

import android.content.Context
import android.util.Base64
import com.fingerprintjs.android.fingerprint.Configuration
import com.fingerprintjs.android.fingerprint.FingerprinterFactory
import com.fingerprintjs.android.fingerprint.signal_providers.StabilityLevel
import org.json.JSONException
import org.json.JSONObject

class FingerprintService(private val context: Context) : IdentifierDeviceService {

    private var fingerprinter = FingerprinterFactory
        .getInstance(context, Configuration(3))

    private var deviceId: String? = null;

    override fun init() {
        init { }
    }

    override fun init(body: (String) -> Unit) {
        fingerprinter.getFingerprint(StabilityLevel.UNIQUE) { fingerprintResult ->
            val fingerprint = fingerprintResult.fingerprint

            val jsonObject = JSONObject()
            try {
                jsonObject.put("ip", "100.1.1.1")
                jsonObject.put("id", fingerprint)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val device =
                Base64.encodeToString(jsonObject.toString().toByteArray(), Base64.URL_SAFE)
                    .replace("\n", "")

            println(device)
            deviceId = device
            body.invoke(device)

        }
    }

    override fun getDeviceIdCallable(body: (String) -> Unit) {
        if (deviceId == null)
            init(body)
        else body.invoke(deviceId!!)
    }

    override fun getDeviceId(): String? {
        return deviceId
    }

}