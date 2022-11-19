package com.tw.remainder.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.internal.TextWatcherAdapter
import com.tw.remainder.components.AppBar
import com.tw.remainder.databinding.FragmentAddNewTaskBinding
import com.tw.remainder.theme.RemainderTheme
import com.tw.remainder.viewModels.AddNewTaskViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddNewTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddNewTaskBinding
    private val viewModel : AddNewTaskViewModel by viewModel()

    private val calendar = Calendar.getInstance()
    private val currentYear = calendar.get(Calendar.YEAR)
    private val currentMonth = calendar.get(Calendar.MONTH)
    private val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    private val currentHour = calendar.get(Calendar.HOUR)
    private val currentMinute = calendar.get(Calendar.MINUTE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.appBar.setContent {
            RemainderTheme {
                AppBar(
                    title = "Add Task",
                    enableBack = true,
                    doneIcon = true,
                    onBack = { navController.popBackStack() },
                    onDone = { viewModel.addTask() }
                )
            }
        }

        viewModel.date.observe(viewLifecycleOwner){
            binding.dateEditTextView.text = SpannableStringBuilder(it)
        }

        viewModel.time.observe(viewLifecycleOwner){
            binding.timeEditTextView.text = SpannableStringBuilder(it)
        }

        binding.titleEditTextView.addTextChangedListener(@SuppressLint("RestrictedApi")
        object : TextWatcherAdapter(){
            override fun onTextChanged(title: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.setTitle(title.toString())
            }
        })

        initDateListener()
        initTimeListener()
    }

    private fun initTimeListener() {
        binding.timeEditTextView.setOnClickListener {
            val timePickerDialog =
                TimePickerDialog(requireContext(), { _, hour, minute ->
                    viewModel.setTime(minute,hour)
                }, currentHour, currentMinute, false)
            timePickerDialog.show()
        }
        binding.dateClearButton.setOnClickListener{
            viewModel.clearDate()
        }
    }

    private fun initDateListener() {
        binding.dateEditTextView.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                    viewModel.setDate(day = dayOfMonth, month = monthOfYear,year = year)
                }, currentYear, currentMonth, currentDay)
            datePickerDialog.show()
        }
        binding.timeClearButton.setOnClickListener{
            viewModel.clearTime()
        }
    }
}