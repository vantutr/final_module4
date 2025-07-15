package com.codegym.product_management.controller;

import com.codegym.product_management.model.Product;
import com.codegym.product_management.service.IProductService;
import com.codegym.product_management.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductTypeService productTypeService;

    @GetMapping
    public String showList(Model model,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) Long typeId,
                           @RequestParam(required = false) Double priceFrom,
                           @RequestParam(required = false) Double priceTo,
                           @PageableDefault(size = 5) Pageable pageable) {

        Page<Product> products;
        boolean isSearching = StringUtils.hasText(name) || typeId != null || priceFrom != null || priceTo != null;

        if (isSearching) {
            products = productService.searchAndPaginate(name, typeId, priceFrom, priceTo, pageable);
        } else {
            products = productService.findAll(pageable);
        }

        model.addAttribute("products", products);
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("name", name);
        model.addAttribute("typeId", typeId);
        model.addAttribute("priceFrom", priceFrom);
        model.addAttribute("priceTo", priceTo);

        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("productTypes", productTypeService.findAll());
        return "create";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productTypes", productTypeService.findAll());
            return "create";
        }
        productService.save(product);
        redirect.addFlashAttribute("message", "Thêm mới sản phẩm thành công!");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("productTypes", productTypeService.findAll());
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String updateProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productTypes", productTypeService.findAll());
            return "edit";
        }
        productService.save(product);
        redirect.addFlashAttribute("message", "Cập nhật sản phẩm thành công!");
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteProducts(@RequestParam("selectedIds") List<Long> ids, RedirectAttributes redirect) {
        productService.deleteAllById(ids);
        redirect.addFlashAttribute("message", "Đã xóa các sản phẩm được chọn.");
        return "redirect:/";
    }
}