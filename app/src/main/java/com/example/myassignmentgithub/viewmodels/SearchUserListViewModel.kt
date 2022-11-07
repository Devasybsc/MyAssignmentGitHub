package com.example.myassignmentgithub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myassignmentgithub.datasource.PreferenceProvider
import com.example.myassignmentgithub.model.UserShortInfo
import com.example.myassignmentgithub.repositories.UserRepository
import com.example.myassignmentgithub.ui.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchUserListViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val preferenceProvider: PreferenceProvider
) : ViewModel() {

    val data: LiveData<List<SearchUserViewModel>>
        get() = _data
    private val _data = MutableLiveData<List<SearchUserViewModel>>(emptyList())

    val screenState: LiveData<ScreenState> = MutableLiveData()

    var savedIds: ArrayList<Long> = arrayListOf()

    init {
        savedIds = preferenceProvider.getSavedIds()
    }

    /**
     * Get users by query, if exists local data - do not perform API call
     */
    fun searchUser(query: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (query.isNullOrBlank()) {
                    _data.postValue(userRepository.getAllUsers().map {
                        if (savedIds.contains(it.id)) {
                            it.setFavorite(true)
                        } else {
                            it.setFavorite(false)
                        }
                        SearchUserViewModel(it)
                    })
                } else {
                    _data.postValue(userRepository.searchUser(query).map {
                        if (savedIds.contains(it.id)) {
                            it.setFavorite(true)
                        } else {
                            it.setFavorite(false)
                        }
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

    fun setFavoriteUser(user: UserShortInfo) {
        if (savedIds.contains(user.id)) {
            savedIds.remove(user.id)
        } else {
            savedIds.add(user.id)
        }
        _data.value?.first {
            it.userShortInfo.id == user.id
        }?.userShortInfo?.apply {
            setFavorite(
                isFavorite.let {
                    it.value?.not() ?: false
                }
            )
        }
    }

    fun onDestroy() {
        preferenceProvider.saveUserId(savedIds)
    }
}
