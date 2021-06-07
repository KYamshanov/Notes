package ru.undframe.notes.services

import android.content.Context
import android.util.Base64
import com.fingerprintjs.android.fingerprint.Configuration
import com.fingerprintjs.android.fingerprint.FingerprinterFactory
import com.fingerprintjs.android.fingerprint.signal_providers.StabilityLevel
import org.json.JSONException
import org.json.JSONObject

class FingerprintService(private val context:Context):IdentifierDeviceService {

    private var fingerprinter = FingerprinterFactory
    .getInstance(context, Configuration(3))

    override fun getDeviceId(body:(String)->Unit){

        fingerprinter.getFingerprint(StabilityLevel.UNIQUE) { fingerprintResult ->
            val fingerprint = fingerprintResult.fingerprint

            val jsonObject = JSONObject()
            try {
                jsonObject.put("ip", "100.1.1.1")
                jsonObject.put("id", fingerprint)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val deviceId =  String(Base64.encode(jsonObject.toString().toByteArray(), Base64.URL_SAFE))

            body.invoke(deviceId)
        }

    }

}