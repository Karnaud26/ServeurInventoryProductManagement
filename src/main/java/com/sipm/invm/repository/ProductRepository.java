package com.sipm.invm.repository;

import com.sipm.invm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findProductByRef(String ref);
    boolean checkIfIdExists(int id);
    List<Product> findProductByNameOrPartName(String name);
    List<Product> findProductByUnitPrice(double unitPrice);
    boolean checkIfNameExists(String name);
}
