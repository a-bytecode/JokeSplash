package com.example.jokesplash.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesplash.*
import com.example.jokesplash.model.JokesClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JokeAdapter(private val viewModel: MainViewModel):  RecyclerView.Adapter<JokeAdapter.ItemViewHolder>() {

    private var dataset = mutableListOf<JokesClass>()

    private var selectedLimit = 0

    fun submitlist(jokesList: MutableList<JokesClass>) {
        dataset = jokesList
        notifyDataSetChanged()
    }

    fun updateItem(position: Int, joke: JokesClass) {
        dataset[position] = joke
        notifyItemChanged(position)
    }

    fun selectedLimit(limit : Int) {
        selectedLimit = limit
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

        holder.cardView.setOnClickListener {
            val animations = AnimationUtils.loadAnimation(holder.itemView.context,R.anim.animation)
            holder.cardView?.startAnimation(animations)
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                viewModel.getJokes(selectedLimit)
            }
            Log.e("LIMITADAPTER","$selectedLimit")
        }
    }

    override fun getItemCount(): Int {
       return dataset.size
    }
}


