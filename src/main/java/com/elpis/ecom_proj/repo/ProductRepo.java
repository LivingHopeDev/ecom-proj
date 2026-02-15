package com.elpis.ecom_proj.repo;

import com.elpis.ecom_proj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// the Integer here represent the primary integer
public interface ProductRepo extends JpaRepository<Product, Integer> {
    //JpQL
    @Query("SELECT p from Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT( '%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT( '%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT( '%', :keyword, '%'))"

    )
    List<Product> searchProducts(String keyword);
}
