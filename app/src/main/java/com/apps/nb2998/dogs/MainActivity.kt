package com.apps.nb2998.dogs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import com.apps.nb2998.dogs.adapter.DogAdapter
import com.apps.nb2998.dogs.model.Dog
import com.apps.nb2998.dogs.model.DogResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var dogAdapter: DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arr: Array<String> = resources.getStringArray(R.array.dogBreeds)
        arrayAdapter = ArrayAdapter(this, android.R.layout.select_dialog_item, arr)

        autoCompleteTextView.threshold = 2
        autoCompleteTextView.setAdapter(arrayAdapter)

        dogAdapter = DogAdapter(arr.toMutableList(), this)
//        recViewDogsList.layoutManager = GridLayoutManager(this, 3)
        recViewDogsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recViewDogsList.adapter = dogAdapter
    }
}
