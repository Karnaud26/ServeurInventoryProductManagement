package com.sipm.invm.service;

import com.sipm.invm.entity.Product;
import com.sipm.invm.exception.ProductAlreadyExistsException;
import com.sipm.invm.exception.ProductNotFoundException;
import com.sipm.invm.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) throws ProductAlreadyExistsException {
        System.out.println("Hello=" + product.getName());
        if (checkIfRefExists(product.getRef())) {
            System.out.println("ProductAlreadyExistsException(\"Product are existing in database\")");
            throw new ProductAlreadyExistsException("Product are existing in database");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException{
        productRepository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException(String.valueOf(product.getId())));
        product.setId(product.getId());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) throws ProductNotFoundException{
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.valueOf(id)));

        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductByNameOrPartName(String name) {
        return findProductByNameOrPartName(new StringBuilder().append("%").append(name).append("%").toString());
    }

    @Override
    public List<Product> findProductByUnitPrice(double unitPrice) {
        return productRepository.findProductByUnitPrice(unitPrice);
    }

    @Override
    public boolean checkIfIdExists(Integer id) {
        return productRepository.checkIfIdExists(id);
    }

    @Override
    public boolean checkIfNameExists(String name) {
        //Product product = productRepository.find
        return productRepository.checkIfNameExists(name);
    }

    @Override
    public Product findProductByRef(String ref) {
        return productRepository.findProductByRef(ref);
    }

    @Override
    public boolean checkIfRefExists(String ref){
        Product product = productRepository.findProductByRef(ref);
        if (product == null)
            return false;
        return true;
    }

    @Override
    public Product findProductById(Integer id) throws ProductNotFoundException{
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.valueOf(id)));
    }
}

