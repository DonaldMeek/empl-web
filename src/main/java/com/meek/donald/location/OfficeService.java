package com.meek.donald.location;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meek.donald.model.employee.EmployeeModel;
import com.meek.donald.model.employee.ManagerModel;
import com.meek.donald.model.location.LocationModel;
import com.meek.donald.model.location.OfficeModel;

public interface OfficeService {
	 List<EmployeeModel> getEmployeesByOfficeId(OfficeModel officeModel)
			 throws IOException;
	 List<OfficeModel> getOfficesByLocationId(LocationModel locationModel)
			 throws IOException;
	 Map<Integer, EmployeeModel> getOfficeEmployeesByManagerid(
			 ManagerModel manager) throws IOException;
	 List<OfficeModel> getOfficesByLocationExample(LocationModel locationModel)
			 throws IOException;
	 OfficeModel getOfficeByEmplId(EmployeeModel emplModel)
			 throws IOException;
}
