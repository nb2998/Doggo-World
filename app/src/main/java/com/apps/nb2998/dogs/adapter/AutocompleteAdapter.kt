package com.apps.nb2998.dogs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.apps.nb2998.dogs.R
import com.apps.nb2998.dogs.UtilFunctions

class AutocompleteAdapter(val context: Context) : BaseAdapter(), Filterable {
    private val MAX_RESULTS = 10
    private var resultList: List<String> = ArrayList<String>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        }

        view!!.findViewById<TextView>(android.R.id.text1).text = getItem(position).toString()
        return view
    }

    override fun getItem(position: Int): Any = resultList.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = resultList.size

    override fun getFilter(): Filter {
        val filter = object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
//                UtilFunctions.ge
//                filterResults.
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

            }
        }
        return filter
    }
}