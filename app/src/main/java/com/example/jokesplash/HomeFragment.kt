package com.example.jokesplash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.jokesplash.databinding.HomeFragmentBinding

class HomeFragment: Fragment() {

    private lateinit var binding : HomeFragmentBinding

    private val viewModel: MainViewModel by activityViewModels()


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

        val options = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)

        val limitSpinner = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)

        limitSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerHome.adapter = limitSpinner

        binding.spinnerHome.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val limit = options[position]
                viewModel.geJokes(limit)
                val detailFragment = DetailFragment.newInstance(limit)
                val transaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.homeFragment, detailFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(requireContext(),"Please select Limit",Toast.LENGTH_LONG).show()
            }
        }

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

//        var currentDrawableIndex = 0

        binding.go4Jokes.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
        }

//        binding.glideCoverHome.setOnClickListener {
////            currentDrawableIndex = (currentDrawableIndex +1) % drawableElements.size
//            Glide.with(requireContext())
//                .load(drawableElements[currentDrawableIndex])
//                .centerCrop()
//                .placeholder(drawableElements.random())
//                .into(binding.glideCoverHome)
//        }

        Glide
            .with(requireContext())
            .load(drawableElements.random())
            .centerCrop()
            .placeholder(drawableElements.random())
            .into(binding.glideCoverHome)
    }

}