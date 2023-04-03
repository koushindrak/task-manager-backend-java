package com.todo.dao;

import com.todo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> getProjectsByUser_Id(Long userId);

    Optional<Project> findProjectByIdAndUser_Id(Long projectId, Long userId);

}
