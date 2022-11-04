package com.example.myassignmentgithub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myassignmentgithub.model.UserShortInfo
import com.example.myassignmentgithub.repositories.UserRepository
import com.example.myassignmentgithub.ui.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchUserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val userList: LiveData<List<UserShortInfo>> = MutableLiveData()
    val screenState: LiveData<ScreenState> = MutableLiveData()

    /**
     * Get users by query, if exists local data - do not perform API call
     */
    fun searchUser(query: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if(query != null) {
                    userRepository.searchUser(query).let {
                        (userList as MutableLiveData).postValue(it)
                    }
                }else{
                    userRepository.getAllUsers().let {
                        (userList as MutableLiveData).postValue(it)
                    }
                }

                (screenState as MutableLiveData).postValue(ScreenState.UsersLoaded)

            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                (screenState as MutableLiveData).value = ScreenState.StateError(throwable)
            }
        }
    }
}
