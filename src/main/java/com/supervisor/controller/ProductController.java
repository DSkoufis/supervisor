package com.supervisor.controller;

import com.supervisor.command.ProductSaveCommand;
import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import com.supervisor.util.response.ProductCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.supervisor.util.constant.ViewMapping.PRODUCT_VIEW_PATH;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private ProductService productService;

    public ProductController() {}

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView index() {
        ModelAndView model = new ModelAndView(getProductViewName("productsPage"));
        List<Product> products = productService.getAllProducts();
        model.addObject("products", products);
        return model;
    }

    @PostMapping(value = "/save")
    public ModelAndView createProduct(@Valid @ModelAttribute("command") ProductSaveCommand cmd, BindingResult result) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("command", cmd);

        if (result.hasErrors()) {
            return ProductCreationResponse.from(result.getAllErrors()).asModel(getProductViewName("_createProductForm"), modelMap);
        }

        ProductCreationResponse response;
        try {
            Product product = productService.saveProduct(cmd);
            return ProductCreationResponse.from(product).asModel(getProductViewName("_productListItem"));
        } catch (javax.validation.ConstraintViolationException ex) {
            response = ProductCreationResponse.from(ex);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            response = ProductCreationResponse.from("name", "product.Product.name.unique", "product.Product.name.unique");
        }
        return response.asModel(getProductViewName("_createProductForm"), modelMap);
    }

    private String getProductViewName(String viewName) {
        return PRODUCT_VIEW_PATH + viewName;
    }
}
