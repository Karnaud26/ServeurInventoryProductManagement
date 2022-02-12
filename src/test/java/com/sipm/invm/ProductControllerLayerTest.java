package com.sipm.invm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipm.invm.controller.ProductController;
import com.sipm.invm.entity.Product;
import com.sipm.invm.exception.ProductNotFoundException;
import com.sipm.invm.service.IProductService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class))
@WebMvcTest(ProductController.class)
public class ProductControllerLayerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    /*@Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }*/

    @MockBean
    private IProductService iProductService;

    @Test
    public void testSaveProduct_whenPostMethod() throws Exception{
        Product product = Product.getInstance();
        if (null == product) {
            throw new ProductNotFoundException();
        }
        //final Product product = new Product("DX10001","Coca-Cola",5,750);
        product.setName("Mango");
        product.setRef("DX10002");
        product.setQty(5);
        product.setUnitprice(750);
        try {
            BDDMockito.given(iProductService.addProduct(product)).willReturn(product);
            mockMvc.perform(MockMvcRequestBuilders.post("/addProduct")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(product))
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isCreated())
                    .andExpect(content().string("Product save Done"))
                    .andExpect(jsonPath(("$.name"), Matchers.is(product.getName())));
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
