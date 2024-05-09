package com.hatio.todoapplication.service;

import com.hatio.todoapplication.dto.ProjectDto;
import com.hatio.todoapplication.exceptions.project.ProjectNotFoundException;
import com.hatio.todoapplication.exceptions.user.UserNotFoundException;
import com.hatio.todoapplication.model.Project;
import com.hatio.todoapplication.model.User;
import com.hatio.todoapplication.repository.ProjectRepository;
import com.hatio.todoapplication.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectExportService exportService;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, ProjectExportService exportService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.exportService = exportService;
    }

    public Project getProjectById(Integer id){
        Project project = projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Project with corresponding id not found"));
        return project;
    }

    public Project createNewProject(ProjectDto newProject) {

        Project projectToSave = new Project();


        User user = userRepository.findById(newProject.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with corresponding userId not found"));


        projectToSave.setTitle(newProject.getTitle());
        projectToSave.setCreatedDate(new Date()); // Set current date
        projectToSave.setUser(user);


        return projectRepository.save(projectToSave);
    }

    public ResponseEntity<?> getAllProjectsService() {
        List<Project> projectList =  projectRepository.findAll();
        return ResponseEntity.ok(projectList);
    }

    public ResponseEntity<?> getProjectByUserIdService(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with corresponding ID not found"));
        List<Project> projects = projectRepository.findByUser(user);
        if(projects.isEmpty()){
            throw new ProjectNotFoundException("No projects found for the corresponding user");
        }
        return ResponseEntity.ok(projects);
    }

    public ResponseEntity<Project> updateProjectByIdService(Integer id,String newTitle) {

        Project project = projectRepository.findById(id).orElseThrow(()-> new ProjectNotFoundException("Project not found with corresponding Id"));
        project.setTitle(newTitle);
        return ResponseEntity.ok(projectRepository.save(project));

    }

    public ResponseEntity<String> deleteProjectByIdService(Integer id) {
        if(projectRepository.existsById(id)){
            projectRepository.deleteById(id);
            return ResponseEntity.ok("Project With ID: " + id + " has been deleted");
        }
        else{
            throw new ProjectNotFoundException("Project not found with corresponding Id");
        }

    }

    public ResponseEntity<String> exportProjectService(Integer id) throws IOException {
        Project project = projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException("Project with corresponding ID not found"));
        String markdownString = exportService.exportToMarkdown(project);
        return ResponseEntity.ok(markdownString);
    }
}
