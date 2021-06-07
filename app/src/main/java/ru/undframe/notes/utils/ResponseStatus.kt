package ru.undframe.notes.utils

object ResponseStatus {
    const val NOT_AUTHORIZED = -1
    const val SUCCESSFUL_AUTHORIZATION = 1
    const val ERROR = 0
    const val INVALID_PASSWORD = 2
    const val INVALID_TOKEN = 3
    const val EXPIRED_TOKEN = 4
    const val INVALID_INPUT_DATA = 5
    const val INVALID_INPUT_DATA_JSON = 6
    const val SERVICE_NOT_SUPPORT = 7
    const val LOGOUT_SUCCESSFUL = 8
    const val TOKEN_IS_ALIVE = 9
    const val TOKEN_NOT_ALIVE = 10
}