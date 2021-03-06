package com.example.zivame_assignment.ui.gadgetlist

import com.example.zivame_assignment.ui.gadgetlist.model.GadgetListResponse
import com.example.zivame_assignment.ui.gadgetlist.model.ProductModel
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GadgetListModelTest {

    lateinit var responseModel: GadgetListResponse
    lateinit var productModel: ProductModel

    @Before
    fun setUp() {
        val mockResponse = "{   \"products\": [     {       \"name\": \"OnePlus 6 (Mirror Black 6GB RAM + 64GB memory)\",       \"price\": \"34999\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/41DZ309iN9L._AC_US160_.jpg\",       \"rating\": 4     },     {       \"name\": \"Nokia 105 (Black)\",       \"price\": \"999\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/41gYdatbC4L._AC_US160_.jpg\",       \"rating\": 4     },     {       \"name\": \"Samsung On7 Pro (Gold)\",       \"price\": \"7990\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/41INpKtZV-L._AC_US160_.jpg\",       \"rating\": 3     },     {       \"name\": \"Moto G5s Plus (Lunar Grey, 64GB)\",       \"price\": \"11999\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/51dO+9RvvlL._AC_US160_.jpg\",       \"rating\": 4     },     {       \"name\": \"InFocus Vision 3 (Midnight Black, 18:9 FullVision Display)\",       \"price\": \"7981\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/41NF6Om3xLL._AC_US160_.jpg\",       \"rating\": 2     },     {       \"name\": \"OnePlus 6 (Red, 8GB RAM + 128GB Memory)\",       \"price\": \"39999\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/41++HzstqcL._AC_US160_.jpg\",       \"rating\": 3     },     {       \"name\": \"Redmi 6 Pro (Gold, 32GB)\",       \"price\": \"10999\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/41OYBBIteNL._AC_US160_.jpg\",       \"rating\": 3     },     {       \"name\": \"Nokia 105 (Dual SIM, Black)\",       \"price\": \"1149\",       \"image_url\": \"https://images-na.ssl-images-amazon.com/images/I/51wrcikRDcL._SL1126_.jpg\",       \"rating\": 5     },     {       \"name\": \"OnePlus 6 (Mirror Black, 8GB RAM + 128GB memory)\",       \"price\": \"39999\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/41DZ309iN9L._AC_US160_.jpg\",       \"rating\": 4     },     {       \"name\": \"Lenovo K8 Note XT 1902 (Venom Black)\",       \"price\": \"9600\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/51uPEbFR5HL._AC_US160_.jpg\",       \"rating\": 4     },     {       \"name\": \"Honor 7X (Blue, 4GB RAM + 32GB Memory)\",       \"price\": \"11999\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/41+XaJSYp-L._AC_US160_.jpg\",       \"rating\": 4     },     {       \"name\": \"Huawei Nova 3i (Black, 4GB RAM + 128GB Memory)\",       \"price\": \"20900\",       \"image_url\": \"https://images-eu.ssl-images-amazon.com/images/I/41wZM6JOM6L._AC_US160_.jpg\",       \"rating\": 4     }   ] }"

        responseModel = Gson().fromJson(mockResponse, GadgetListResponse::class.java)
        productModel = responseModel.productList!!.get(0)
    }

    @Test
    fun testGadgetListRespomse() {
        assertNotNull(responseModel)
        assertNotNull(responseModel.productList)
        assertEquals(12, responseModel.productList?.size)
    }

    @Test
    fun testProductModel() {
        assertNotNull(productModel)
        assertEquals("OnePlus 6 (Mirror Black 6GB RAM + 64GB memory)", productModel.name)
        assertEquals("34999", productModel.price)
        assertEquals("https://images-eu.ssl-images-amazon.com/images/I/41DZ309iN9L._AC_US160_.jpg", productModel.imageUrl)
        assertEquals(4.0f, productModel.rating)
    }
}