package com.devtides.coroutinesroom.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.devtides.coroutinesroom.databinding.FragmentLoginBinding
import com.devtides.coroutinesroom.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener { onLogin(it) }
        binding.gotoSignupBtn.setOnClickListener { onGotoSignup(it) }

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginComplete.observe(this, Observer { isComplete ->

        })

        viewModel.error.observe(this, Observer { error ->


        })
    }

    private fun onLogin(v: View) {
        val action = LoginFragmentDirections.actionGoToMain()
        Navigation.findNavController(v).navigate(action)
    }

    private fun onGotoSignup(v: View) {
        val action = LoginFragmentDirections.actionGoToSignup()
        Navigation.findNavController(v).navigate(action)
    }
}
