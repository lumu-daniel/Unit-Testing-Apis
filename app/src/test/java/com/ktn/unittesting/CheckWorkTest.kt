package com.ktn.unittesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CheckWorkTest{

    @Test
    fun `when input is 0 or 1, return input`(){
        val result = CheckWork.fib(50)
        assertThat(result).isEqualTo(CheckWork.fib(48)+CheckWork.fib(49))
    }

    @Test
    fun `when one bracket is started and not completed, return false`(){
        val result = CheckWork.checkBraces("(")
        assertThat(result).isFalse()
    }

    @Test
    fun `when one bracket is started and is completed, return true`(){
        val result = CheckWork.checkBraces(")(")
        assertThat(result).isTrue()
    }
}