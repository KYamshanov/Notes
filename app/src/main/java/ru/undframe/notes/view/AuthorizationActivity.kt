package ru.undframe.notes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.undframe.notes.App
import ru.undframe.notes.R
import ru.undframe.notes.contracts.AuthorizationContract
import ru.undframe.notes.data.User
import ru.undframe.notes.encryption.SimpleCipher
import ru.undframe.notes.services.AuthService
import ru.undframe.notes.services.IdentifierDeviceService
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import javax.xml.bind.DatatypeConverter

class AuthorizationActivity : AppCompatActivity(), AuthorizationContract.View {


    @Inject
    lateinit var presenter: AuthorizationContract.Presenter

    @Inject
    lateinit var authService: AuthService

    @Inject
    lateinit var identifierDeviceService: IdentifierDeviceService

    private lateinit var loginTextView: TextView
    private lateinit var passwordTextView: TextView
    private lateinit var savePasswordCallback: CheckBox
    private lateinit var error: TextView
    private lateinit var authButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        authButton = findViewById(R.id.authButton)
        loginTextView = findViewById(R.id.login)
        passwordTextView = findViewById(R.id.password)
        savePasswordCallback = findViewById(R.id.auth_savepassword)
        error = findViewById(R.id.auth_error_info)

        authButton.setOnClickListener {
            identifierDeviceService.getDeviceId { device ->
                println("DeviceId: $device")

                val password = getPassword()

                val aesKey = SecretKeySpec(
                    Base64.decode(
                        SimpleCipher.PASSWORD_CIPHER_KEY.toByteArray(),
                        Base64.DEFAULT
                    ), "AES"
                )
                val cipher = Cipher.getInstance("AES")
                cipher.init(Cipher.ENCRYPT_MODE, aesKey)
                val encrypted = cipher.doFinal(password.toByteArray())
                val s = DatatypeConverter.printBase64Binary(encrypted)



                authService.authUser(getLogin(), s, device, "NEEDLE")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        println(it)
                    }, {
                        println(it)
                    })
            }
        }

    }


    fun getLogin(): String {
        return loginTextView.text.toString()
    }


    fun getPassword(): String {
        return passwordTextView.text.toString()
    }


}