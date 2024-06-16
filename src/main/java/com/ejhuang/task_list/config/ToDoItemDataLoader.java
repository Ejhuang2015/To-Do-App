package com.ejhuang.task_list.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ejhuang.task_list.models.ToDoItem;
import com.ejhuang.task_list.repositories.ToDoItemRepository;

@Component
public class ToDoItemDataLoader implements CommandLineRunner{

    private final Logger logger = LoggerFactory.getLogger(ToDoItemDataLoader.class);

    @Autowired
    ToDoItemRepository toDoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }
    
    private void loadSeedData() {
        if (toDoItemRepository.count() == 0) {
            ToDoItem starter1 = new ToDoItem("Create new tasks");
            ToDoItem starter2 = new ToDoItem("Delete template data");

            toDoItemRepository.save(starter1);
            toDoItemRepository.save(starter2);
        }

        logger.info("Number of Tasks: {}", toDoItemRepository.count());
    }
}
