package com.tw.remainder.fragments

import android.view.View
import com.tw.remainder.R
import com.tw.remainder.databinding.FragmentTaskHolderBinding
import com.tw.remainder.entities.TaskEntity
import com.xwray.groupie.viewbinding.BindableItem

class TaskHolder(val task:TaskEntity):BindableItem<FragmentTaskHolderBinding>() {

    override fun initializeViewBinding(view: View): FragmentTaskHolderBinding {
        return FragmentTaskHolderBinding.bind(view)
    }

    override fun bind(binding: FragmentTaskHolderBinding, position: Int) {
        binding.title.text = task.name
        binding.date.text = task.date.toString()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_task_holder
    }


}