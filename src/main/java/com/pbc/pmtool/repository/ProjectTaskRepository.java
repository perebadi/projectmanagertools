package com.pbc.pmtool.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectProblem;
import com.pbc.pmtool.entity.Task;
import com.pbc.pmtool.model.ProjectTaskModel;


@Repository("projectTaskRepository")
public interface ProjectTaskRepository   extends JpaRepository<Task, Serializable>{
	public abstract Task  findById(int id);
	public abstract List<Task> findAll();
	public Page<Task> findAllByOrderByIdDesc( Pageable pageable);
	public abstract List<Task> findByProject(Project project);
	
	public abstract List<Task>  findByProjectAndStatus(Project project, int status);
	public abstract List<Task>  findByStatus(int status);


	@Query(value = "SELECT id, projectname, user_username, projectactive, backlog, todo, inprogress, done FROM view_project_task WHERE user_username = ?1 and projectactive = 1 or id in (SELECT project_id FROM projectmanagertools.project_user WHERE user_username = ?1 and projectactive = 1);", nativeQuery = true)	
	public List<Object[]> findProjectTask(String username);


}



