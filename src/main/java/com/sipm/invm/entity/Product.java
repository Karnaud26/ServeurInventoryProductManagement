package com.sipm.invm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

//@Entity
//@Table(name = "Product")
public class Product implements Serializable {
    private String ref;
    private String name;
    private double qty;
    private double unitprice;

    private static Product instance;

    public static Product getInstance(String ref, String name, double qty, double unitprice){
        if (instance == null)
            instance = new Product(ref,name,qty,unitprice);
        return instance;
    }

    public static Product getInstance(){
        if (instance == null)
            instance = new Product();
        return instance;
    }

    private Product(String ref, String name, double qty, double unitprice) {
        this.ref = ref;
        this.name = name;
        this.qty = qty;
        this.unitprice = unitprice;
    }

    private Product() {
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

    public double getUnitprice() {
        return unitprice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getRef() == product.getRef();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRef());
    }
}
