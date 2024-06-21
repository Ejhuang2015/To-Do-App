package com.ejhuang.task_list.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "todo_item")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Description is required")
    private String description;
    
    @Getter
    @Setter
    private boolean complete;

    @Getter
    @Setter
    private String createdDate;

    @Getter
    @Setter
    private String modifiedDate;

    @Getter
    @Setter
    private String goalDate;

    @Getter
    @Setter
    private int taskProirity;

    public ToDoItem() {}

    public ToDoItem(String description) {
        this.description = description;
        this.complete = false;
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
        this.goalDate = LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
        this.taskProirity = 0;
    }

    @Override
    public String toString() {
        return String.format("TodoItem{id=%d, description='%s', complete='%s', createdDate='%s', modifiedDate='%s'}",
        id, description, complete, createdDate, modifiedDate);
    }
}
