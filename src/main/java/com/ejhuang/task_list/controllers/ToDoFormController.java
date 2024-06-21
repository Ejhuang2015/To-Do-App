package com.ejhuang.task_list.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ejhuang.task_list.models.ToDoItem;
import com.ejhuang.task_list.repositories.ToDoItemRepository;


@Controller
public class ToDoFormController {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping("/create-todo")
    public ModelAndView showCreateForm(ToDoItem toDoItem){
        ModelAndView showView = new ModelAndView("index");
        showView.addObject("content", "add");
        return showView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditForm(@PathVariable("id") long id, Model model) {
        ToDoItem toDoItem = toDoItemRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Task ID# " + id + " not found."));

            model.addAttribute("todo", toDoItem);
            ModelAndView showView = new ModelAndView("index");
            showView.addObject("content", "edit");
            return showView;
    }
    
    @GetMapping("/delete/{id}")
    public ModelAndView deleteTodoItem(@PathVariable("id") long id, Model model) {
        ToDoItem toDoItem = toDoItemRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Task ID# " + id + " not found"));
    
        toDoItemRepository.delete(toDoItem);
        ModelAndView showView =  new ModelAndView("redirect:/");
        return showView;
    }
}
