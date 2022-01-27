package com.sipm.invm.service;

import com.sipm.invm.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IProductService {
     List<Product> getProducts();
     void addProduct(Product product);
     Product updateProduct(Product product);
     void deleteProduct(String ref);
     Optional<Product> getproductByRef(String ref);
     boolean checkIfIdExists(String ref);
}
