package com.ngapps.composereceivecontent.model

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Player(
    val name: String,
    val team: String,
    val position: String,
    val sportCategory: String,
    val id: String = UUID.randomUUID().toString(),
)