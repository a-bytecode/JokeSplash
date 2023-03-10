package com.example.jokesplash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesplash.R
import com.example.jokesplash.model.JokesClass

class JokeAdapter:  RecyclerView.Adapter<JokeAdapter.ItemViewHolder>() {

    private var dataset = listOf<JokesClass>()

    fun submitlist(jokesList: List<JokesClass>) {
        dataset = jokesList
        notifyDataSetChanged()
    }

    class ItemViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val jokesText = view.findViewById<TextView>(R.id.item_text)
        val cardView = view.findViewById<CardView>(R.id.jokes_CardView_Recycler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemlayout = LayoutInflater.from(parent.context).inflate(R.layout.item_jokes,parent,false)
        return ItemViewHolder(itemlayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
         val data : JokesClass = dataset[position]
        holder.jokesText.text = data.joke
    }

    override fun getItemCount(): Int {
       return dataset.size
    }
}


