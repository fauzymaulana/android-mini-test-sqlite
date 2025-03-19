package com.papero.minisqlite.data.remote.entities

import com.google.gson.annotations.SerializedName


data class GoogleGithubResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("html_url")
    val linkRepo: String?
)
