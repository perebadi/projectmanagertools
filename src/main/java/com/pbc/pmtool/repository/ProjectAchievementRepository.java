package com.pbc.pmtool.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;


@Repository("projectAchievementRepository")
public interface ProjectAchievementRepository  extends JpaRepository<ProjectAchievement, Serializable>{
	public abstract ProjectAchievement  findById(int id);
	public abstract List<ProjectAchievement> findAll();
	public Page<ProjectAchievement> findAllByOrderByIdDesc( Pageable pageable);
	public abstract List<ProjectAchievement> findByProject(Project project);
	public abstract Page<ProjectAchievement> findByProject(Project project, Pageable pageable);


	//@Query(value = "SELECT sum(base) as sbase, tipoirpf,sum(irpf) as sirpf, tipoiva,sum(iva) as siva,sum(total) as stotal FROM factura_cliente WHERE fechafactura >= ?1 and fechafactura < ?2 GROUP BY tipoirpf,tipoiva", nativeQuery = true)	
	//public List<Object[]> findProjectAchievements();


}



