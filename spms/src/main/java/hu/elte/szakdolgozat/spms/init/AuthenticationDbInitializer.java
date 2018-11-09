/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.init;

import hu.elte.szakdolgozat.spms.model.entity.spms.Right;
import hu.elte.szakdolgozat.spms.model.entity.spms.Role;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.repository.spms.RightRepository;
import hu.elte.szakdolgozat.spms.repository.spms.RoleRepository;
import hu.elte.szakdolgozat.spms.repository.spms.UserRepository;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Blanka Orosz
 */
@Component
public class AuthenticationDbInitializer {
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private RightRepository rightRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    
    @PostConstruct
    private void init() throws IllegalArgumentException, IllegalAccessException {
        initRightIfEmpty();
        initRoleIfEmpty();
        mockUser();
    }
    
    private void initRightIfEmpty() throws IllegalArgumentException, IllegalAccessException {
        if( rightRepository.count() > 0 ){
            return;
        }
        
        for(Field field : Right.RightName.class.getFields()) {
            Right right = new Right();
            right.setName( (Right.RightName) field.get(null));
            rightRepository.save(right);
        }
    }
    
     private void initRoleIfEmpty() throws IllegalArgumentException {
        if( roleRepository.count() > 0 ){
            return;
        }
        Role salesRole = new Role();
        salesRole.setName(Role.RoleName.SALES);
        List<Right> salesRights = new ArrayList<>();
        salesRights.add(rightRepository.findByName(Right.RightName.READ_COMMENT));
        salesRights.add(rightRepository.findByName(Right.RightName.READ_PLAN));
        salesRights.add(rightRepository.findByName(Right.RightName.WRITE_PLAN));
        salesRights.add(rightRepository.findByName(Right.RightName.SEND_PLAN));
        salesRole.setRights(salesRights);
        
        Role controllerRole = new Role();
        controllerRole.setName(Role.RoleName.CONTROLLER);
        List<Right> controllerRights = new ArrayList<>();
        controllerRights.add(rightRepository.findByName(Right.RightName.READ_COMMENT));
        controllerRights.add(rightRepository.findByName(Right.RightName.WRITE_COMMENT));
        controllerRights.add(rightRepository.findByName(Right.RightName.READ_PLAN));
        controllerRights.add(rightRepository.findByName(Right.RightName.WRITE_PLAN));
        controllerRights.add(rightRepository.findByName(Right.RightName.ACCEPT_PLAN));
        controllerRights.add(rightRepository.findByName(Right.RightName.SEND_PLAN));
        controllerRole.setRights(controllerRights);
        
        Role ceoRole = new Role();
        ceoRole.setName(Role.RoleName.CEO);
        List<Right> ceoRights = new ArrayList<>();
        ceoRights.add(rightRepository.findByName(Right.RightName.READ_PLAN));
        ceoRights.add(rightRepository.findByName(Right.RightName.READ_COMMENT));
        ceoRights.add(rightRepository.findByName(Right.RightName.WRITE_PLANNING_PERIOD));
        ceoRole.setRights(ceoRights);
        
        roleRepository.save(salesRole);
        roleRepository.save(controllerRole);
        roleRepository.save(ceoRole);
        
    }
     
     private void mockUser() {
         User user1 = new User();
         user1.setUserName("Kovács Ede");
         user1.setAgentCode("E1");
         user1.setPassword(encoder.encode("asd"));
         user1.setRole(roleRepository.findByName(Role.RoleName.SALES));
         
         userRepository.save(user1);

         User user2 = new User();
         user2.setUserName("ControllerAranka");
         user2.setPassword(encoder.encode("asd"));
         user2.setRole(roleRepository.findByName(Role.RoleName.CONTROLLER));

         userRepository.save(user2);
         
     }
}
