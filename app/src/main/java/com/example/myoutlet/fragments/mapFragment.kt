package com.example.myoutlet.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myoutlet.R
import com.example.myoutlet.databinding.PgMapBinding
import com.example.myoutlet.model.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class mapFragment : Fragment() {

  companion object {
    fun newInstance() = mapFragment()
  }

  private var _binding: PgMapBinding? = null
  private val binding get() = _binding!!

  private val places = arrayListOf(

    Place(
      "C${"&"}A",
      "Av. República do Líbano, 251 - Pina, Recife - PE",
      LatLng(-8.0864409, -34.896674)
    ),
    Place(
      "Renner",
      "R. Manoel Hónorato da Costa, 555 - Vila da Fabrica, Camaragibe - PE",
      LatLng(-8.0163327, -34.9775849)
    )

  )
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = PgMapBinding.inflate(inflater, container, false)
    val view = binding.root

//    binding.btnMapBack.setOnClickListener {
//      findNavController().navigate(R.id.fromMapFragmenttoProductFragment)
//    }
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)


    val mapFragment =
      childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
    mapFragment.getMapAsync { googleMap ->
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

  override fun onResume() {
    super.onResume()
    Log.d("wwwd", "OnResume MapFragment")
  }

  override fun onPause() {
    super.onPause()
    Log.d("wwwd", "onPause MapFragment")
  }

  private fun addMarkers(googleMap: GoogleMap) {
    places.forEach { place ->
      googleMap.addMarker(
        MarkerOptions()
          .title(place.name)
          .snippet(place.adress)
          .position(place.latLng)
      )
    }
  }
}

