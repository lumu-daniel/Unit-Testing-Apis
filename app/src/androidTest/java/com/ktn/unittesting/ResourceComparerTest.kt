package com.ktn.unittesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ResourceComparerTest{
    
    private lateinit var resourceComparer:ResourceComparer

    @Before
    fun setUp(){
        resourceComparer = ResourceComparer()
    }
    
    @Test
    fun stringResourceSameAsGivenString_returnTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "Unit Testing")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentFromGivenString_returnFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context, R.string.app_name, "Unit Tsting")
        assertThat(result).isFalse()
    }
}