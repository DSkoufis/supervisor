package com.supervisor.service;

import com.supervisor.command.ProductCreateCommand;
import com.supervisor.dao.ProductDao;
import com.supervisor.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productRepository;

	@Autowired
	public ProductServiceImpl(ProductDao productRepository) {
		this.productRepository = productRepository;
	}

	@Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
	public Product createProduct(ProductCreateCommand cmd) {
        Product product = new Product(cmd.getName());
        product.addVendors(cmd.getVendors());

        return productRepository.save(product);
    }
}
