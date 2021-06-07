package ru.undframe.notes.presentor

import android.util.Base64
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.undframe.notes.contracts.AuthorizationContract
import ru.undframe.notes.encryption.SimpleCipher
import ru.undframe.notes.services.AuthService
import ru.undframe.notes.services.IdentifierDeviceService
import ru.undframe.notes.services.UserService
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

class AuthorizationPresenter(
    private val view:AuthorizationContract.View,
    private val identifierDeviceService: IdentifierDeviceService,
    private val authService: AuthService,
    private val userService: UserService
) : AuthorizationContract.Presenter {

    override fun authorizationUser(login: String, password: String) {
        identifierDeviceService.getDeviceIdCallable { device ->

            val s = encryptPassword(password)

            authService.authUser(login, s, device, "NEEDLE")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    if(user!=null && user.isAuthorization()){
                        userService.updateUser(user)
                        view.closeActivity()
                    }else{
                        view.showError()
                    }
                }, {
                    view.showError()
                    Log.e("Authorization", "login", it)
                })
        }
    }

    private fun encryptPassword(password: String): String {
        val aesKey = SecretKeySpec(
            Base64.decode(
                SimpleCipher.PASSWORD_CIPHER_KEY.toByteArray(),
                Base64.DEFAULT
            ), "AES"
        )
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, aesKey)
        val encrypted = cipher.doFinal(password.toByteArray())
        return DatatypeConverter.printBase64Binary(encrypted)
    }

}