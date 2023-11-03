package com.example.Blog_App.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.Blog_App.payloads.CategoryDTO;
import com.example.Blog_App.service.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerDiffblueTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryServiceImpl categoryServiceImpl;

    /**
     * Method under test: {@link CategoryController#getAllCategories()}
     */
    @Test
    void testGetAllCategories() throws Exception {
        when(categoryServiceImpl.getAllCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/categ");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CategoryController#addCategory(CategoryDTO)}
     */
    @Test
    void testAddCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCatDescription("Cat Description");
        categoryDTO.setCatId(1);
        categoryDTO.setCatTtitle("Dr");
        when(categoryServiceImpl.addCategory(Mockito.<CategoryDTO>any())).thenReturn(categoryDTO);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setCatDescription("Cat Description");
        categoryDTO2.setCatId(1);
        categoryDTO2.setCatTtitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(categoryDTO2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"catId\":1,\"catTtitle\":\"Dr\",\"catDescription\":\"Cat Description\"}"));
    }

    /**
     * Method under test: {@link CategoryController#getCatById(int)}
     */
    @Test
    void testGetCatById() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCatDescription("Cat Description");
        categoryDTO.setCatId(1);
        categoryDTO.setCatTtitle("Dr");
        when(categoryServiceImpl.getCategoryById(anyInt())).thenReturn(categoryDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cat/{id}", 1);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"catId\":1,\"catTtitle\":\"Dr\",\"catDescription\":\"Cat Description\"}"));
    }

    /**
     * Method under test: {@link CategoryController#modifyCategory(CategoryDTO, int)}
     */
    @Test
    void testModifyCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCatDescription("Cat Description");
        categoryDTO.setCatId(1);
        categoryDTO.setCatTtitle("Dr");
        when(categoryServiceImpl.updateCategory(Mockito.<CategoryDTO>any(), anyInt())).thenReturn(categoryDTO);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setCatDescription("Cat Description");
        categoryDTO2.setCatId(1);
        categoryDTO2.setCatTtitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(categoryDTO2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cat/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"catId\":1,\"catTtitle\":\"Dr\",\"catDescription\":\"Cat Description\"}"));
    }

    /**
     * Method under test: {@link CategoryController#removeCategory(int)}
     */
    @Test
    void testRemoveCategory() throws Exception {
        doNothing().when(categoryServiceImpl).deleteCategory(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/cat/{id}", 1);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CategoryController#removeCategory(int)}
     */
    @Test
    void testRemoveCategory2() throws Exception {
        doNothing().when(categoryServiceImpl).deleteCategory(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/cat/{id}", 1);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

