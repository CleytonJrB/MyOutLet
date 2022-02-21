package com.example.myoutlet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity() {

  private val places = arrayListOf(
    Place("C${"&"}A","Av. República do Líbano, 251 - Pina, Recife - PE",LatLng(-8.0864409,-34.896674)),
        Place("Renner","R. Manoel Hónorato da Costa, 555 - Vila da Fabrica, Camaragibe - PE",LatLng(-8.0163327,-34.9775849))

  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.pg_map)


    val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
    mapFragment.getMapAsync{ googleMap ->
      addMarkers(googleMap)

      googleMap.setOnMapLoadedCallback {
        val bounds = LatLngBounds.builder()

        places.forEach { place ->
          bounds.include(place.latLng)
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
      }
    }
  }
  private fun addMarkers(googleMap: GoogleMap){
    places.forEach{ place ->
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
