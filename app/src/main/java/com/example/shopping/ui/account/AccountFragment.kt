package com.example.shopping.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.shopping.R
import com.example.shopping.databinding.FragmentAccountBinding
import com.example.shopping.util.initToolbar

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileLogout.setOnClickListener {
            // toDo I'll implementation this a code next time
            Toast.makeText(context, "I'll implementation this a code next time", Toast.LENGTH_SHORT).show()
        }
        initToolbar(binding.toolbar, R.string.account,false)
    }

}