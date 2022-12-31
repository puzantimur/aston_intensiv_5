package com.example.astonintensiv5.model

data class Contact(
    var name: String,
    var surname: String,
    var phoneNumber: String
) {

    fun changeContact(name: String?, surname: String?, phoneNumber: String?) {
        this.name = name ?: this.name
        this.surname = surname ?: this.surname
        this.phoneNumber = phoneNumber ?: this.phoneNumber
    }

}