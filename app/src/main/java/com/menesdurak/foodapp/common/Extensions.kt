package com.menesdurak.foodapp.common

import android.text.TextUtils
import android.util.Patterns

fun String.isValidEmail(): Boolean =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()