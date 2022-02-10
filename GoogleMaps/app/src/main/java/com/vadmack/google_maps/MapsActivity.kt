package com.vadmack.google_maps

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)

        button.setOnClickListener{
            findLocation()
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    @SuppressLint("ShowToast")
    private fun findLocation() {
        val location = editText.text.toString()

        val geocoder = Geocoder(this, Locale.getDefault())
        var adresses: List<Address> = emptyList()
        try {
            adresses = geocoder.getFromLocationName(location, 1)
        } catch (ioException: IOException) {
        } catch (ex: IllegalArgumentException) {
        }


        if (adresses.isNotEmpty()) {
            val latLng = LatLng(adresses[0].latitude, adresses[0].longitude)
            mMap.setMinZoomPreference(17.0f)
            mMap.addMarker(MarkerOptions().position(latLng).title(location))
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        } else {
            Toast.makeText(applicationContext, "Location not found", Toast.LENGTH_LONG);
        }

    }
}