package com.example.shreebhagavatgeeta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.shreebhagavatgeeta.databinding.FragmentVerseBinding
import com.example.shreebhagavatgeeta.models.versesItem
import com.example.shreebhagavatgeeta.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class VerseFragment : Fragment() {

    private val binding : FragmentVerseBinding by lazy {
        FragmentVerseBinding.inflate(layoutInflater)
    }
    private val viewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getAllVerses()
        return binding.root
    }

    private fun getAllVerses() {
        lifecycleScope.launch {
            viewModel.getVerses(4).collect{
                for (i:versesItem in it)
                {
                    Log.e("TAG", "getAllVerses: ${i.toString()}", )
                    Toast.makeText(context, i.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}