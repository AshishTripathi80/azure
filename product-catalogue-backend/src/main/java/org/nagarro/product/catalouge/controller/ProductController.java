package org.nagarro.product.catalouge.controller;


import org.nagarro.product.catalouge.exception.ProductNotFoundException;
import org.nagarro.product.catalouge.repository.ProductRepo;
import org.nagarro.product.catalouge.model.Availability;
import org.nagarro.product.catalouge.model.Product;
import org.nagarro.product.catalouge.repository.AvailableRepo;
import org.nagarro.product.catalouge.responsemodel.AvailableResponse;
import org.nagarro.product.catalouge.responsemodel.DetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/product-service")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;


    @Autowired
    private AvailableRepo availableRepo;

    @GetMapping("/welcome")
    public String wish(){
        return "Welcome to azure";
    }

    @PostMapping("/save/product")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        productRepo.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(product.getCode()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/products")
    public ResponseEntity<?> showProducts() {
        List<Product> products = productRepo.findAll();
        if (products != null) return ResponseEntity.ok(products);
        else throw new ProductNotFoundException("No product found ");
    }

    @GetMapping("/search/{name}/{code}/{brand}")
    public ResponseEntity<?> searchByFilter(@PathVariable String name, @PathVariable String code, @PathVariable String brand) {
        List<Product> products = productRepo.findByNameOrCodeOrBrand(name, code, brand);
        if (products == null) {
            throw new ProductNotFoundException("No product found");
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{code}")
    public ResponseEntity<?> findProductDetails(@PathVariable String code) {
        Optional<Product> product = productRepo.findById(code);
        if (product != null) return ResponseEntity.ok(product);
        else throw new ProductNotFoundException("No product found");
    }

    @GetMapping("/search/details/{code}")
    public ResponseEntity<?> findDetails(@PathVariable String code) {
        String details = productRepo.getDetails(code);
        return ResponseEntity.ok(new DetailResponse(details));
    }

    @GetMapping("/check/{code}/{pinCode}")
    public ResponseEntity<?> checkAvailability(@PathVariable String code, @PathVariable Long pinCode) {
        Availability availability = availableRepo.findByCodeAndPinCode(code, pinCode);
        if (availability != null) {
            return ResponseEntity.ok(new AvailableResponse(true));
        }
        return ResponseEntity.ok(new AvailableResponse(false));
    }
}
