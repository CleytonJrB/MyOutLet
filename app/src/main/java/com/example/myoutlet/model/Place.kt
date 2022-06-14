package com.example.myoutlet.model

import com.google.android.gms.maps.model.LatLng

data class Place(val name : String,
                 val address : String,
                 val latLng : LatLng,)
