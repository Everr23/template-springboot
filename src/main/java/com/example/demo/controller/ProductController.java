package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.models.Product;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<ProductDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/products/create")
    public String createProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "products/create";
    }

    @PostMapping("/products/create")
    public String saveProduct(@Valid @ModelAttribute("product") ProductDto productDto,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", productDto);
            return "products/create";
        }
        productService.saveProduct(productDto);
        return "redirect:/products";
    }

    @GetMapping("/products/{productId}/edit")
    public String editProductForm(@PathVariable("productId") Long productId, Model model) {
        ProductDto product = productService.findProductById(productId);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/products/{productId}/edit")
    public String updateProduct(@PathVariable("productId") Long productId,
            @Valid @ModelAttribute("product") ProductDto product,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "products/edit";
        }
        product.setId(productId);
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{productId}/delete")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        productService.delete(productId);
        return "redirect:/products";
    }
}
