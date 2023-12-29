package com.example.movieapplicationclone.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel : ViewModel() {
    protected val state = MutableStateFlow<UIState>(UIState.Init)
    val mState: StateFlow<UIState> get() = state

    protected fun showLoading() {
        state.value = UIState.IsLoading(true)
    }

    protected fun hideLoading() {
        state.value = UIState.IsLoading(false)
    }

    protected fun showToast(message: String) {
        state.value = UIState.ShowToast(message)
    }

    protected fun showErrorMessage(message: String) {
        state.value = UIState.Error(message)
    }
}
    sealed class UIState {
        object Init : UIState()
        data class IsLoading(val isLoading: Boolean) : UIState()
        data class ShowToast(val message: String) : UIState()

        //data class Success(val Entity: WrappedResponse<T>) : UIState()
        data class Error(val rawResponse: String) : UIState()
    }