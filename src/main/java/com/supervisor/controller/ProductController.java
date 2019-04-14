package com.supervisor.controller;

import com.supervisor.command.ProductSaveCommand;
import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import com.supervisor.util.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static com.supervisor.util.constant.ViewMapping.PRODUCT_VIEW_PATH;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private ProductService productService;

    public ProductController() { }

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
        ProductResponse response = new ProductResponse();

        if (result.hasErrors()) {
            return response.withErrors(result.getAllErrors()).andModelAttribute("command", cmd)
                    .asModel(getProductViewName("_createProductForm"));
        }

        try {
            Product product = productService.saveProduct(cmd);
            return response.withSuccess(product).asModel(getProductViewName("_productListItem"));
        } catch (javax.validation.ConstraintViolationException ex) {
            response.withErrors(ex);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            response.withErrors("name", null, "product.Product.name.unique");
        }
        return response.andModelAttribute("command", cmd).asModel(getProductViewName("_createProductForm"));
    }

    @GetMapping(value = "/details/{id}")
    public ModelAndView productDetails(@PathVariable("id") String idStr) {
        return null;
    }

    private String getProductViewName(String viewName) {
        return PRODUCT_VIEW_PATH + viewName;
    }
}
