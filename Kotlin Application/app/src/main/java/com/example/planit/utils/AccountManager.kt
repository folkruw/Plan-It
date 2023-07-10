package com.example.planit.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planit.dtos.DtoInputAccount
import com.example.planit.dtos.DtoOutputLogin
import com.example.planit.repositories.AccountRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class AccountManager : ViewModel() {
    private val accountRepository = RetrofitHelper
        .create()
        .create(AccountRepository::class.java)

    val mutableLoginAccount : MutableLiveData<DtoInputAccount> = MutableLiveData()

    fun launchLoginAccount(dto: DtoOutputLogin) {
        viewModelScope.launch (CoroutineExceptionHandler
        { _, ex -> Log.e("DEB_ERROR", ex.toString()) }) // Error redirection, do not crash the application

        {
            val account = accountRepository.login(dto)
            mutableLoginAccount.postValue(account)
        }
    }
}