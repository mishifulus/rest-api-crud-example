package com.ust.restapicrudexample.controllers;

import com.ust.restapicrudexample.controllers.handlers.ItemNotFoundException;
import com.ust.restapicrudexample.controllers.handlers.SaleNotFoundException;
import com.ust.restapicrudexample.model.Item;
import com.ust.restapicrudexample.persistance.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    List<Item> all()
    {

        return itemRepository.findAll();
    }

    @GetMapping("/items/{id}")
    Item getById(@PathVariable Long id)
    {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        //Optional<Item> item = itemRepository.findById(id); //Para evitar tronar si no se encuentra el id
        //return item.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/items")
    Item createNew(@Valid @RequestBody Item newItem)
    {

        return itemRepository.save(newItem);
    }

    @DeleteMapping("/items/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id)
    {

        if (itemRepository.existsById(id))
        {
            itemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/items/{id}")
    Item updateOrCreate(@Valid @RequestBody Item newItem, @PathVariable Long id) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setDescription(newItem.getDescription());
                    item.setCategory(newItem.getCategory());
                    item.setPrice(newItem.getPrice());
                    return itemRepository.save(item); //Se guarda el que ya tiene los cambios realizados
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return itemRepository.save(newItem);
                });
    }
}
