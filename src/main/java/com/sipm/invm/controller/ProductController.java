package com.sipm.invm.controller;

import com.sipm.invm.entity.Product;
import com.sipm.invm.exception.ProductAlreadyExistsException;
import com.sipm.invm.exception.ProductNotFoundException;
import com.sipm.invm.service.IProductService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = {"ProductController"})
@CrossOrigin
public class ProductController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final IProductService iProductService;

    public ProductController(IProductService iProductService){
        this.iProductService =  iProductService;
    }

    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok().body(iProductService.getProducts());
    }
    /**
     * Recherche un produit dans la base de données en fonction de son numéro de ref. Si le produit n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param ref
     * @return
     */
    /*@GetMapping("/{ref}")
    public ResponseEntity<Product> getProductByRef(String ref){
       Product product = iProductService.(ref);
        return iProductService.getproductByRef(ref);

    }*/

    /**
     * Recherche un produit dans la base de données en fonction de son numéro de ref. Si le produit n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param id
     * @return
     */
    @GetMapping("/productdetails/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(iProductService.findProductById(id));
        }catch (ProductNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }


    }

    /**
     * Recherche un produit dans la base de données en fonction de son numéro de ref. Si le produit n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param product
     * @return
     */
    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try{
            Product product1 = iProductService.addProduct(product);
            /*URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(product1.getId())
                    .toUri();*/
            return new ResponseEntity<Product>(product1,HttpStatus.CREATED);

        }catch (ProductAlreadyExistsException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    /**
     * Met à jour les données d'un pruduit dans la base de données . Si le produit n'est pas retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
     * @param product
     * @return
     */
    @PutMapping("/updateproduct")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        try{
            return ResponseEntity.ok().body(iProductService.updateProduct(product));
        }catch (ProductNotFoundException ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Supprime un produit dans la base de données. Si le produit n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param id
     * @return
     */
    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws ProductNotFoundException{
        iProductService.deleteProduct(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
