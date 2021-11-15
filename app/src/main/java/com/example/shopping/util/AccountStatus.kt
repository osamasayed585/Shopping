package com.example.shopping.util

sealed class AccountStatus {

    class SOME_ERROR(var error: String) : AccountStatus()
    class SUCCESS() : AccountStatus()
}
