package com.hasanshukurov.myquicknote.adapter

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.LayoutInflaterCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hasanshukurov.myquicknote.R
import com.hasanshukurov.myquicknote.databinding.NoteRowBinding
import com.hasanshukurov.myquicknote.model.NoteModel
import com.hasanshukurov.myquicknote.view.HomeFragmentDirections
import com.hasanshukurov.myquicknote.viewmodel.HomeViewModel

class RvAdapter(val rvList: List<NoteModel>,
                val viewModel: HomeViewModel
                ): RecyclerView.Adapter<RvAdapter.RvHolder>() {
    class RvHolder(val binding: NoteRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val binding = NoteRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RvHolder(binding)
    }

    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.itemView.apply {
            val rvPosition = rvList[position]

            holder.binding.homeTextView.text = rvPosition.title

            holder.binding.homeTextView.setOnClickListener {
                val bundle = Bundle().apply {
                    putSerializable("noteArgs",rvPosition)
                }
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_replaceFragment,bundle)
            }

            holder.binding.deleteButton.setOnClickListener {
                val alert = AlertDialog.Builder(holder.itemView.context)
                alert.setTitle("Delete The Note")
                alert.setMessage("Are You Sure Delete The Note ?")
                alert.setPositiveButton("Yes"){dialog,which ->
                    viewModel.deleteNoteVM(rvPosition.id!!)
                    Toast.makeText(holder.itemView.context,"The note has been deleted",Toast.LENGTH_LONG).show()
                }
                alert.setNegativeButton("No"){dialog,which ->
                    return@setNegativeButton
                }
                alert.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return rvList.size
    }
}