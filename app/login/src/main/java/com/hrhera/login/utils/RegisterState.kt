package com.hrhera.login.utils

@Suppress("ClassName")
sealed class RegisterState {

    class EMAIL_ERROR(var error: String?) : RegisterState()
    class PASSWORD_ERROR(var error: String?) : RegisterState()
    class PHONE_ERROR(var error: String?) : RegisterState()


    class SUCCESS() : RegisterState()
    class LOADING() : RegisterState()
    class SOME_ERROR(var error: String) : RegisterState()
}