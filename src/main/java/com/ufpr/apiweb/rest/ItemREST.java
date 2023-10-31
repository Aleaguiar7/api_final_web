package com.ufpr.apiweb.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.apiweb.model.Item;
import com.ufpr.apiweb.repository.ItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemREST {
    private final ItemRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ItemREST(ItemRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    List<Item> obterItens() {
        List<Item> itens = repository.findAll();
        return itens.stream()
            .map(item -> mapper.map(item, Item.class))
            .collect(Collectors.toList());
    }

    @GetMapping("/{id_item}")
    ResponseEntity<Item> obterItemPorId(@PathVariable("id_item") Optional<Item> id_item) {
    	int getId_item = 0;
		Item item = repository.findByid(getId_item);
        if (id_item != null) {
            return ResponseEntity.ok(mapper.map(item, Item.class));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<Item> inserirItem(@RequestBody Item item) {
        Item entity = mapper.map(item, Item.class);
        repository.save(entity);
        Item savedItem = repository.findByid(entity.getId_item());
        Item savedItemDTO = mapper.map(savedItem, Item.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
    }

    @PutMapping("/{id_item}")
    ResponseEntity<Item> alterarItem(@PathVariable("id_item") int id_item, @RequestBody Item item) {
        Item existingItem = repository.findByid(id_item);
        if (existingItem != null) {
            mapper.map(item, existingItem);
            repository.save(existingItem);
            return ResponseEntity.ok(mapper.map(existingItem, Item.class));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id_item}")
    ResponseEntity<Item> removerItem(@PathVariable("id_item") int id_item) {
        Item item = repository.findByid(id_item);
        if (item != null) {
            repository.deleteById(id_item);
            return ResponseEntity.ok(mapper.map(item, Item.class));
        }
        return ResponseEntity.notFound().build();
    }
}