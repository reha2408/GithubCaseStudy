package com.reha.casestudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.reha.casestudy.App
import com.reha.casestudy.di.base.AppComponent
import com.reha.casestudy.extension.observeLiveData
import javax.inject.Inject

abstract class BaseFragment<V : BaseViewModel, T : ViewDataBinding> : Fragment() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).component()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected lateinit var binding: T

    protected lateinit var viewModel: V

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.run {
            observeLiveData(progressLiveData) {
                it?.let {
                    when (it) {
                        ResponseSubscriptionStatus.SUBSCRIBED -> lockScreen()
                        ResponseSubscriptionStatus.FINISHED -> unlockScreen()
                    }
                }
            }
        }
    }

    protected fun lockScreen() = progressStatus(View.VISIBLE)

    protected fun unlockScreen() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) {
        with(activity) {
            if (this is BaseActivity) {
                when (viewStatus) {
                    View.VISIBLE -> lockScreen()
                    View.GONE -> unlockScreen()
                }
            }
        }
    }

    protected fun onBackPressed() {
        activity?.onBackPressed()
    }
}