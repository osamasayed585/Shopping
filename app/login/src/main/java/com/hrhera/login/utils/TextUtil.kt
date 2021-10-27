package com.hrhera.login.utils

import android.text.TextUtils
import android.util.Patterns


 fun String?.isInvalidEmail(): Boolean {
    return this ==null || TextUtils.isEmpty(this) || !(Patterns.EMAIL_ADDRESS.matcher(this).matches())
}

fun String?.isInvalidPassword(): Boolean {
    return this ==null || TextUtils.isEmpty(this) || this.length<6
}

fun String?.isInvalidPhone(): Boolean {
    return this ==null || TextUtils.isEmpty(this) || this.length<11
}

