package org.nagarro.product.catalouge.repository;


import org.nagarro.product.catalouge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {

    List<Product> findByNameOrCodeOrBrand(String name, String code , String brand);

    @Query("select details from Product where code=?1")
    String getDetails(String code);
}
