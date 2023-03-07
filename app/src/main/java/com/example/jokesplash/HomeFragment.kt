package com.example.jokesplash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
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

        //TODO Spinner maximale Anzahl an 4 Elementen festsetzen, den rest Scrollbar.

//        val spinner = view.findViewById<Spinner>(R.id.spinner_home)
//
//        val itemHeight = resources.getDimension(R.dimen.spinner_item_height)
//
//        spinner.dropDownWidth = (itemHeight * 4).toInt()
//
//        spinner.dropDownVerticalOffset = itemHeight.toInt()


        val options = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerHome.adapter = adapter


        val drawableElements = arrayOf(
            R.drawable.funny1,
            R.drawable.funny2,
            R.drawable.funny4,
            R.drawable.funny5,
            R.drawable.funny6,
            R.drawable.funny8,
            R.drawable.funny9,
            R.drawable.funny10,
            R.drawable.funny11
        )

        val animations = AnimationUtils.loadAnimation(requireContext(),R.anim.pulse)

        var currentDrawableIndex = 0

        binding.go4Jokes.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
        }

        binding.glideCoverHome.setOnClickListener {
            binding.glideCoverHome.startAnimation(animations)
            currentDrawableIndex = (currentDrawableIndex +1) % drawableElements.size
            Glide.with(requireContext())
                .load(drawableElements[currentDrawableIndex])
                .centerCrop()
                .placeholder(drawableElements.random())
                .into(binding.glideCoverHome)
        }

        Glide
            .with(requireContext())
            .load(drawableElements.random())
            .centerCrop()
            .placeholder(drawableElements.random())
            .into(binding.glideCoverHome)
    }

}