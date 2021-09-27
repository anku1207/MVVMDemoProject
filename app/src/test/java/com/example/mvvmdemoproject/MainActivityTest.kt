package com.example.mvvmdemoproject


import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun withNameOrAgeIsEmpty(){
        val name :String = ""
        val age :Int=0
        val result =MainActivity.validaTextField(name,age)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun withNameOrAgeIsNotEmpty(){
        val name :String = "Test"
        val age :Int=10
        val result =MainActivity.validaTextField(name,age)
        assertThat(result).isEqualTo(true)


    }
}