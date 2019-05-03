package com.meek.donald.location;

import java.io.IOException;
import com.meek.donald.model.employee.EmployeeModel;
import com.meek.donald.model.location.LocationModel;
import com.meek.donald.model.location.OfficeModel;

public interface LocationService {
	EmployeeModel getEmployeeByLocationExample(LocationModel locModel)
			throws IOException;
	EmployeeModel getEmployeeByLocationId(LocationModel locModel)
			throws IOException;
	OfficeModel getOfficeByLocationId(LocationModel locModel)
			throws IOException;

}
