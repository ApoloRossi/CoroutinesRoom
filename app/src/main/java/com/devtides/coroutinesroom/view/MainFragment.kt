package com.devtides.coroutinesroom.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.devtides.coroutinesroom.databinding.FragmentMainBinding
import com.devtides.coroutinesroom.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signoutBtn.setOnClickListener { onSignout() }
        binding.deleteUserBtn.setOnClickListener { onDelete() }

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.signout.observe(this, Observer {

        })
        viewModel.userDeleted.observe(this, Observer {

        })
    }

    private fun onSignout() {
        val action = MainFragmentDirections.actionGoToSignup()
        Navigation.findNavController(binding.usernameTV).navigate(action)
    }

    private fun onDelete() {
        val action = MainFragmentDirections.actionGoToSignup()
        Navigation.findNavController(binding.usernameTV).navigate(action)
    }

}
