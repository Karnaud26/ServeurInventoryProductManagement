package com.sipm.invm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "Product.findProductByNameOrPartName",
                query="SELECT p FROM Product p WHERE p.name = :name"),
        @NamedQuery(name = "Product.findProductByUnitPrice",
                query = "Select p From Product p where p.unitPrice >= :unitPrice"),
        @NamedQuery(name = "Product.findProductByRef",
                query = "Select p from Product p where p.ref = :ref"),
        @NamedQuery(name = "Product.checkIfIdExists",
                query = "Select p from Product p where p.id = :id"),
        @NamedQuery(name = "Product.checkIfNameExists",
                query = "Select p From Product p where p.name = :name")
})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, length = 100)
    private String ref;
    @Column(nullable = false, length = 255)
    private String name;
    @Column
    private double qty;
    @Column
    private double unitPrice;

    private static Product instance;

    public static Product getInstance(String ref, String name, double qty, double unitPrice){
        if (instance == null)
            instance = new Product(ref,name,qty,unitPrice);
        return instance;
    }

    public static Product getInstance(){
        if (instance == null)
            instance = new Product();
        return instance;
    }

    public Product(String ref, String name, double qty, double unitPrice) {
        this.ref = ref;
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    private Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

    public double getQty() {
        return qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public void setUnitprice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId() == product.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
