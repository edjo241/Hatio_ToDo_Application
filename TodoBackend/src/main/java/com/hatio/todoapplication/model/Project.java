package com.hatio.todoapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Date createdDate;
    private boolean isDeleted;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Todo> todoList;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

}
