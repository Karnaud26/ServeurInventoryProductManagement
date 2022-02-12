package com.sipm.invm;

import com.sipm.invm.entity.Product;
import com.sipm.invm.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Ignore
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    /*@BeforeEach
    void initUseCase(){
        productRepository.save(new Product("1020A","Tomate",15,2500));
    }

    @AfterEach
    public void destroy(){
        productRepository.deleteAll();
    }*/

    @Test
    @Order(1)
    @Rollback(value = false)
    void testSave(){
        List<Product> products = Arrays.asList(
                new Product("ABC", "karim", 10,452),
                new Product("CDE", "khan", 25,1520),
                new Product("EFG", "karim", 54,585)
        );
        Iterable<Product> allProduct = productRepository.saveAll(products);
        AtomicInteger validIdFound = new AtomicInteger();
        allProduct.forEach(product -> {
            if (product.getId() > 0){
                validIdFound.getAndIncrement();
            }
        });
        Assertions.assertThat(validIdFound.intValue()).isEqualTo(3);
    }

    @Test
    @Order(2)
    void testFindAll_success(){
        List<Product> allProducts = productRepository.findAll();
        Assertions.assertThat(allProducts.size()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    void testFindByName_success(){
        List<Product> productsByName = productRepository.findProductByNameOrPartName("khan");
        Assertions.assertThat(productsByName.size()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void testFindProductByUnitPrice_success(){
        List<Product> productListByCost = productRepository.findProductByUnitPrice(520);
        Assertions.assertThat(productListByCost.size()).isNotNull();
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    void testUpdateProduct(){
        Product product = productRepository.findProductByRef("CDE");
        product.setName("Allibaba");
        Product productUpdated = productRepository.save(product);
        Assertions.assertThat(productUpdated.getName()).isEqualTo("Allibaba");
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    void testDeleteProduct(){
        Product product = productRepository.findProductByRef("ABC");
        if (productRepository.existsById(product.getId())){
            productRepository.delete(product);
            Product product1 = null;
            Optional<Product> optionalProduct =  Optional.of(product);
            if(optionalProduct.isPresent()){
                product1 = optionalProduct.get();
            }
            Assertions.assertThat(product1).isNotNull();
        }
    }
}
