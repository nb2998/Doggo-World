package com.apps.nb2998.dogs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arr = arrayOf("Apple", "Appale", "Saple", "Saeqw")
        arrayAdapter = ArrayAdapter(this, android.R.layout.select_dialog_item, arr)

        autoCompleteTextView.threshold = 2
        autoCompleteTextView.setAdapter(arrayAdapter)
    }
}
