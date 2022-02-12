package com.sipm.invm;

import com.sipm.invm.entity.Product;
import com.sipm.invm.exception.ProductNotFoundException;
import com.sipm.invm.repository.ProductRepository;
import com.sipm.invm.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testAddProduct_Success() throws ProductNotFoundException {
        Product product = Product.getInstance();
        if (null == product) {
            throw new ProductNotFoundException();
        }
        //final Product product = new Product("DX10001","Coca-Cola",5,750);
        product.setName("Coca-Cola");
        product.setRef("DX10001");
        product.setQty(5);
        product.setUnitprice(750);

        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(product);

        //Product saveProduct = productService.addProduct(product);
        //Assertions.assertThat(saveProduct.getName()).isSameAs(product.getName());
        verify(productRepository).save(product);
    }

    @Test
    //@Order(2)
    public void shouldReturnFindAll(){
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product("DXD101","Fanta",52,4500));
        productList.add(new Product("DXD102","Sprite",50,4200));
        productList.add(new Product("DXD103","Ananas",12,700));

        given(productRepository.findAll()).willReturn(productList);
        List<Product> expected = productService.getProducts();
        expected.stream().forEach(product -> System.out.println(product.getName()));
        //assertEquals(expected,productList);
        Assertions.assertThat(expected.size()).isGreaterThan(0);
    }
}
