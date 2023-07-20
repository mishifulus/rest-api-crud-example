package com.ust.restapicrudexample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SALE")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @NotNull
    @Min(value = 0)
    @Max(value = 1000000)
    private float total;

    @Column(nullable = false)
    @NotBlank
    @NotNull // Validaciones con JSR 380
    @OneToMany(mappedBy = "ITEM") //Multiples items
    private List<Item> items;

    @Column(nullable = false)
    @ManyToOne // Solo un cliente
    @NotBlank
    @NotNull
    private Customer customer;

    @Column(nullable = false)
    @NotBlank
    @NotNull
    private LocalDateTime date;
}
