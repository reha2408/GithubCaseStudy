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

abstract class BaseFragment<V : BaseViewModel, T : ViewDataBinding> : Fragment() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBRViewModelId(): Int

    protected abstract fun getViewModelClass(): Class<V>

    protected lateinit var binding: T

    protected lateinit var viewModel: V

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
        viewModel.run {
            observeLiveData(progressLiveData) {
                it?.let {
                    when (it) {
                        ResponseSubscriptionStatus.SUBSCRIBED -> lockScreen()
                        ResponseSubscriptionStatus.FINISHED -> unlockScreen()
                    }
                }
            }
            observeLiveData(uiMessageLiveData) {
                showError(it)
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

    private fun provideViewModel() = ViewModelProvider(this).get(getViewModelClass())
}
