package com.supervisor.controller;

import com.supervisor.dao.ProductDao;
import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"index", "/"})
    public String index(Model model) {
    	List<Product> productList = productService.getProducts();
    	model.addAttribute("products", productList);
        return "";
    }
}
