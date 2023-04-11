package com.example.jokesplash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jokesplash.databinding.DetailFragmentBinding
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailFragment:Fragment() {

    private lateinit var binding: DetailFragmentBinding

    private lateinit var jokeSpinner: Spinner

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val selectedLimit = DetailFragmentArgs.fromBundle(requireArguments()).selectedItem
        val animations = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
        val animations2 = AnimationUtils.loadAnimation(requireContext(),R.anim.animation2)

        binding.jokesCardView.setOnClickListener {
            binding.jokesCardView.startAnimation(animations)
            viewModel.getJokes(selectedLimit)
            Log.e("LIMIT","$selectedLimit")
        }

        binding.backButton.setOnClickListener {
            binding.backButton.startAnimation(animations2)

            lifecycleScope.launch(Main) {
                delay(1000)
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
            }
        }

        viewModel.jokes.observe(viewLifecycleOwner, Observer { jokeslist ->
            if(!jokeslist.isNullOrEmpty()) {
                val firstJoke = jokeslist[0]
                binding.jokesText.text = firstJoke.joke
            }
        })

    }
}