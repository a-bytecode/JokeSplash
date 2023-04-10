package com.example.jokesplash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.jokesplash.databinding.DetailFragmentBinding

class DetailFragment:Fragment() {

    private lateinit var binding: DetailFragmentBinding

    private lateinit var jokeSpinner: Spinner

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.detail_fragment, container, false)

        binding = DetailFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val selectedSpinnerItem = requireArguments().getString("selectedItem")

        val animations = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)

        if (!selectedSpinnerItem.isNullOrEmpty()) {
            val selectedLimit = selectedSpinnerItem.toIntOrNull()

            if (selectedLimit != null) {

                binding.jokesCardView.setOnClickListener {
                    animations.start()
                    viewModel.getJokes(selectedLimit)
                }

            } else {
                Toast.makeText(requireContext(),"Das Limit ist nicht gesetzt",Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(),"Kein Element im bundle vorhanden",Toast.LENGTH_LONG).show()

        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
        }

        viewModel.jokes.observe(viewLifecycleOwner, Observer { jokeslist ->
            if(!jokeslist.isNullOrEmpty()) {
                val firstJoke = jokeslist[0]
                binding.jokesText.text = firstJoke.joke
            }
        })

    }
}