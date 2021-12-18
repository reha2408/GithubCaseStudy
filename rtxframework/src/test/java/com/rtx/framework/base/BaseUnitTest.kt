package com.rtx.framework.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.model.Statement
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseUnitTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    val overrideSchedulersRule = RxSchedulerOverrideRule()

    @After
    fun tearDownMockito() {
        Mockito.validateMockitoUsage()
    }

    class RxSchedulerOverrideRule : TestRule {

        override fun apply(base: Statement, description: Description?): Statement {
            return object : Statement() {
                @Throws(Throwable::class)
                override fun evaluate() {
                    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
                    try {
                        base.evaluate()
                    } finally {
                        RxJavaPlugins.reset()
                    }
                }
            }
        }
    }
}
