package com.elpis.ecom_proj.repo;

import com.elpis.ecom_proj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// the Integer here represent the primary integer
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
