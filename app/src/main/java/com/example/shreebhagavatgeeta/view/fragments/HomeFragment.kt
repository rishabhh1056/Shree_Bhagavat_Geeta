package com.example.shreebhagavatgeeta.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shreebhagavatgeeta.NetworkManager
import com.example.shreebhagavatgeeta.R
import com.example.shreebhagavatgeeta.databinding.FragmentHomeBinding
import com.example.shreebhagavatgeeta.models.chaptersItem
import com.example.shreebhagavatgeeta.view.adapter.AdapterChapters
import com.example.shreebhagavatgeeta.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val binding : FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val viewModel : MainViewModel by viewModels()
    private lateinit var adapterChapters : AdapterChapters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


       // checkNetworkConnectivity()
        getAllChapters()
//
        Log.e("TAG", "onCreateView: ${getAllChapters()} ", )
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun checkNetworkConnectivity() {
        val networkManager = NetworkManager(requireContext())
        networkManager.observe(viewLifecycleOwner){
            if (it == true)
            {
                binding.shimmer.visibility = View.VISIBLE
                binding.RvChapters.visibility = View.VISIBLE
                binding.checkInternet.visibility = View.GONE
                getAllChapters()
            }
            else {
                binding.shimmer.visibility = View.GONE
                binding.RvChapters.visibility = View.GONE
                binding.checkInternet.visibility = View.VISIBLE
            }
        }
    }

    private fun getAllChapters() {

        lifecycleScope.launch {
            viewModel.getAllChapters().collect{

                for (i in it)
                {
                    Log.e("TAG", "getAllChapters: ${i.toString()}", )
                }
               adapterChapters = AdapterChapters(::onChapterIVClicked)
                binding.RvChapters.adapter = adapterChapters
                adapterChapters.differ.submitList(it)
                binding.shimmer.visibility = View.GONE

            }
        }

    }

    private fun onChapterIVClicked(chaptersItem: chaptersItem){

        findNavController().navigate(R.id.action_homeFragment_to_verseFragment)
    }


}