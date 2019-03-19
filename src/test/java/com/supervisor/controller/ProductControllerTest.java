package com.supervisor.controller;

import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import com.supervisor.util.builder.ProductBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productServiceMock;

    @Test
    public void indexMethodReturnViewToCorrectFile() throws Exception {
        this.mockMvc.perform(get("/product/"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productList"));
    }

    @Test
    public void indexMethodListensToMultipleURIs() throws Exception {
        final String viewName = "product/productList";

        this.mockMvc.perform(get("/product/"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));

        this.mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }

    @Test
    public void indexReturnsAllProductsWithTheModel() throws Exception {
        final String productName1 = "product 1";
        final String productName2 = "product 2";

        Product product1 = new ProductBuilder().withId(1L).withName(productName1).build();
        Product product2 = new ProductBuilder().withId(2L).withName(productName2).build();

        when(productServiceMock.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        this.mockMvc.perform(get("/product/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("products", hasSize(2)))
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("name", is(productName1))
                        )
                )))
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("name", is(productName2))
                        )
                )));

        verify(productServiceMock, times(1)).getAllProducts();
        verifyNoMoreInteractions(productServiceMock);
    }
}
