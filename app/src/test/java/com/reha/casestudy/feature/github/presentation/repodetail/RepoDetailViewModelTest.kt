package com.reha.casestudy.feature.github.presentation.repodetail

import android.content.Context
import android.content.SharedPreferences
import com.reha.casestudy.BaseUnitTest
import com.reha.casestudy.feature.github.data.model.Owner
import com.reha.casestudy.feature.github.data.model.Repo
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Spy

class RepoDetailViewModelTest : BaseUnitTest() {

    private val test = "test"
    private val repo = Repo(1, test, test, test, 1, Owner(1, test), true)

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    @Mock
    lateinit var editor: SharedPreferences.Editor

    @InjectMocks
    @Spy
    lateinit var vm: RepoDetailViewModel

    @Test
    fun `verify set repo detail`() {
        // when
        vm.setRepoDetail(repo)

        // then
        assertEquals(vm.repoDetail.get(), repo)
    }

    @Test
    fun `verify repo detail not set`() {
        // then
        assertEquals(vm.repoDetail.get(), null)
    }

    @Test
    fun `verify when favorite clicked`() {
        // given
        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putBoolean(anyString(), anyBoolean())).thenReturn(editor)
        vm.setRepoDetail(repo.apply { isFavorite = true })

        // when
        vm.onFavoriteClicked()

        // then
        assertEquals(vm.repoDetail.get()?.isFavorite, false)
    }

    @Test
    fun `verify when non-favorite clicked`() {
        // given
        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putBoolean(anyString(), anyBoolean())).thenReturn(editor)
        vm.setRepoDetail(repo.apply { isFavorite = false })

        // when
        vm.onFavoriteClicked()

        // then
        assertEquals(vm.repoDetail.get()?.isFavorite, true)
    }
}
