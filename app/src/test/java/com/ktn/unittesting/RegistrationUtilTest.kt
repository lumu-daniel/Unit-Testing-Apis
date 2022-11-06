package com.ktn.unittesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun `empty username returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Daniel",
            "123",
            "123"
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }


    @Test
    fun `empty password returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Daniel",
            "",
            ""
        )

        assertThat(result).isFalse()
    }


    @Test
    fun `Incorrectly repeated password returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Daniel",
            "123",
            "124"
        )

        assertThat(result).isFalse()
    }



    @Test
    fun `password contains less than 2 digits returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Daniel",
            "da3",
            "da3"
        )

        assertThat(result).isFalse()
    }
}