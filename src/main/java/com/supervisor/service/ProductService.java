package com.supervisor.service;

import com.supervisor.command.ProductSaveCommand;
import com.supervisor.domain.product.Product;

import java.util.List;

public interface ProductService {

	List<Product> getAllProducts();

	Product saveProduct(ProductSaveCommand params);
}
