package com.pbc.pmtool.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectProblem;


@Repository("projectProblemRepository")
public interface ProjectProblemRepository   extends JpaRepository<ProjectProblem, Serializable>{
	public abstract ProjectProblem  findById(int id);
	public abstract List<ProjectProblem> findAll();
	public Page<ProjectProblem> findAllByOrderByIdDesc( Pageable pageable);
	public abstract List<ProjectProblem> findByProject(Project project);


	//@Query(value = "SELECT sum(base) as sbase, tipoirpf,sum(irpf) as sirpf, tipoiva,sum(iva) as siva,sum(total) as stotal FROM factura_cliente WHERE fechafactura >= ?1 and fechafactura < ?2 GROUP BY tipoirpf,tipoiva", nativeQuery = true)	
	//public List<Object[]> findProjectProblems();


}



