package com.test.task.todo.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.task.todo.data.model.Todos
import com.test.task.todo.databinding.RowItemBinding
import com.test.task.todo.ui.adapter.TodoAdapter.TodosViewHolder

class TodoAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<Todos, TodosViewHolder>(DifferCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        markCompleted(holder.binding, position)
        holder.setData(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class TodosViewHolder(val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(item: Todos) {

            binding.apply {
                textView.text = item.title
                checkBox.isChecked = item.completed
            }
        }

        init {

            binding.checkBox.setOnClickListener { v ->
                val isChecked = (v as CheckBox).isChecked
                val item = currentList[adapterPosition]
                item.completed = isChecked
                markCompleted(binding, position)

                listener.onCheckBoxClick(item)
            }
        }
    }

    private class DifferCallback : DiffUtil.ItemCallback<Todos>() {
        override fun areItemsTheSame(oldItem: Todos, newItem: Todos): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todos, newItem: Todos): Boolean {
            return oldItem == newItem
        }
    }

    fun markCompleted(binding: RowItemBinding, position: Int){
        if (currentList[position].completed) {
            binding.rowitem.setBackgroundColor(Color.parseColor("#D3F4D3"))
        } else {
            binding.rowitem.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    interface OnItemClickListener {
        fun onCheckBoxClick(todo: Todos)
    }
}