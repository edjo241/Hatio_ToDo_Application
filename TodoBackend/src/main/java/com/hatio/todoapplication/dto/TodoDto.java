package com.hatio.todoapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {


    private String title;

    private String description;

    private Integer projectId;
}
