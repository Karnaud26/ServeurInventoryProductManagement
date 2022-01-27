package com.sipm.invm.controller;

import com.sipm.invm.entity.Product;
import com.sipm.invm.service.IProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/product")
@CrossOrigin
public class ProductController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService iProductService;

    @GetMapping()
    public List<Product> getProducts(){
        return iProductService.getProducts();
    }
    /**
     * Recherche un produit dans la base de données en fonction de son numéro de ref. Si le produit n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param ref
     * @return
     */
     /*@GetMapping("/{ref}")
    public ResponseEntity<Product> getProductByRef(String ref){
       Product product = iProductService.getproductByRef(ref);
        return iProductService.getproductByRef(ref);

    }*/
    /**
     * Recherche un produit dans la base de données en fonction de son numéro de ref. Si le produit n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param productDTORequest
     * @return
     */
    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product productDTORequest){
        iProductService.addProduct(productDTORequest);
    }

    /**
     * Met à jour les données d'un pruduit dans la base de données . Si le produit n'est pas retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
     * @param product
     * @return
     */
    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        if(!iProductService.checkIfIdExists(product.getRef()))
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        Product productRequest = this.mapProductDTOToProduct(product);
        Product ProductResponse =  iProductService.updateProduct(productRequest);
        if (!ProductResponse.toString().isEmpty() || ProductResponse != null){
            return new ResponseEntity<Product>(ProductResponse,HttpStatus.OK);
        }
        return  new ResponseEntity<Product>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Supprime un produit dans la base de données. Si le produit n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param ref
     * @return
     */
    @DeleteMapping("/deleteCustomer/{ref}")
    public ResponseEntity<String> deleteProduct(@PathVariable String ref){
        iProductService.deleteProduct(ref);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    /**
     * Transforme un POJO ProductDTO en une entity Product
     *
     * @param productDTO
     * @return
     */
    private Product mapProductDTOToProduct(Product productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDTO,Product.class);
        return product;
    }

}
