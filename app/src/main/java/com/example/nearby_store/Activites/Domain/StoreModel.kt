package com.example.nearby_store.Activites.Domain

import java.io.Serializable

data class StoreModel(
    var Id: Int = 0,
    var CategoryId: String,
    var varTitle: String = "",
    var Latitude: Double = 0.0,
    var Longitude: Double = 0.0,
    var Call: String = "",
    var Address: String ="",
    var Activity: String ="",
    var ShortAddress: String ="",
    var Hours: String="",
    var ImagePath: String =""

) : Serializable