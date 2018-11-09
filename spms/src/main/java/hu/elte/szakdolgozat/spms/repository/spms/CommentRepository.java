/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.repository.spms;

import hu.elte.szakdolgozat.spms.model.entity.spms.Comment;
import hu.elte.szakdolgozat.spms.model.entity.spms.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Blanka Orosz
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByNameFrom(String nameFrom);

    List<Comment> findByNameTo(String nameTo);

    List<Comment> findByChecked(boolean checked);

    List<Comment> findByPlan(Plan plan);

}
