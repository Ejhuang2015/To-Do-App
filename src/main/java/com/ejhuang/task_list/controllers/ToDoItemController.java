package com.ejhuang.task_list.controllers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ejhuang.task_list.repositories.ToDoItemRepository;
import com.ejhuang.task_list.models.ToDoItem;

import jakarta.validation.Valid;

@Controller
public class ToDoItemController {
    private final Logger logger = LoggerFactory.getLogger(ToDoItemController.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping("/")
    public ModelAndView index() {
        logger.debug( "Request to GET home");
        ModelAndView showView = new ModelAndView("index");
        showView.addObject("toDoItems", toDoItemRepository.findAll());
        showView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        showView.addObject("content", "home");
        return showView;
    }

    @PostMapping("/todo")
    public ModelAndView createToDoItem(@Valid ToDoItem toDoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            ModelAndView showView = new ModelAndView("index");
            showView.addObject("content", "add");
            return showView;
        }

        toDoItem.setCreatedDate(LocalDateTime.now().format(formatter));
        toDoItem.setModifiedDate(LocalDateTime.now().format(formatter));
        toDoItemRepository.save(toDoItem);
        
        ModelAndView showView =  new ModelAndView("redirect:/");
        return showView;
    }

    @PostMapping("/todo/{id}")
    public ModelAndView updateToDoItem(@PathVariable("id") long id, @Valid ToDoItem toDoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            toDoItem.setId(id);
            ModelAndView showView = new ModelAndView("index");
            showView.addObject("content", "edit");
            return showView;
        }

        toDoItem.setModifiedDate(LocalDateTime.now().format(formatter));
        toDoItemRepository.save(toDoItem);
        ModelAndView showView =  new ModelAndView("redirect:/");
        return showView;
    }
}
