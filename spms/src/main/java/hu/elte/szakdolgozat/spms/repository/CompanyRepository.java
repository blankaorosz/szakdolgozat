/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository;

import hu.elte.szakdolgozat.spms.model.entity.Company;
import hu.elte.szakdolgozat.spms.model.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    Optional<Company> findByName(String name);

    Optional<Company> findByActive(boolean active);

    Optional<Company> findByUser(User user);
}
