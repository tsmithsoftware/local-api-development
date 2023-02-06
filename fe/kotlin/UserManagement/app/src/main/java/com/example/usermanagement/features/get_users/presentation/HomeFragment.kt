package com.example.usermanagement.features.get_users.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core.Failure
import com.example.core.extension.failure
import com.example.core.extension.observe
import com.example.usermanagement.R
import com.example.usermanagement.databinding.FragmentHomeBinding
import com.example.usermanagement.features.get_users.domain.entities.UserEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var usersViewModel: UsersViewModel? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersViewModel = ViewModelProvider(this)[UsersViewModel::class.java]
        usersViewModel?.let {
            with(it) {
                observe(users, ::renderUsersList)
                failure(failure, ::handleFailure)
                it.loadUsers()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.viewmodel = usersViewModel
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun renderUsersList(movies: List<UserEntity>?) {
        _binding?.textHome?.text = movies?.count().toString()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.ServerFailure -> renderFailure(getString(R.string.failure_network_connection))
            is Failure.CacheFailure -> renderFailure(getString(R.string.failure_server_error))
            else -> renderFailure(getString(R.string.failure_server_error))
        }
    }

    private fun renderFailure(failureNetworkConnection: String) {
        _binding?.textHome?.text = failureNetworkConnection
    }
}