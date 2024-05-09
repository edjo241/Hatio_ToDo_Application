package com.hatio.todoapplication.mapper;

import com.hatio.todoapplication.dto.ProjectDto;
import com.hatio.todoapplication.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public class ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);



}
