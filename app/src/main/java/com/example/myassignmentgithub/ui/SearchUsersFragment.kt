package com.example.myassignmentgithub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myassignmentgithub.R
import com.example.myassignmentgithub.databinding.FragmentSearchUserBinding
import com.example.myassignmentgithub.model.UserShortInfo
import com.example.myassignmentgithub.ui.listeners.SearchUsersListener
import com.example.myassignmentgithub.viewmodels.SearchUserListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Fragment to search users.
 */
class SearchUsersFragment : DaggerFragment(), OnNavigateToUserDetailsListener, SearchUsersListener {

    private var _binding: FragmentSearchUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchUserListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.listener = this
        viewModel.searchUser(null)
        binding.viewModel = viewModel
        viewModel.screenState.observe(viewLifecycleOwner) { onStateChanged(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.queryHint = getString(R.string.search_hint)
        binding.searchView.isIconifiedByDefault = false
        binding.searchView.setOnQueryTextListener(QueryTextListener {
            viewModel.searchUser(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onStateChanged(state: ScreenState?) = when (state) {
        is ScreenState.StateProgress -> showProgress()
        is ScreenState.UsersLoaded -> setUsers()
        is ScreenState.StateError -> showError(state.throwable)
        else -> {}
    }

    private fun showError(throwable: Throwable) {
        binding.groupErrorWidget.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.GONE
        binding.recyclerUsers.visibility = View.GONE
    }

    private fun setUsers() {
        binding.groupErrorWidget.visibility = View.GONE
        binding.loadingBar.visibility = View.GONE
        binding.recyclerUsers.visibility = View.VISIBLE
    }

    private fun showProgress() {
        binding.groupErrorWidget.visibility = View.GONE
        binding.loadingBar.visibility = View.VISIBLE
        binding.recyclerUsers.visibility = View.GONE
    }

    override fun onNavigateToUserDetails(userShortInfo: UserShortInfo) {
//        viewModel.navigateToUserDetails(userShortInfo)
    }

    override fun retryClicked() {
        viewModel.searchUser(binding.searchView.query.toString())
    }

    override fun setFavoriteUser(user: UserShortInfo) {
        viewModel.setFavoriteUser(user)
    }

    override fun onStop() {
        viewModel.onDestroy()
        super.onStop()
    }
}

interface OnNavigateToUserDetailsListener {
    fun onNavigateToUserDetails(userShortInfo: UserShortInfo)
}

class QueryTextListener(private val action: (String) -> Unit) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null && query.isNotBlank()) action(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean = false
}
