package com.apps.nb2998.dogs.adapter

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.apps.nb2998.dogs.MainActivity
import com.apps.nb2998.dogs.R
import com.apps.nb2998.dogs.model.Dog
import com.apps.nb2998.dogs.model.DogResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_row.view.*
import okhttp3.*
import java.io.IOException

class DogAdapter(private val dogList: MutableList<String>, private val context: Context) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(LayoutInflater.from(context).inflate(R.layout.single_row, parent, false))
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        with(holder.itemView) {
            tvDogName.text = dogList[position].toUpperCase()
            Log.d("tag", dogList[position])
            fetchAndDisplayImage(dogList[position], ivDogPic)
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    class DogViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    private fun fetchAndDisplayImage(dogName: String, ivDogPic: ImageView) {
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
//                        Log.d("tag", dogResponse!!.status)
                        if (dogResponse != null && dogResponse.status == "success") {
                            Log.d("tag", "not null")

                            val handler = Handler(context.mainLooper)
                            val runnable = Runnable {
                                Picasso.get()
                                        .load(Uri.parse(dogResponse.message))
//                                    .resize(50, 50)
//                                    .centerCrop()
                                        .placeholder(R.drawable.shiba)
                                        .into(ivDogPic)
                            }
                            handler.post(runnable)
                        }
                    }
                })
    }
}