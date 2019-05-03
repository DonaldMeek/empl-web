package com.meek.donald.project;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.model.employee.EmployeeModel;
import com.meek.donald.model.location.OfficeModel;
import com.meek.donald.model.projects.ProjectModel;

public interface ProjectService {

	List<Integer> getEmployeeIdsByProjectExample(ProjectModel projModel)
			throws IOException;
	OfficeModel getOfficeByProjectExample(ProjectModel projModel)
			throws IOException;
	ProjectModel getAllActiveProjects()
			throws IOException;
}
