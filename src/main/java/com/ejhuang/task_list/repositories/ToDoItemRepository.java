package com.ejhuang.task_list.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ejhuang.task_list.models.ToDoItem;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {  
}