package com.pbc.pmtool.serviceimpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.SumValuesModel;
import com.pbc.pmtool.repository.ProjectRepository;
import com.pbc.pmtool.service.ProjectService;

@Service("projectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

	
	@Autowired
	@Qualifier("projectRepository")
	private ProjectRepository projectRepository;
	
	@Override
	public Project addProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Project findProjectById(int id) {
		
		return projectRepository.findById(id);
	}


	@Override
	public List<Project> listPageableProject(int pageno, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> listProjectByUser(User user) {
		
		List<Project> projects = projectRepository.findByUser(user);
		return projects;
	
	}



	@Override
	public void removeSource(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Project> listPageableProjects(int pageno, User user) {
		Page<Project> pageprojects = projectRepository.findByUser(user, new PageRequest(pageno, 20, Sort.Direction.DESC, "id"));
		List<Project> projects = pageprojects.getContent();
		return projects;
	}

	@Override
	public List<Project> listProjects() {
		// TODO Auto-generated method stub
		return projectRepository.findAll();
	}

	@Override
	public int countRecords(User user) {
		List<Project> projects = projectRepository.findByUser(user);
		return projects.size();
	}

	@Override
	public Project updateProject(Project project) {
		// TODO Auto-generated method stub
		System.out.print("CUANTOS"+project.getAchievements().size());
		
		Iterator iterator = project.getAchievements().iterator();
		while(iterator.hasNext()){
		  ProjectAchievement element = (ProjectAchievement) iterator.next();
		  System.out.print("DENTRO" + element.getSummaryachievement());
		}
		
		return projectRepository.save(project); 
	}

	@Override
	public SumValuesModel getActiveSum(String username, boolean isPmo) {
				
		SumValuesModel sumValuesModel = new   SumValuesModel();
				
		List<Object[]> ops = new ArrayList<Object[]>();

		if(isPmo) {
			ops=projectRepository.getPMOActiveSum(username, username);
		}else {
			ops=projectRepository.getActiveSum(username);
		}
		
		for (Object[] op : ops ){
			sumValuesModel.setsTIC((double) op[1]);
			sumValuesModel.setsTVC((double) op[0]);
			sumValuesModel.setScostestimated((double) op[2]);
			sumValuesModel.setEACOP(0);
			sumValuesModel.setOP(0);
			
			sumValuesModel.setEACOP((1-(double) op[2]/(double) op[0])*100);
			sumValuesModel.setOP((1-(double) op[1]/(double) op[0])*100);
			
			if(Double.isNaN(sumValuesModel.getEACOP())) {
				sumValuesModel.setEACOP(0);
			}
			
			if(Double.isNaN(sumValuesModel.getOP())) {
				sumValuesModel.setOP(0);
			}
			
			}
		
		return sumValuesModel;
	}

	@Override
	public List<Project> listPageablePmoProjects(int pageno, User user) {
		Page<Project> pageprojects = projectRepository.findByPmo(user, new PageRequest(pageno, 20, Sort.Direction.DESC, "id"));
		List<Project> projects = pageprojects.getContent();
		return projects;
	}

	@Override
	public boolean uploadE3T(MultipartFile e3tFile, int id) {
		File file = new File(System.getProperty("user.dir")+ViewConstant.E3TFOLDER+"e3tfile_" + id + "." + FilenameUtils.getExtension(e3tFile.getOriginalFilename()));

		try {
			FileUtils.writeByteArrayToFile(file, e3tFile.getBytes());
			
			Project project = findProjectById(id);
			
			project.setE3t("e3tfile_" + id + "." + FilenameUtils.getExtension(e3tFile.getOriginalFilename()));
			
			projectRepository.save(project);
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			
			return false;
		}
	}

}
