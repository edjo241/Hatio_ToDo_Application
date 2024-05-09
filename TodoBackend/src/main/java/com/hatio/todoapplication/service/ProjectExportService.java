package com.hatio.todoapplication.service;

import com.hatio.todoapplication.model.Project;
import com.hatio.todoapplication.model.Todo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHGist;
import org.kohsuke.github.GHGistBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

@Service
public class ProjectExportService {

    @Value("${github.token}")
    private  String githubToken;

    public String createSecretGist(String markdownContent, String personalAccessToken,String fileName) throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken(personalAccessToken).build();
        GHGistBuilder builder = github.createGist();
        builder.public_(false); // Make the Gist secret


        builder.file(fileName, markdownContent);

        GHGist gist = builder.create();
        return gist.getHtmlUrl().toString();
    }

    public String exportToMarkdown(Project project) throws IOException {
        String markdownContent = generateMarkdown(project);


        // Save the markdown content to a local file
        String fileName = project.getTitle() + ".md"; // Replace with your desired file name
        String gistUrl = createSecretGist(markdownContent, githubToken,fileName);
        String basePath = new File("").getAbsolutePath(); // Get the base directory of the application
        String filePath = basePath + File.separator + "exportedProjects" + File.separator + fileName;
        File file = new File(filePath);
        FileUtils.writeStringToFile(file, markdownContent, "UTF-8");

        return gistUrl;
    }
    private String generateMarkdown(Project project) {
        StringBuilder markdown = new StringBuilder();
        markdown.append("# ").append(project.getTitle()).append("\n\n");
        markdown.append("**Summary:** ");

        List<Todo> todoList = project.getTodoList();
        int completedCount = 0;
        for (Todo todo : todoList) {
            if (todo.isDone()) {
                completedCount++;
            }
        }
        markdown.append(completedCount).append("/").append(todoList.size()).append(" Todos Completed\n\n");

        markdown.append("### Pending\n");
        for (Todo todo : todoList) {
            if (!todo.isDone()) {
                markdown.append("- [ ] ").append(todo.getTitle()).append("\n");
                if (todo.getDescription() != null && !todo.getDescription().isEmpty()) {
                    markdown.append("  ").append(todo.getDescription()).append("\n");
                }
            }
        }

        markdown.append("\n### Completed\n");
        for (Todo todo : todoList) {
            if (todo.isDone()) {
                markdown.append("- [x] ").append(todo.getTitle()).append("\n");
                if (todo.getDescription() != null && !todo.getDescription().isEmpty()) {
                    markdown.append("  ").append(todo.getDescription()).append("\n");
                }
            }
        }

        return markdown.toString();
    }
}
