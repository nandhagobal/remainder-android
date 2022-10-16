package com.tw.remainder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.fragment.findNavController
import com.tw.remainder.R
import com.tw.remainder.components.AppBar
import com.tw.remainder.databinding.FragmentAllRemainderBinding

class AllRemainderFragment : Fragment() {
    private lateinit var binding: FragmentAllRemainderBinding
    val navController = findNavController()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllRemainderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchEnable = mutableStateOf(false)
        binding.appBar.setContent {
            AppBar(
                enableBack = true,
                onBack = { navController.popBackStack() },
                onSearchClick = { searchEnable.value = true },
                enableSearch = searchEnable.value
            )
        }
    }
}

