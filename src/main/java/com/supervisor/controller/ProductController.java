package com.supervisor.controller;

import com.supervisor.command.ProductCreateCommand;
import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = {"index", "/"})
    public RedirectView index() {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("list");
        return redirectView;
    }

    @GetMapping(value = "list")
	public String listProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "list";
	}

    @PostMapping(value = "create")
    public void createProduct(@RequestBody ProductCreateCommand cmd) {
		// TODO Could this return the product? think so
        productService.createProduct(cmd);
    }
}
