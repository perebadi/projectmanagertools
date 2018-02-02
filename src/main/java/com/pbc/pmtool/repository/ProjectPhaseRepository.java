package com.pbc.pmtool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectPhase;


@Repository("projectPhaseRepository")
public interface ProjectPhaseRepository {
	public abstract ProjectPhase  findById(int id);
	public abstract List<ProjectPhase> findAll();
	public Page<ProjectPhase> findAllByOrderByIdDesc( Pageable pageable);
	public abstract List<ProjectPhase> findByProject(Project project);


	//@Query(value = "SELECT sum(base) as sbase, tipoirpf,sum(irpf) as sirpf, tipoiva,sum(iva) as siva,sum(total) as stotal FROM factura_cliente WHERE fechafactura >= ?1 and fechafactura < ?2 GROUP BY tipoirpf,tipoiva", nativeQuery = true)	
	//public List<Object[]> findProjectPhases();


}



