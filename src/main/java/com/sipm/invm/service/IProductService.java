package com.sipm.invm.service;

import com.sipm.invm.entity.Product;
import com.sipm.invm.exception.ProductAlreadyExistsException;
import com.sipm.invm.exception.ProductNotFoundException;

import java.util.List;

public interface IProductService {
     List<Product> getProducts();
     Product addProduct(Product product) throws ProductAlreadyExistsException;
     Product updateProduct(Product product) throws ProductNotFoundException;
     void deleteProduct(Integer id) throws ProductNotFoundException;
     List<Product> findProductByNameOrPartName(String name);
     List<Product> findProductByUnitPrice(double unitPrice);
     boolean checkIfIdExists(Integer id);
     boolean checkIfNameExists(String name);
     boolean checkIfRefExists(String ref);
     Product findProductByRef (String ref);
     Product findProductById(Integer id) throws ProductNotFoundException;
}
