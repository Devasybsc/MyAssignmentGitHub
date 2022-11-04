package com.example.myassignmentgithub.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myassignmentgithub.network.model.UserShortInfo
import com.example.myassignmentgithub.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SearchUserListViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val compositeDisposable: CompositeDisposable
)  : ViewModel(){

    val userList: LiveData<List<UserShortInfo>> = MutableLiveData()

    /**
     * Get users by query, if exists local data - do not perform API call
     */
    fun searchUser(query: String) {
        userRepository.searchUser(query)
            .doOnSubscribe { disposable ->
                compositeDisposable.add(disposable)
            }
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulersAndroidSchedulers.mainThread())
            .subscribe(
                { users: List<UserShortInfo> ->
                    (userList as MutableLiveData).value = users
                },
                {
                }
            )
    }
}
