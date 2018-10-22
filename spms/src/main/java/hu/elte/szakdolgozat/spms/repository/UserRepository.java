/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository;

import hu.elte.szakdolgozat.spms.model.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    Optional<User> findByName(String name);
    
    Optional<User> findByAgentCode(String agentCode);

    Optional<User> findByNameAndPassword(String name, String password);
}
