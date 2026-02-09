package com.elpis.ecom_proj.controller;


import com.elpis.ecom_proj.model.Product;
import com.elpis.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){

        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK) ;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK) ;

    }
    @PostMapping("/products"
    )
    public ResponseEntity<?> addProduct(
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile   ){
     try{
         System.out.println(product);

         Product newProduct = service.addProduct(product, imageFile);
         return new ResponseEntity<>(newProduct, HttpStatus.CREATED);

     }catch (Exception e){
         return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     }

    }



    @GetMapping("/products/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id){
      Product product =  service.getProductById(id);
      byte[] imageFile = product.getImageData();

      return  ResponseEntity.ok().contentType(MediaType.valueOf((product.getImageType())))
              .body(imageFile);
    }
}

