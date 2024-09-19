package ru.logosph.effectivemobiletesttask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.logosph.effectivemobiletesttask.R
import ru.logosph.effectivemobiletesttask.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment(R.layout.fragment_messages) {

    private val binding: FragmentMessagesBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

