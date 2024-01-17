package com.hasanshukurov.myquicknote.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.hasanshukurov.myquicknote.R
import com.hasanshukurov.myquicknote.adapter.RvAdapter
import com.hasanshukurov.myquicknote.databinding.FragmentHomeBinding
import com.hasanshukurov.myquicknote.model.NoteModel
import com.hasanshukurov.myquicknote.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var noteAdapter: RvAdapter
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolBarHome)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.search_menu, menu)
                val item = menu.findItem(R.id.search_Id)
                val searchView = item.actionView as androidx.appcompat.widget.SearchView
                searchView.setOnQueryTextListener(this@HomeFragment)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.noteList.observe(viewLifecycleOwner) { noteList ->
            createAdapter(noteList, viewModel)
        }


        viewModel.getAllNoteVM()

        binding.fab.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(HomeFragmentDirections.actionHomeFragmentToSaveFragment())
        }

    }


    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.searchNoteVM(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.searchNoteVM(newText)
        return true
    }

    private fun createAdapter(list: List<NoteModel>, viewModel: HomeViewModel) {
        noteAdapter = RvAdapter(list, viewModel)
        binding.rv.adapter = noteAdapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onResume() {
        super.onResume()
        viewModel.getAllNoteVM()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}