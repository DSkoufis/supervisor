package com.supervisor.controller;

import com.supervisor.command.ProductSaveCommand;
import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.supervisor.util.constant.ControllerMapping.PRODUCT_CONTROLLER_ROOT;
import static com.supervisor.util.constant.ViewMapping.PRODUCT_VIEW_PATH;

@Controller
@RequestMapping(value = PRODUCT_CONTROLLER_ROOT)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView index() {
        ModelAndView model = new ModelAndView(PRODUCT_VIEW_PATH + "productList");
        List<Product> products = productService.getAllProducts();
        model.addObject("products", products);
        return model;
    }

    @PostMapping(value = "save")
    public void createProduct(@RequestBody ProductSaveCommand cmd) {
        // TODO Could this return the product? think so
        productService.saveProduct(cmd);
    }
}
