package com.test.task.todo.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.task.todo.data.model.Resource
import com.test.task.todo.data.model.Todos
import com.test.task.todo.databinding.FragmentToDoListBinding
import com.test.task.todo.ui.adapter.TodoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToDoListFragment : Fragment(), TodoAdapter.OnItemClickListener {

    private var adapter = TodoAdapter(this@ToDoListFragment)

    private val todosViewModel: TodosViewModel by viewModels()

    private lateinit var binding: FragmentToDoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentToDoListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }

        binding.button.setOnClickListener {
            val newTodo = binding.editText.text.toString()
            if (TextUtils.isEmpty(newTodo)) {
                Toast.makeText(
                    requireContext(), "Empty field not allowed!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                lifecycleScope.launch {
                    todosViewModel.addTodo(Todos(title = newTodo, completed = false))
                }
                setupTodosList()
            }
            binding.editText.text.clear()
        }
        setupTodosList()
    }

    private fun setupTodosList() {
        lifecycleScope.launch {
            todosViewModel.getTodosFromDB().observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Success -> {
                        success(result.data)
                        if (result.data.isEmpty()) {
                            initTodosList()
                        }
                    }
                    is Resource.Loading -> {
                        loading()
                    }
                    is Resource.Failure -> {
                        failure(result.e)
                    }
                }
            }
        }
    }

    private fun initTodosList() {
        lifecycleScope.launch {
            todosViewModel.getTodosFromApi().observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Success -> {
                        todosViewModel.addAllTodos(result.data)
                        success(result.data)
                    }
                    is Resource.Loading -> {
                        loading()
                    }
                    is Resource.Failure -> {
                        failure(result.e)
                    }
                }
            }
        }
    }

    private fun success(data: List<Todos>) {
        binding.progressBar.visibility = View.GONE
        adapter.submitList(data)
    }

    private fun loading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun failure(e: Exception) {
        binding.progressBar.visibility = View.GONE
        Log.e("ToDoListFragment", e.toString())
        Toast.makeText(
            requireContext(),
            "Problem with retrieving data",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onCheckBoxClick(todo: Todos) {
        todosViewModel.updateTodo(todo)
    }
}