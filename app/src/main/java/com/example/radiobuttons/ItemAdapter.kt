package com.example.radiobuttons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.radiobuttons.databinding.ItemBankBinding


class ItemAdapter(private val items: List<Bank>, val onClick: (Bank) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var checkPosition = -1

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemBankBinding.bind(view)

        fun bind(item: Bank, position: Int) {
            Glide.with(view.context).load(item.img).into(binding.ivIconBanco)
            binding.tvNameBank.text = item.name
            // si adapterPosition esta depricado usrar -> absoluteAdapterPosition
            binding.rbBank.isChecked = adapterPosition == checkPosition
            binding.rbBank.tag = position
            binding.bankSelected.tag = position

            binding.bankSelected.setOnClickListener {
                //binding.rbBank.isChecked = !binding.rbBank.isChecked
                binding.rbBank.isChecked = true
                deleteSelectedPosition()
            }
            binding.rbBank.setOnClickListener {
                deleteSelectedPosition()
            }
        }

        private fun deleteSelectedPosition() {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                if (binding.rbBank.isChecked) {
                    onClick(items[adapterPosition])
                }
            }
            checkPosition = adapterPosition
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bank, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size

}