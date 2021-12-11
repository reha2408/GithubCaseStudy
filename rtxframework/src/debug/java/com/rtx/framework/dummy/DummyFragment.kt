package com.rtx.framework.dummy

import com.rtx.framework.BR
import com.rtx.framework.R
import com.rtx.framework.base.BaseFragment
import com.rtx.framework.databinding.DummyFragmentBinding

class DummyFragment: BaseFragment<DummyViewModel, DummyFragmentBinding>() {


    override fun getLayoutId() = R.layout.dummy_fragment

    override fun getBRViewModelId() = BR.viewModel

    override fun getViewModelClass() = DummyViewModel::class.java
}