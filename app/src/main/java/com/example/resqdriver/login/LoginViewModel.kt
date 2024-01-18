package com.example.resqdriver.login

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resqdriver.Repository
import com.example.resqdriver.model.domain.EmTransportModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val emTransportData = mutableStateListOf<EmTransportModel>()
    fun getAllEmTransport() {
        repository.getAllEmTransport {
            emTransportData.clear()
            emTransportData.addAll(it)
        }
    }

    fun login(
        emTransportId: String,
        onSuccess:() -> Unit
    ){
        viewModelScope.launch {
            repository.login(emTransportId = emTransportId)
            delay(1500)
            onSuccess()
        }
    }

    init {
        getAllEmTransport()
    }
}
