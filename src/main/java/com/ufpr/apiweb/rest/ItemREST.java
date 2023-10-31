package com.ufpr.apiweb.rest;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ufpr.apiweb.model.Item;

@CrossOrigin
@RestController
public class ItemREST {
    public static List<Item> listaItens = new ArrayList<>();

    @GetMapping("/itens")
    public List<Item> obterItens() {
        return listaItens;
    }

    @GetMapping("/itens/{id}")
    public Item obterItemPorId(@PathVariable("id") int id) {
        return listaItens.stream().filter(item -> item.getId() == id).findAny().orElse(null);
    }

    @PostMapping("/itens")
    public Item inserirItem(@RequestBody Item item) {
        Item newItem = listaItens.stream().max(Comparator.comparing(Item::getId)).orElse(null);
        if (newItem == null)
            item.setId(1);
        else
            item.setId(newItem.getId() + 1);
        listaItens.add(item);
        return item;
    }

    @PutMapping("/itens/{id}")
    public Item alterarItem(@PathVariable("id") int id, @RequestBody Item item) {
        Item existingItem = listaItens.stream().filter(i -> i.getId() == id).findAny().orElse(null);
        if (existingItem != null) {
            existingItem.setNome(item.getNome());
            existingItem.setValor(item.getValor());
            existingItem.setQuantia(item.getQuantia());
        }
        return existingItem;
    }

    @DeleteMapping("/itens/{id}")
    public Item removerItem(@PathVariable("id") int id) {
        Item item = listaItens.stream().filter(i -> i.getId() == id).findAny().orElse(null);
        if (item != null)
            listaItens.removeIf(i -> i.getId() == id);
        return item;
    }

    static {
        listaItens.add(new Item(1, "Item1", 10.0, 5));
        listaItens.add(new Item(2, "Item2", 15.0, 8));
        listaItens.add(new Item(3, "Item3", 20.0, 3));
    }
}
