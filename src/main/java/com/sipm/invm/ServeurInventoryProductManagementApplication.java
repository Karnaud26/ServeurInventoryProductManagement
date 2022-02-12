package com.sipm.invm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class ServeurInventoryProductManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServeurInventoryProductManagementApplication.class, args);
        /* ctx = new ApplicationContext();
        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        productRepository.save(new Product("100A","Livre",50,25));
        productRepository.save(new Product ("200B","Cahier",3,25.5));
        productRepository.save(new Product ("300C","Stylo",10,25.8));*/
    }

}
