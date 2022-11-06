package com.ktn.unittesting.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.ktn.unittesting.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.shoppingDao()
    }

    @After
    fun tearDown(){
        database.close()
    }


    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name",1,1f,"url",id =1)
        dao.insertShoppingItem(shoppingItem)
        val allShoppingItems = dao.observerAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name",1,1f,"url",id =1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)
        val allShoppingItems = dao.observerAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPriceSum() = runBlockingTest {

        val shoppingItem1 = ShoppingItem("name",2,1.1f,"url",id =1)
        val shoppingItem2 = ShoppingItem("name",3,10f,"url",id =2)
        val shoppingItem3 = ShoppingItem("name",4,5.5f,"url",id =3)
        val shoppingItem4 = ShoppingItem("name",5,400f,"url",id =5)
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)
        dao.insertShoppingItem(shoppingItem4)
        val totalPrice = dao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPrice).isEqualTo(2*1.1f + 3*10f + 4*5.5f + 5*400f)
    }

}