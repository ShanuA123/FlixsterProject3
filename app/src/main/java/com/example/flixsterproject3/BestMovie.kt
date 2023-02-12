package com.example.flixsterproject3

import com.google.gson.annotations.SerializedName

class BestMovie {
    @SerializedName("original_title")
    var title: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("poster_path")
    var imageURL: String? = null

}