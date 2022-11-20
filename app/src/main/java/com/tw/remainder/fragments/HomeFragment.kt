package com.tw.remainder.fragments

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tw.remainder.components.AppBar
import com.tw.remainder.databinding.FragmentHomeBinding
import com.tw.remainder.viewModels.AddNewTaskViewModel
import com.tw.remainder.viewModels.HomeViewModel
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
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
        val groupAdapter = GroupieAdapter()
        binding.recyclerView.adapter = groupAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        setUpAppBar(navController)
        setAddTaskListener(navController)

        binding.quickTaskDone.setOnClickListener{
            val text = binding.quickTask.text.toString()
            if(text.isNotEmpty()){
                homeViewModel.addQuickTask(text)
                binding.quickTask.text = SpannableStringBuilder("")
            }
        }

        homeViewModel.taskList.observe(viewLifecycleOwner) { taskList ->
            groupAdapter.clear()
            val section = Section()
            taskList.map { task ->
                section.add(TaskHolder(task))
            }
            groupAdapter.add(section)
            if (taskList.isNotEmpty()) {
                Log.e("TaskList", taskList.size.toString())
                taskList.forEach {
                    Log.e("TaskList", it.toString())
                }
            }
        }
    }

    private fun setUpAppBar(navController: NavController) {
        binding.appBar.setContent {
            AppBar(
                title = "Remainder",
                onSearchClick = { navController.navigate(HomeFragmentDirections.actionHomeFragmentToAllRemainderFragment()) })
        }
    }

    private fun setAddTaskListener(navController: NavController) {
        binding.addTaskButton.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToAddNewTaskFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadTask()
    }
}