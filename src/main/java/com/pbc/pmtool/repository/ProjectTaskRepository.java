package com.pbc.pmtool.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectProblem;
import com.pbc.pmtool.entity.Task;


@Repository("projectTaskRepository")
public interface ProjectTaskRepository   extends JpaRepository<Task, Serializable>{
	public abstract Task  findById(int id);
	public abstract List<Task> findAll();
	public Page<Task> findAllByOrderByIdDesc( Pageable pageable);
	public abstract List<Task> findByProject(Project project);
	
	public abstract List<Task>  findByProjectAndStatus(Project project, int status);
	public abstract List<Task>  findByStatus(int status);


	//@Query(value = "SELECT sum(base) as sbase, tipoirpf,sum(irpf) as sirpf, tipoiva,sum(iva) as siva,sum(total) as stotal FROM factura_cliente WHERE fechafactura >= ?1 and fechafactura < ?2 GROUP BY tipoirpf,tipoiva", nativeQuery = true)	
	//public List<Object[]> findProjectProblems();


}



