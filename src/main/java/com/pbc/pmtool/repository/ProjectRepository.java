package com.pbc.pmtool.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.SumValuesModel;


@Repository("projectRepository")
public interface ProjectRepository  extends JpaRepository<Project, Serializable> {
	public abstract Project findById(int id);
	public abstract List<Project> findAll();
	public Page<Project> findAllByOrderByIdDesc( Pageable pageable);
	public abstract List<Project> findByUser(User user);
	
	public abstract Page<Project> findByPmo(User user, Pageable pageable);
	public abstract Page<Project> findByUser(User user, Pageable pageable);


	@Query(value = "SELECT coalesce(sum(TVC), 0) as sTVC, coalesce(sum(TIC), 0) as sTIC, coalesce(sum(costestimated), 0) as scostestimatedl FROM project WHERE  user_username =?1 AND projectactive=true", nativeQuery = true)	
	public  List<Object[]> getActiveSum(String username);


}



