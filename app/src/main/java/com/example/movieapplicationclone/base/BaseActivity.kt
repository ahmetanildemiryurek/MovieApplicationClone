package com.example.movieapplicationclone.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.movieapplicationclone.utils.ConnectionLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

abstract class BaseActivity <VB : ViewBinding, VM : ViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : AppCompatActivity() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!
    protected lateinit var connectionLiveData: ConnectionLiveData

    /* onCreateView tamamlanmadan önce yapılması gereken işlemler burada yapılmaktadır. */
    open fun onBeforeOnCreate(savedInstanceState: Bundle?) {
    }

    /* onViewCreated tan sonra çalışmaktadır. */
    abstract fun setupUI()

    /* Observing states */
    protected open fun observe() {
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(_binding!!.root)
        connectionLiveData = ConnectionLiveData(this)
        observe()
        setupUI()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    /* Generic collect flow */
    fun <T> Flow<T>.collectLaunchOnLifecycleScope(execute: suspend (data: T) -> Unit) {
        lifecycleScope.launchWhenCreated {
            collectLatest {
                execute(it)
            }
        }
    }

    // region handle state
    protected open fun handleStateChange(state: UIState) {
        when (state) {
            is UIState.Init -> Unit
            is UIState.ShowToast -> showToast(state.message)
            is UIState.IsLoading -> handleLoading(state.isLoading)
            is UIState.Error -> TODO()
            else -> {
                println("Error")
            }
        }
    }

    protected open fun handleErrorLogin(response: String) {
        // TODO : Show toast message for error
    }

    protected open fun showToast(response: String) {
        // TODO : Show toast message for error
    }

    protected open fun handleLoading(isLoading: Boolean) {
        // TODO: Loading state
    }

    protected open fun handleSuccessLogin(loginEntity: String) {
        // TODO: Success State
    }
    //endregion handle state

    /* Memory leaky i engellemek için destroy durumunda _binding sıfırlanmalıdır. */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}