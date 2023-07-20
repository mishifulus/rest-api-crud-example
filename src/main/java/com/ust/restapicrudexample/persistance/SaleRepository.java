package com.ust.restapicrudexample.persistance;

import com.ust.restapicrudexample.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
