package com.example.jokesplash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.jokesplash.databinding.HomeFragmentBinding

class HomeFragment: Fragment() {

    private lateinit var binding : HomeFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val drawableElements = arrayOf(R.drawable.funny1,R.drawable.funny3)


        binding.go4Jokes.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
        }

        Glide
            .with(requireContext())
            .load(drawableElements.random())
            .centerCrop()
            .placeholder(drawableElements.random())
            .into(binding.glideCoverHome)


    }

}