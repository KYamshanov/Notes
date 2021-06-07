package ru.undframe.notes.services

interface IdentifierDeviceService {
    fun getDeviceIdCallable(body: (String) -> Unit)
    fun getDeviceId(): String?
    fun init(body: (String) -> Unit)
    fun init()
}