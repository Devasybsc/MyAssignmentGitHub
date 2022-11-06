package com.example.myassignmentgithub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myassignmentgithub.databinding.BindableItemViewModel
import com.example.myassignmentgithub.repositories.UserRepository
import com.example.myassignmentgithub.ui.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchUserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val data: LiveData<List<SearchUserViewModel>>
        get() = _data
    private val _data = MutableLiveData<List<SearchUserViewModel>>(emptyList())

    val screenState: LiveData<ScreenState> = MutableLiveData()

    /**
     * Get users by query, if exists local data - do not perform API call
     */
    fun searchUser(query: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (query.isNullOrBlank() ) {
                    _data.postValue(userRepository.getAllUsers().map {
                        SearchUserViewModel(it)
                    })
                } else {
                    _data.postValue(userRepository.searchUser(query).map {
                        SearchUserViewModel(it)
                    })
                }

                (screenState as MutableLiveData).postValue(ScreenState.UsersLoaded)

            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                (screenState as MutableLiveData).postValue(ScreenState.StateError(throwable))
            }
        }
    }
}
