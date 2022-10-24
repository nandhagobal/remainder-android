package com.tw.remainder.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tw.remainder.components.AppBar
import com.tw.remainder.databinding.FragmentHomeBinding
import com.tw.remainder.viewModels.HomeViewModel
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.appBar.setContent {
            AppBar(
                title = "Remainder",
                onSearchClick = { navController.navigate(HomeFragmentDirections.actionHomeFragmentToAllRemainderFragment()) })
        }
        binding.addTaskButton.setOnClickListener {
//            navController.navigate(HomeFragmentDirections.actionHomeFragmentToAddNewTaskFragment())
            homeViewModel.addTask()
        }

        homeViewModel.taskList.observe(viewLifecycleOwner) { taskList ->
            Log.e("TaskList", taskList[0].toString())
        }

    }
}