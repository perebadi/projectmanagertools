package com.pbc.pmtool.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.User;


@Repository("projectRepository")
public interface ProjectRepository  extends JpaRepository<Project, Serializable> {
	public abstract Project findById(int id);
	public abstract List<Project> findAll();
	public Page<Project> findAllByOrderByIdDesc( Pageable pageable);
	public abstract List<Project> findByUser(User user);
	
	public abstract Page<Project> findByUser(User user, Pageable pageable);


	//@Query(value = "SELECT sum(base) as sbase, tipoirpf,sum(irpf) as sirpf, tipoiva,sum(iva) as siva,sum(total) as stotal FROM factura_cliente WHERE fechafactura >= ?1 and fechafactura < ?2 GROUP BY tipoirpf,tipoiva", nativeQuery = true)	
	//public List<Object[]> findProjectAchievements();


}



