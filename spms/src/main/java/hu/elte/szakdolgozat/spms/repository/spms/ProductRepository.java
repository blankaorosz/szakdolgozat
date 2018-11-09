/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository.spms;

import hu.elte.szakdolgozat.spms.model.entity.spms.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    
    Product findByName(String name);

    List<Product> findByPrice(BigDecimal price);

    List<Product> findByUnit(String unit);
}
