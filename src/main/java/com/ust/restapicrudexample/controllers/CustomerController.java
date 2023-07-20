package com.ust.restapicrudexample.controllers;

import com.ust.restapicrudexample.controllers.handlers.CustomerNotFoundException;
import com.ust.restapicrudexample.controllers.handlers.ItemNotFoundException;
import com.ust.restapicrudexample.model.Customer;
import com.ust.restapicrudexample.persistance.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired //Inyectar dependencias
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    List<Customer> all()
    {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    Customer getById(@PathVariable Long id)
    {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        //Optional<Customer> customer = customerRepository.findById(id); //Para evitar tronar si no se encuentra el id
        //return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/customers")
    Customer createNew(@Valid @RequestBody Customer newCustomer)
    {
        return customerRepository.save(newCustomer);
    }

    @DeleteMapping("/customers/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (customerRepository.existsById(id))
        {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/customers/{id}")
    Customer updateOrCreate(@Valid @RequestBody Customer newCustomer, @PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setLastName(newCustomer.getLastName());
                    customer.setEmail(newCustomer.getEmail());
                    customer.setPhone(newCustomer.getPhone());
                    customer.setAddress(newCustomer.getAddress());
                    return customerRepository.save(customer); //Se guarda el que ya tiene los cambios realizados
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }
}
