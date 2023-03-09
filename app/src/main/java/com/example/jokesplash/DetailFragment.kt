package com.example.jokesplash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.jokesplash.databinding.DetailFragmentBinding

class DetailFragment:Fragment() {

    private lateinit var binding: DetailFragmentBinding

    private val viewModel: MainViewModel by activityViewModels()

    companion object {
        private const val ARG_LIMIT = "limit"

        fun newInstance(limit: Int): DetailFragment {
            val args = Bundle().apply {
                putInt(ARG_LIMIT, limit)
            }
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var limit: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
            limit = it.getInt(ARG_LIMIT)
        }

        val animations = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)

        binding.jokesCardView.setOnClickListener {
            binding.jokesCardView.startAnimation(animations)
            viewModel.geJokes(limit)
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