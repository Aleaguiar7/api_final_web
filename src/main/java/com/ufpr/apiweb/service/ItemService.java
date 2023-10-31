package com.ufpr.apiweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufpr.apiweb.model.Item;
import com.ufpr.apiweb.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    
    public ItemService(ItemRepository itemRepository) {
		this.itemRepository = null;
        
    }
    @Transactional
    public List<Item> obterItens() {
        return itemRepository.findAll();
    }
    @Transactional
    public Item obterItemPorId(int id_item) {
        return itemRepository.findById(id_item).orElse(null);
    }
    @Transactional
    public Item inserirItem(Item item) {
        return itemRepository.save(item);
    }
    @Transactional
    public Item alterarItem(int id_item, Item item) {
        if (itemRepository.existsById(id_item)) {
            item.setId_item(id_item);
            return itemRepository.save(item);
        }
        return null;
    }
    @Transactional
    public Item removerItem(int id_item) {
        Item item = itemRepository.findById(id_item).orElse(null);
        if (item != null) {
            itemRepository.deleteById(id_item);
        }
        return item;
    }
    
    
    }

