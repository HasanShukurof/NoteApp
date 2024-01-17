package com.hasanshukurov.myquicknote.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hasanshukurov.myquicknote.databinding.FragmentReplaceBinding
import com.hasanshukurov.myquicknote.viewmodel.ReplaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReplaceFragment : Fragment() {

    private var _binding: FragmentReplaceBinding? = null
    private val binding: FragmentReplaceBinding get() = _binding!!
    private lateinit var viewModel: ReplaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ReplaceViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReplaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: ReplaceFragmentArgs by navArgs()
        val gelenArgs = bundle.noteArgs

        binding.titleText.setText(gelenArgs.title)
        binding.noteText.setText(gelenArgs.note)



        binding.buttonReplace.setOnClickListener {

            val title = binding.titleText.text.toString()
            val note = binding.noteText.text.toString()

            viewModel.replaceNoteVM(gelenArgs.id!!, title, note)


            findNavController().navigate(ReplaceFragmentDirections.actionReplaceFragmentToHomeFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}