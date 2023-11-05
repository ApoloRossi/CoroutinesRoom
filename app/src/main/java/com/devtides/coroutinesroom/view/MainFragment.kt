package com.devtides.coroutinesroom.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.devtides.coroutinesroom.databinding.FragmentMainBinding
import com.devtides.coroutinesroom.model.LoginState
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

        binding.usernameTV.text = "Welcome ${LoginState.user?.username}"

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.signout.observe(this, Observer {
            Toast.makeText(activity, "Signed out", Toast.LENGTH_SHORT).show()
            val action = MainFragmentDirections.actionGoToSignup()
            Navigation.findNavController(binding.usernameTV).navigate(action)
        })
        viewModel.userDeleted.observe(this, Observer {
            Toast.makeText(activity, "User deleted", Toast.LENGTH_SHORT).show()
            val action = MainFragmentDirections.actionGoToSignup()
            Navigation.findNavController(binding.usernameTV).navigate(action)
        })
    }

    private fun onSignout() {
        viewModel.onSignout()
    }

    private fun onDelete() {
       activity?.let {
           AlertDialog.Builder(it)
               .setTitle("Delete Account")
               .setMessage("Are you sure you want to delete your account?")
               .setPositiveButton("Yes") { dialog, which ->
                   viewModel.onDeleteUser()
               }
               .setNegativeButton("Cancel", null)
               .create()
               .show()
       }
    }

}
