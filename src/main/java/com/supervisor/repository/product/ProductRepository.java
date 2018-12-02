package com.supervisor.repository.product;

import com.supervisor.domain.product.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
