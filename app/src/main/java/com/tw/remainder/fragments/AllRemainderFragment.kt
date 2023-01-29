package com.tw.remainder.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tw.remainder.components.AppBar
import com.tw.remainder.databinding.FragmentAllRemainderBinding
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.viewModels.AllRemainderViewModel
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllRemainderFragment : Fragment(),TaskHolder.ItemClickListener {
    private val allRemainderViewModel: AllRemainderViewModel by viewModel()
    private lateinit var binding: FragmentAllRemainderBinding
    private val groupAdapter = GroupieAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllRemainderBinding.inflate(inflater)
        return binding.root
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val searchEnable = mutableStateOf(false)
        val searchText = mutableStateOf("")
        binding.taskRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.taskRecyclerView.adapter = groupAdapter

        allRemainderViewModel.searchEnable.observe(viewLifecycleOwner){
            searchEnable.value = it
        }

        binding.appBar.setContent {
            AppBar(
                title = "All Remainder",
                enableBack = allRemainderViewModel.enableBack,
                onBack = {
                    if (searchEnable.value) searchEnable.value = false
                    else navController.popBackStack()
                },
                onSearchClick = { allRemainderViewModel.toggleSearch() },
                enableSearch = searchEnable.value,
                searchText = searchText,
                onSearchChange = ::onSearchChangeCallBack
            )
        }
        lifecycleScope.launchWhenResumed {
            allRemainderViewModel.searchText.collect{
                searchText.value = it
            }
        }

        allRemainderViewModel.filteredRemainderList.observe(viewLifecycleOwner) { taskList ->
            groupAdapter.clear()
            val section = Section()
            taskList.map { task ->
                Log.e("Task",task.toString())
                section.add(TaskHolder(task, this))
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

    private fun onSearchChangeCallBack(searchText:String){
        allRemainderViewModel.onChangeSearchTextHandler(searchText)
    }

    override fun onTaskClicked(task: TaskEntity) {
        TODO("Not yet implemented")
    }

    override fun onCheckBoxClicked(task: TaskEntity) {
        allRemainderViewModel.changeStatus(task)
    }
}

