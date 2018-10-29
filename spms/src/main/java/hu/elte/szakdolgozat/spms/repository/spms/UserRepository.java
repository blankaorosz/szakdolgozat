/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository.spms;

import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    Optional<User> findByUserName(String userName);
    
    Optional<User> findByAgentCode(String agentCode);

    Optional<User> findByUserNameAndPassword(String userName, String password);
}
