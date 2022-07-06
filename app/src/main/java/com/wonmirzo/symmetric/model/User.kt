package com.wonmirzo.symmetric.model

import java.io.Serializable

data class User(
    val email: String,
    val password: String
) : Serializable
