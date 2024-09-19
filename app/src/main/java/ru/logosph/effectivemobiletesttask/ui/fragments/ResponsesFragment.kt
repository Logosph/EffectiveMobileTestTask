package ru.logosph.effectivemobiletesttask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.logosph.effectivemobiletesttask.databinding.FragmentResponsesBinding

class ResponsesFragment : Fragment() {

    private lateinit var binding: FragmentResponsesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResponsesBinding.inflate(inflater, container, false)

        return binding.root
    }
}