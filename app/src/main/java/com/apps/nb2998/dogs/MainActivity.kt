package com.apps.nb2998.dogs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import com.apps.nb2998.dogs.adapter.DogAdapter
import kotlinx.android.synthetic.main.activity_main.*

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

        btnSearch.setOnClickListener{
            searchAndDisplay(autoCompleteTextView.text.toString())
        }
    }

    private fun searchAndDisplay(dogBreed: String) {
        UtilFunctions.createDogDescriptionDialog(this, dogBreed)
    }
}
