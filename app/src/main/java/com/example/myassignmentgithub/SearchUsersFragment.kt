package com.example.myassignmentgithub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myassignmentgithub.databinding.FragmentSearchUserBinding
import com.example.myassignmentgithub.network.model.UserShortInfo
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

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchUserListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

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
