package com.rtx.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rtx.framework.extension.observeLiveData
import com.rtx.framework.extension.showError

@Suppress("TooManyFunctions")
abstract class BaseFragment<V : BaseViewModel, T : ViewDataBinding> : Fragment() {

    lateinit var binding: T

    lateinit var viewModel: V

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBRViewModelId(): Int

    abstract fun getViewModelClass(): Class<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.setVariable(getBRViewModelId(), viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBaseViewModel()
    }

    fun observeBaseViewModel() {
        viewModel.run {
            observeLiveData(progressLiveData) {
                showProgress(it)
            }
            observeLiveData(uiMessageLiveData) {
                showError(it)
            }
        }
    }

    fun showProgress(status: ResponseSubscriptionStatus) {
        when (status) {
            ResponseSubscriptionStatus.SUBSCRIBED -> lockScreen()
            ResponseSubscriptionStatus.FINISHED -> unlockScreen()
        }
    }

    fun lockScreen() = (activity as? BaseActivity)?.lockScreen()

    fun unlockScreen() = (activity as? BaseActivity)?.unlockScreen()

    fun onBackPressed() {
        activity?.onBackPressed()
    }

    private fun provideViewModel() = ViewModelProvider(this).get(getViewModelClass())
}
