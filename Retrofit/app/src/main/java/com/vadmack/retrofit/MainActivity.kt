package com.vadmack.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var retrofit: Retrofit? = null

    lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://anapioficeandfire.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiService = retrofit?.create(APIService::class.java)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            sendRequest()
        }

    }

    fun sendRequest(){
        // hardcoded id to John Snow
        api?.findCharacterById(583)?.enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                Log.i("Person", response.body().toString())
                Toast.makeText(this@MainActivity, response.body()!!.name + " has arrived!", Toast.LENGTH_LONG)
                    .show();
            }

            override fun onFailure(call: Call<Person>, ex: Throwable) {
                Toast.makeText(this@MainActivity, "Request error", Toast.LENGTH_LONG)
                    .show();
            }
        })
    }

    companion object {
        private var apiService: APIService? = null
        val api: APIService?
            get() = apiService
    }

}