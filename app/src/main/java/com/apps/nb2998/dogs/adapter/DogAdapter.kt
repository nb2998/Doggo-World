package com.apps.nb2998.dogs.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.nb2998.dogs.R
import com.apps.nb2998.dogs.UtilFunctions
import kotlinx.android.synthetic.main.single_row.view.*

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
//            fetchAndDisplayImage(dogList[position], ivDogPic)  // this keeps generating random images!

            cardViewDogBreed.setOnClickListener {
                UtilFunctions.createDogDescriptionDialog(context, dogList[position])
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    class DogViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)


}