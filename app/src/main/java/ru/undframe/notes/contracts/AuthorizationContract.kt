package ru.undframe.notes.contracts

class AuthorizationContract {

    interface View{
        fun closeActivity()
        fun showError()

    }

    interface Presenter{

        fun authorizationUser(login: String, password: String)
    }

}