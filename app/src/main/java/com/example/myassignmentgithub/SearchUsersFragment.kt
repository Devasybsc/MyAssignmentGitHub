package com.example.myassignmentgithub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myassignmentgithub.adapters.UserListAdapter
import com.example.myassignmentgithub.databinding.FragmentSearchUserBinding
import com.example.myassignmentgithub.model.UserShortInfo
import com.example.myassignmentgithub.ui.ScreenState
import com.example.myassignmentgithub.viewmodels.SearchUserListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Fragment to search users.
 */
class SearchUsersFragment : DaggerFragment(), OnNavigateToUserDetailsListener {

    private var _binding: FragmentSearchUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val userListAdapter = UserListAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchUserListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.recyclerUsers.apply {
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager.isSmoothScrollbarEnabled = true
            this.layoutManager = layoutManager
            this.adapter = userListAdapter
        }

        viewModel.searchUser(null)
        viewModel.userList.observe(viewLifecycleOwner) {
            userListAdapter.setItems(it)
        }
        viewModel.screenState.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
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
