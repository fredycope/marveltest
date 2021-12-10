package com.example.marveldemo

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marveldemo.domain.usecase.GetMarvelUseCase
import com.example.marveldemo.ui.listfragment.ListViewModel
import com.example.marveldemo.utils.Constants.API_KEY
import com.example.marveldemo.utils.Constants.HASH
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
@Config(application = HiltTestApplication::class, sdk = [30])
@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
class ExampleUnitTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var viewModel: ListViewModel
    @Inject
    lateinit var getMarvelUseCase: GetMarvelUseCase

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun getMarvelUseCase(){
        testCoroutineRule.runBlockingTest {
            viewModel = ListViewModel(getMarvelUseCase)
            viewModel.onCreate(API_KEY, HASH)
            assertNotNull(viewModel.marvel)
            assertEquals(false, viewModel.marvel.hasObservers())
        }
    }


}