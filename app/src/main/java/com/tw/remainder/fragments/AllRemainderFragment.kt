package com.tw.remainder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tw.remainder.R
import com.tw.remainder.databinding.FragmentAllRemainderBinding

class AllRemainderFragment : Fragment() {
    private lateinit var binding: FragmentAllRemainderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllRemainderBinding.inflate(inflater)
        return binding.root
    }


}