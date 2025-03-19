package com.papero.minisqlite.presentation.github

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.papero.minisqlite.R
import com.papero.minisqlite.core.base.BaseFragment
import com.papero.minisqlite.core.state.ResultState
import com.papero.minisqlite.core.utilities.OnClickListenerAdapter
import com.papero.minisqlite.core.utilities.observe
import com.papero.minisqlite.databinding.FragmentGoogleRepoBinding
import com.papero.minisqlite.domain.entities.GoogleRepoEntity
import com.papero.minisqlite.domain.entities.SnackBar
import com.papero.minisqlite.presentation.github.adapter.GoogleRepoAdapter

class GoogleRepoFragment : BaseFragment()  {

    private var _binding: FragmentGoogleRepoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GoogleRepoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoogleRepoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        setupAdapter()
    }

    private fun observer() {
        observe(viewModel.allRepoGoogle, ::observeListGoogleRepo)
        observe(viewModel.showSnackBar, ::observeSnackbar)
    }

    private fun observeListGoogleRepo(resultState: ResultState<List<GoogleRepoEntity>>) {
        when(resultState) {
            is ResultState.BadRequest -> {}
            is ResultState.Forbidden -> {}
            is ResultState.HideLoading -> {}
            is ResultState.Loading -> {}
            is ResultState.NoConnection, is ResultState.Timeout -> {}
            is ResultState.NotFound -> {}
            is ResultState.Success -> {
                listRepoAdapter.submitList(resultState.data)
            }
            is ResultState.Unauthorized -> {}
            is ResultState.UnknownError -> {}
            else -> {

            }
        }
    }

    private val listRepoAdapter by lazy {
        GoogleRepoAdapter(OnClickListenerAdapter { r ->
            Log.e("TAG", "REPORT DI KLIK ${r.id}: ", )
        })
    }

    private fun setupAdapter() {
        binding.rvListRepo.removeAllViews()
        binding.rvListRepo.setHasFixedSize(true)
        binding.rvListRepo.layoutManager = LinearLayoutManager(context)
        binding.rvListRepo.adapter = listRepoAdapter
    }

    private fun observeSnackbar(snackBar: SnackBar) {
        showSnackBarWithAction(
            snack = snackBar,
            actionMessage = if (snackBar.action != null) "Muat ulang" else null
        ) {
            snackBar.action
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchAllRepoGoogle()
    }
}