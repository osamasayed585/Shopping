package com.hrhera.login.utils

@Suppress("ClassName")
sealed class LoginStates() {

    class EMAIL_ERROR(var error: String?):LoginStates()
    class PASSWORD_ERROR(var error: String?):LoginStates()

    class SUCCESS():LoginStates()
    class LOADING():LoginStates()
    class SOME_ERROR(var error:String):LoginStates()
}