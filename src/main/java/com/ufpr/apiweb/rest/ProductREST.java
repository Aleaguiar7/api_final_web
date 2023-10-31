package com.ufpr.apiweb.rest;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ufpr.apiweb.model.Product;

@CrossOrigin
@RestController
public class ProductREST {
    public static List<Product> listaProdutos = new ArrayList<>();

    @GetMapping("/product")
    public List<Product> obterProdutos() {
        return listaProdutos;
    }

    @GetMapping("/product/{id}")
    public Product obterProdutoPorId(@PathVariable("id") int id) {
        return listaProdutos.stream().filter(produto -> produto.getId() == id).findAny().orElse(null);
    }

    @PostMapping("/product")
    public Product inserirProduto(@RequestBody Product produto) {
        Product newProduct = listaProdutos.stream().max(Comparator.comparing(Product::getId)).orElse(null);
        if (newProduct == null)
            produto.setId(1);
        else
            produto.setId(newProduct.getId() + 1);
        listaProdutos.add(produto);
        return produto;
    }

    @PutMapping("/product/{id}")
    public Product alterarProduto(@PathVariable("id") int id, @RequestBody Product produto) {
        Product existingProduct = listaProdutos.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        if (existingProduct != null) {
            existingProduct.setDescricao(produto.getDescricao());
            existingProduct.setValor(produto.getValor());
        }
        return existingProduct;
    }

    @DeleteMapping("/product/{id}")
    public Product removerProduto(@PathVariable("id") int id) {
        Product produto = listaProdutos.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        if (produto != null)
            listaProdutos.removeIf(p -> p.getId() == id);
        return produto;
    }
}
