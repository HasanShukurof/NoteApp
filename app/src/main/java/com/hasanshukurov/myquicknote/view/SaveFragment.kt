package com.hasanshukurov.myquicknote.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hasanshukurov.myquicknote.R
import com.hasanshukurov.myquicknote.databinding.FragmentHomeBinding
import com.hasanshukurov.myquicknote.databinding.FragmentSaveBinding
import com.hasanshukurov.myquicknote.viewmodel.HomeViewModel
import com.hasanshukurov.myquicknote.viewmodel.SaveViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : Fragment() {

    private var _binding: FragmentSaveBinding? = null
    private val binding: FragmentSaveBinding get() = _binding!!
    private lateinit var viewModel: SaveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this).get(SaveViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {

            val title = binding.titleText.text.toString()
            val note = binding.noteText.text.toString()

            viewModel.saveNoteVM(title, note)

            findNavController().navigate(SaveFragmentDirections.actionSaveFragmentToHomeFragment())
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}