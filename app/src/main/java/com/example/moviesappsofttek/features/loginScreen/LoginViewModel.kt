package com.example.moviesappsofttek.features.loginScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappsofttek.core.utils.ResourceEvent
import com.example.moviesappsofttek.core.components.TextFieldState
import com.example.moviesappsofttek.core.states.LoadState
import com.example.moviesappsofttek.core.states.UiState
import com.example.moviesappsofttek.domain.usecase.accounts.AccountUseCase
import com.example.moviesappsofttek.features.navigationCompose.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountUseCase: AccountUseCase
) : ViewModel() {

    //Se inicializa el estado de la pantalla de login
    private var _loginState = mutableStateOf(LoadState())
    val loginState: State<LoadState> = _loginState

    //Se inicializa el evento de la pantalla de login
    private val _eventFlow = MutableSharedFlow<UiState>()
    val eventFlow = _eventFlow.asSharedFlow()

    //Se inicializa el estado del email
    private val _usernameState = mutableStateOf(TextFieldState())
    val usernameState: State<TextFieldState> = _usernameState
    fun setUsername(value: String) {
        _usernameState.value = usernameState.value.copy(text = value)
    }

    //Se inicializa el estado del password
    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState
    fun setPassword(value: String) {
        _passwordState.value = passwordState.value.copy(text = value)
    }

    //Metodo que se encarga de realizar el login
    fun loginUser() {
        //Corutina que realiza la petición de login
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = true)
            //Se realiza la petición de login con el email y el password al useCase
            val loginResult = accountUseCase(
                id = 1,
                username = usernameState.value.text,
                password = passwordState.value.text
            )
            if (loginResult.userError != null) {
                _usernameState.value = usernameState.value.copy(error = loginResult.userError)
            }
            if (loginResult.passwordError != null) {
                _passwordState.value = passwordState.value.copy(error = loginResult.passwordError)
            }
            delay(3000)
            _loginState.value = loginState.value.copy(isLoading = false)
            //Se valida el resultado de la petición
            when (loginResult.result) {
                is ResourceEvent.Success -> {
                    _eventFlow.emit(UiState.NavigateEvent(AppScreens.HomeScreen.route))

                }

                is ResourceEvent.Error -> {
                    //En caso de que la petición no sea exitosa se muestra un mensaje de error
                    _eventFlow.emit(UiState.SnackbarEvent(loginResult.result.message ?: "Error!"))
                }

                else -> {
                    //Nothing
                }
            }
        }
    }

    fun saveUser() {
        //Corutina que realiza la petición de login
        viewModelScope.launch {
            accountUseCase.saveAccountToLocal(
                username = "Admin",
                password = "Password*123."
            )
        }
    }
}