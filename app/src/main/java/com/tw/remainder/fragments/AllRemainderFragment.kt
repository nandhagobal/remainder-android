package com.tw.remainder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tw.remainder.components.AppBar
import com.tw.remainder.databinding.FragmentAllRemainderBinding
import com.tw.remainder.viewModels.AllRemainderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllRemainderFragment : Fragment() {
    private val allRemainderViewModel: AllRemainderViewModel by viewModel()
    private lateinit var binding: FragmentAllRemainderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllRemainderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val searchEnable = mutableStateOf(false)

        allRemainderViewModel.searchEnable.observe(viewLifecycleOwner){
            searchEnable.value = it
        }


        binding.appBar.setContent {
            AppBar(
                enableBack = allRemainderViewModel.enableBack,
                onBack = { navController.popBackStack() },
                onSearchClick = { allRemainderViewModel.toggleSearch() },
                enableSearch = searchEnable.value
            )
        }
    }
}

