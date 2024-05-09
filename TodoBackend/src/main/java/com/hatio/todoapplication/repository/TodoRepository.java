package com.hatio.todoapplication.repository;

import com.hatio.todoapplication.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {

}
