package com.example.shopping.util

@Suppress("ClassName")
sealed class AccountStates() {

    class EMAIL_ERROR(var error: String?):AccountStates()
    class PASSWORD_ERROR(var error: String?):AccountStates()

    class SUCCESS():AccountStates()
    class LOADING():AccountStates()
    class SOME_ERROR(var error:String):AccountStates()
}