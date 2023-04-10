package com.example.jokesplash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jokesplash.adapter.JokeAdapter
import com.example.jokesplash.databinding.RecyclerviewFragmentBinding

class RecyclerView_Fragment: Fragment() {

    private lateinit var binding: RecyclerviewFragmentBinding

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


        binding.go4Jokes3.setOnClickListener {
            findNavController().navigate(RecyclerView_FragmentDirections.actionRecyclerViewFragmentToHomeFragment())
        }


    }
}