package com.sipm.invm.service;

import com.sipm.invm.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
//@Transactional
public class ProductMokServiceImpl implements IProductService {

    private List<Product> products;
    public ProductMokServiceImpl(){
        products = new ArrayList<>();
        products.add(new Product ("100A","Livre",50,25));
        products.add(new Product ("200B","Cahier",3,25.5));
        products.add(new Product ("300C","Stylo",10,25.8));
    }
    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public Product updateProduct(Product product) {
        products.remove(product);
        products.add(product);
        //return products.add(product);
        return product;
    }

    @Override
    public void deleteProduct(String ref) {
        Product product = Product.getInstance();
        product.setRef(ref);
        products.remove(product);
    }

    @Override
    public Optional<Product> getproductByRef(final String ref) {
        return products.stream().filter(row -> row.getRef().equals(ref)).findAny();
    }

    @Override
    public boolean checkIfIdExists(String ref) {
        return (products.stream().filter(row -> row.getRef().equalsIgnoreCase(ref) && row.getRef().contains(ref)).count() == 0);
    }
}
