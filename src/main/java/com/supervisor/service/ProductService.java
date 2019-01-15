package com.supervisor.service;

import com.supervisor.command.ProductCreateCommand;
import com.supervisor.domain.product.Product;

import java.util.List;

public interface ProductService {

	List<Product> getAllProducts();

	Product createProduct(ProductCreateCommand params);
}
