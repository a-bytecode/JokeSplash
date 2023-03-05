package com.example.jokesplash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jokesplash.databinding.DetailFragmentBinding

class DetailFragment:Fragment() {

    private lateinit var binding: DetailFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailFragmentBinding.inflate(inflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}