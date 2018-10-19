/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository;

import hu.elte.szakdolgozat.spms.model.Product;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    
    Optional<Product> findByName(String name);

    Optional<Product> findByPrice(BigDecimal price);

    Optional<Product> findByUnit(String unit);
}
