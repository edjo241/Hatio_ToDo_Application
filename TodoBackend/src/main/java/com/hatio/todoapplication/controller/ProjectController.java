package com.hatio.todoapplication.controller;

import com.hatio.todoapplication.dto.ProjectDto;
import com.hatio.todoapplication.model.Project;
import com.hatio.todoapplication.service.ProjectService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //Get project by ProjectId
    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    //Get All Projects
    @GetMapping("/project/all")
    public ResponseEntity<?> getAllProjects(){
        return projectService.getAllProjectsService();
    }

    //Get All Projects By UserId
    @GetMapping("project/all/{userId}")
    public ResponseEntity<?> getProjectByUserId(@PathVariable Integer userId){
        return projectService.getProjectByUserIdService(userId);

    }



    //Endpoint to create a new project
    @PostMapping("/project/create")
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto projectDto){
        return ResponseEntity.ok(projectService.createNewProject(projectDto));
    }

    //Update Project by ProjectId
    @PostMapping("/project/update/{id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable Integer id,@RequestBody String title){
        return projectService.updateProjectByIdService(id,title);
    }

    @PostMapping("/project/export/{id}")
    public ResponseEntity<String> exportProject(@PathVariable Integer id) throws IOException {
        return projectService.exportProjectService(id);
    }

    @DeleteMapping("/project/delete/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable Integer id){
        return projectService.deleteProjectByIdService(id);
    }

}
