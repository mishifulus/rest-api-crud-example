package com.ust.restapicrudexample.controllers;

import com.ust.restapicrudexample.controllers.handlers.SaleNotFoundException;
import com.ust.restapicrudexample.model.Item;
import com.ust.restapicrudexample.model.Sale;
import com.ust.restapicrudexample.persistance.SaleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    SaleRepository saleRepository;

    @GetMapping("/sales")
    List<Sale> all()
    {

        return saleRepository.findAll();
    }

    @GetMapping("/sales/{id}")
    Sale getById(@PathVariable Long id)
    {
        return saleRepository.findById(id).orElseThrow(() -> new SaleNotFoundException(id));
        //Optional<Sale> sale = saleRepository.findById(id); //Para evitar tronar si no se encuentra el id
        //return sale.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/sales")
    Sale createNew(@Valid @RequestBody Sale newSale)
    {

        return saleRepository.save(newSale);
    }

    @DeleteMapping("/sales/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (saleRepository.existsById(id))
        {
            saleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/sales/{id}")
    Sale updateOrCreate(@Valid @RequestBody Sale newSale, @PathVariable Long id) {
        return saleRepository.findById(id)
                .map(sale -> {
                    sale.setDate(newSale.getDate());
                    sale.setCustomer(newSale.getCustomer());
                    sale.setItems(newSale.getItems());
                    sale.setTotal(newSale.getTotal());
                    return saleRepository.save(sale); //Se guarda el que ya tiene los cambios realizados
                })
                .orElseGet(() -> {
                    newSale.setId(id);
                    return saleRepository.save(newSale);
                });
    }

    @GetMapping("/sales/{id}/items")
    ResponseEntity<List<Item>> getItemsBySaleId(@PathVariable Long id)
    {
        Optional<Sale> sale = saleRepository.findById(id);

        if (sale.isPresent())
        {
            List<Item> items = sale.get().getItems();
            return ResponseEntity.ok(items);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
