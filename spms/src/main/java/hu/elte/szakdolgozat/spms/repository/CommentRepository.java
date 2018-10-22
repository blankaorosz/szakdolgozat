/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository;

import hu.elte.szakdolgozat.spms.model.entity.Comment;
import hu.elte.szakdolgozat.spms.model.entity.Plan;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findByNameFrom(String nameFrom);

    Optional<Comment> findByNameTo(String nameTo);

    Optional<Comment> findByChecked(boolean checked);

    Optional<Comment> findByPlan(Plan plan);

}
