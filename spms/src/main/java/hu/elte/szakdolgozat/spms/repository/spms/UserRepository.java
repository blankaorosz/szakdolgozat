/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository.spms;

import hu.elte.szakdolgozat.spms.model.entity.spms.Role;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByUserName(String userName);
    
    User findByAgentCode(String agentCode);

    List<User> findByRole(Role role);
}
