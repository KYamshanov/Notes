package ru.undframe.notes.services

interface IdentifierDeviceService {
    fun getDeviceId(body: (String) -> Unit)
}