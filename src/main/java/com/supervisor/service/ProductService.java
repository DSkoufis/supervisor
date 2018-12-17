package com.supervisor.service;

import com.supervisor.command.ProductCreateParams;
import com.supervisor.domain.product.Product;

import java.util.List;

public interface ProductService {

	List<Product> getProducts();

	Boolean createProduct(ProductCreateParams params);
}
