package com.ust.restapicrudexample;

import com.ust.restapicrudexample.model.Customer;
import com.ust.restapicrudexample.model.Item;
import com.ust.restapicrudexample.persistance.CustomerRepository;
import com.ust.restapicrudexample.persistance.ItemRepository;
import com.ust.restapicrudexample.persistance.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiCrudExample implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestApiCrudExample.class, args);
    }

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    SaleRepository saleRepository;

    @Override
    public void run(String... args) throws Exception
    {

        customerRepository.save(new Customer(null, "Citlalli", "Martinez", "citla@gmail.com","524772337489", "Geiser 112A Leon, Guanajuato"));
        customerRepository.save(new Customer(null, "Jazmin", "Rios", "jaz@gmail.com","524772849586", "Heroes 212 Leon, Guanajuato"));
        customerRepository.save(new Customer(null, "Carlos", "Martinez", "carlos@gmail.com","524775873658", "Geiser 112A Leon, Guanajuato"));
        customerRepository.save(new Customer(null, "Alexa", "Lopez", "alexa@gmail.com","524777542987", "La Antorcha 132 Leon, Guanajuato"));
        customerRepository.save(new Customer(null, "Angel", "Alvarez", "angel@gmail.com","524779638746", "Noria 211 Leon, Guanajuato"));

        itemRepository.save(new Item(null, "Lapiz", "Lapiz amarillo punto 2", 5.5, "Papeleria"));
        itemRepository.save(new Item(null, "Reglas", "Juego de geometria de 5 piezas", 15.6, "Papeleria"));
        itemRepository.save(new Item(null, "Libreta", "Libreta profesional de 100 hojas raya", 25.5, "Papeleria"));
        itemRepository.save(new Item(null, "Borrador", "Borrador para lapiz", 5.2, "Papeleria"));
        itemRepository.save(new Item(null, "Pluma", "Pluma negra de punto fino", 20.6, "Papeleria"));
    }
}
