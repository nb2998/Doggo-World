package com.apps.nb2998.dogs

import android.annotation.SuppressLint
import android.content.Context
import android.os.Message
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar

class DelayAutoCompleteTextView(ctx: Context): AutoCompleteTextView(ctx) {

    val MESSAGE_TEXT_CHANGED = 100
    val DEFAULT_AUTOCOMPLETE_DELAY = 100
    lateinit var loadingIndicator: ProgressBar

//    private val mHandler = object : Handler() {
//        override fun handleMessage(msg: Message) {
////            super@DelayAutoCompleteTextView.performFiltering(msg.obj as CharSequence, msg.arg1)
//        }
//    }

    private  val mHandler = @SuppressLint("HandlerLeak")
    object: android.os.Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
        }
    }

    override fun performFiltering(text: CharSequence?, keyCode: Int) {
        super.performFiltering(text, keyCode)
    }


}