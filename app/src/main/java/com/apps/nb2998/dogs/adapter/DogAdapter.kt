package com.apps.nb2998.dogs.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.nb2998.dogs.R
import com.apps.nb2998.dogs.model.Dog
import com.apps.nb2998.dogs.model.DogResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_row.*
import kotlinx.android.synthetic.main.single_row.view.*

class DogAdapter(private val dogList: MutableList<Dog>, private val context: Context): RecyclerView.Adapter<DogAdapter.DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(LayoutInflater.from(context).inflate(R.layout.single_row, parent, false))
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        with(holder.itemView){
            tvDogName.text = dogList[position].name
            Picasso.get()
                    .load(dogList[position].url)
                    .resize(50, 50)
                    .centerCrop()
                    .into(ivDogPic)
        }

    }

    class DogViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}