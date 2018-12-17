package com.supervisor.service;

import com.supervisor.command.ProductCreateParams;
import com.supervisor.dao.ProductDao;
import com.supervisor.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Boolean createProduct(ProductCreateParams params) {
        if (params.validate()) {
            Product product = new Product(params.getName());

            if (!params.getVendors().isEmpty()) {
                product.addVendors(params.getVendors());
            }

            product = productRepository.save(product);
            if (product != null) {
                return true;
            }
        }
        return false;
    }
}
