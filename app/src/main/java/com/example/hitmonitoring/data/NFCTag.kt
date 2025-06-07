package com.example.hitmonitoring.data

import kotlinx.serialization.Serializable


@Serializable
data class NFCTag(
    val code: Int,
    val tagType: Int,
    val tagName: String,
    val message: String
)
