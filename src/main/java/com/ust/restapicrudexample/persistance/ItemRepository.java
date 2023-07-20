package com.ust.restapicrudexample.persistance;

import com.ust.restapicrudexample.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long>{
}
