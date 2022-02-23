package com.example.myoutlet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myoutlet.model.Maps
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity() {

  private val places : ArrayList<Place> ?= null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.pg_map)

    val teste1 = places


    val mapData = intent.extras


    val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
    mapFragment.getMapAsync{ googleMap ->
      addMarkers(googleMap)

      googleMap.setOnMapLoadedCallback {
        val bounds = LatLngBounds.builder()

        places?.forEach { place ->
          bounds.include(place.latLng)
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
      }
    }
  }
  private fun addMarkers(googleMap: GoogleMap){
    places?.forEach{ place ->
      googleMap.addMarker(
        MarkerOptions()
          .title(place.name)
          .snippet(place.adress)
          .position(place.latLng)
      )
    }
  }
}

data class Place(
  val name : String,
    val adress : String,
        val latLng : LatLng,
)
