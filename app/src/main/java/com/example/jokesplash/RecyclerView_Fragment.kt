package com.example.jokesplash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jokesplash.adapter.JokeAdapter
import com.example.jokesplash.databinding.RecyclerviewFragmentBinding
import com.example.jokesplash.model.JokesClass
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecyclerView_Fragment: Fragment() {

    private lateinit var binding: RecyclerviewFragmentBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RecyclerviewFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val jokeAdapter = JokeAdapter(viewModel)

        binding.jokeRecycler.adapter = jokeAdapter

        val selectedLimit = RecyclerView_FragmentArgs.fromBundle(requireArguments()).selectedItem

        val animations = AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
        val animation2 = AnimationUtils.loadAnimation(requireContext(),R.anim.animation2)

        binding.jokesRecyclerCardView.setOnClickListener {
            binding.jokesRecyclerCardView.startAnimation(animations)
            lifecycleScope.launch(Main) {
                delay(1000)
                viewModel.getJokes(selectedLimit)
                binding.jokeRecycler.visibility = View.VISIBLE
                binding.jokesRecyclerCardView.visibility = View.INVISIBLE
                jokeAdapter.selectedLimit(selectedLimit)
                Log.e("LIMIT","$selectedLimit")
            }


        }

        binding.backButton.setOnClickListener {
            binding.backButton.startAnimation(animation2)
            lifecycleScope.launch(Main) {
                delay(1000)
                findNavController().navigate(RecyclerView_FragmentDirections.actionRecyclerViewFragmentToHomeFragment())
            }
        }

        viewModel.jokes.observe(viewLifecycleOwner, Observer {
            if ( it != null) {
                jokeAdapter.submitlist(it as MutableList<JokesClass>)
            }
        })
    }
}