package com.apps.nb2998.dogs

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import com.apps.nb2998.dogs.model.DogResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dog_description_dialog.view.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class UtilFunctions {
    companion object {
        fun createDogDescriptionDialog(context: Context, dogName: String) {
            val dialogBuilder = AlertDialog.Builder(context)
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dog_description_dialog, null)
            dialogBuilder.setView(dialogView)
            dialogView.tvDogBreed.text = dogName.toUpperCase()
            fetchAndDisplayImage(context, dogName, dialogView.ivDogPic)
            dialogBuilder.create().show()
        }

        fun fetchAndDisplayImage(context: Context, dogName: String, ivDogPic: ImageView) {
            val baseUrl = "https://dog.ceo/api/breed/$dogName/images/random"
            val okHttpClient = OkHttpClient()
            val request = Request.Builder()
                    .url(baseUrl)
                    .build()
            okHttpClient.newCall(request)
                    .enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            Log.d("tag", e.localizedMessage)
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val gson = Gson()
                            val result = response.body()?.string()
                            val dogResponse: DogResponse? = gson.fromJson(result, DogResponse::class.java)
                            if (dogResponse != null && dogResponse.status == "success") {

                                val handler = Handler(context.mainLooper)
                                val runnable = Runnable {
                                    Picasso.get()
                                            .load(Uri.parse(dogResponse.message))
                                            .placeholder(R.drawable.shiba)
                                            .into(ivDogPic)
                                }
                                handler.post(runnable)
                            }
                        }
                    })
        }

        fun fetchList(): List<String> {
            val list = mutableListOf<String>()
            val url = "https://dog.ceo/api/breeds/list/all"
            val okHttpClient = OkHttpClient()
            val request = Request.Builder().url(url).build()
            okHttpClient.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
//                    val jsonObject = JSONObject(response)
                }

            })
            return list
        }
    }
}