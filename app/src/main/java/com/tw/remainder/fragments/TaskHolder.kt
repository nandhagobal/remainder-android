package com.tw.remainder.fragments

import android.annotation.SuppressLint
import android.view.View
import com.tw.remainder.R
import com.tw.remainder.databinding.FragmentTaskHolderBinding
import com.tw.remainder.entities.TaskEntity
import com.xwray.groupie.viewbinding.BindableItem

class TaskHolder(val task: TaskEntity, private val itemClickListener: ItemClickListener):BindableItem<FragmentTaskHolderBinding>() {

    override fun initializeViewBinding(view: View): FragmentTaskHolderBinding {
        return FragmentTaskHolderBinding.bind(view)
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: FragmentTaskHolderBinding, position: Int) {
        binding.task = task
        binding.itemListener = itemClickListener
        binding.title.text = task.title
        binding.date.text = "${task.date} ${task.time}"
    }

    override fun getLayout(): Int {
        return R.layout.fragment_task_holder
    }

    interface ItemClickListener{
        fun onTaskClicked(task: TaskEntity)
    }

}