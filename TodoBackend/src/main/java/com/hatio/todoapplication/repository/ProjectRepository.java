package com.hatio.todoapplication.repository;


import com.hatio.todoapplication.model.Project;
import com.hatio.todoapplication.model.Todo;
import com.hatio.todoapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

    Optional<Project> findById(Integer id);

    @Override
    List<Project> findAll();

    List<Project> findByUser(User user);


}
