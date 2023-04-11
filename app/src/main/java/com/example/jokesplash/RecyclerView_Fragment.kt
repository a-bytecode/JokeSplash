package com.example.jokesplash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.jokesplash.adapter.JokeAdapter
import com.example.jokesplash.databinding.RecyclerviewFragmentBinding
import com.example.jokesplash.model.JokesClass

class RecyclerView_Fragment: Fragment() {

    private lateinit var binding: RecyclerviewFragmentBinding

    private val viewModel: MainViewModel by activityViewModels()

    private val selectedJokesList = mutableListOf<JokesClass>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RecyclerviewFragmentBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val jokeAdapter = JokeAdapter()

        binding.jokeRecycler.adapter = jokeAdapter

//        val selectedLimit = DetailFragmentArgs.fromBundle(requireArguments()).selectedItem

        val selectedLimit = RecyclerView_FragmentArgs.fromBundle(requireArguments()).selectedItem

        binding.jokesRecyclerCardView.setOnClickListener {
            binding.jokeRecycler.visibility = View.VISIBLE
            binding.jokesRecyclerCardView.visibility = View.INVISIBLE

            viewModel.getJokes(selectedLimit)
            Log.e("LIMIT","$selectedLimit")

            val jokeslist = viewModel.jokes.value
            if (!jokeslist.isNullOrEmpty()) {
                jokeslist.firstOrNull()?.let { selectedJokesList.add(it) }
                jokeAdapter.notifyDataSetChanged()

            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(RecyclerView_FragmentDirections.actionRecyclerViewFragmentToHomeFragment())
        }



    }
}