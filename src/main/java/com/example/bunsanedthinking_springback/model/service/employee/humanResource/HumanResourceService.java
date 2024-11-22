package com.example.bunsanedthinking_springback.model.service.employee.humanResource;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.humanResource.request.AddEmployeeRequest;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.request.CreateFamilyListRequest;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.RelationshipType;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.department.DepartmentEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.employee.EmployeeEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.family.FamilyEntityModel;

@Service
public class HumanResourceService {
	@Autowired
	private EmployeeEntityModel employeeEntityModel;
	@Autowired
	private FamilyEntityModel familyEntityModel;
	@Autowired
	private DepartmentEntityModel departmentEntityModel;

	@Value("${serials.employee}")
	private int EMPLOYEE_SERIAL_NUMBER;

	@Value("${serials.family}")
	private int FAMILY_SERIAL_NUMBER;

	public void addEmployee(AddEmployeeRequest addEmployeeRequest) throws DuplicateResidentRegistrationNumberException,
		ParseException {
		checkResidentRegistrationNumber(addEmployeeRequest.getResidentRegistrationNumber());
		Integer employeeMaxId = employeeEntityModel.getMaxId();
		int employeeId;
		if (employeeMaxId == null) {
			employeeId = Integer.parseInt(EMPLOYEE_SERIAL_NUMBER + "1");
		} else {
			employeeId = NextIdGetter.getNextId(employeeMaxId, EMPLOYEE_SERIAL_NUMBER);
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date employmentDate = formatter.parse(addEmployeeRequest.getEmploymentDate());
		EmployeePosition employeePosition = EmployeePosition.indexOf(addEmployeeRequest.getEmployeePosition());

		ArrayList<Family> familyList = createFamilyList(employeeId, addEmployeeRequest.getTempFamilyList());

		Employee employee = new Employee(addEmployeeRequest.getAddress(), addEmployeeRequest.getBankAccount(),
			addEmployeeRequest.getDepartmentId(),
			employmentDate, addEmployeeRequest.getBankName(), familyList, employeeId, addEmployeeRequest.getName(),
			null, addEmployeeRequest.getPhoneNumber(), employeePosition,
			addEmployeeRequest.getResidentRegistrationNumber(), addEmployeeRequest.getSalary(), null);
		employeeEntityModel.add(employee);
	}

	private void checkResidentRegistrationNumber(String residentRegistrationNumber)
		throws DuplicateResidentRegistrationNumberException {
		// TODO isExist같은 SQL로 수정 해야됨
		for (Employee employee : employeeEntityModel.getAll()) {
			if (employee.getResidentRegistrationNumber().equals(residentRegistrationNumber)) {
				throw new DuplicateResidentRegistrationNumberException();
			}
		}
	}

	private ArrayList<Family> createFamilyList(int employeeId, List<CreateFamilyListRequest> tempFamilyList) {
		Integer familyMaxId = familyEntityModel.getMaxId();
		int familyId;
		if (familyMaxId == null) {
			familyId = Integer.parseInt(("" + FAMILY_SERIAL_NUMBER) + 1);
		} else {
			familyId = NextIdGetter.getNextId(familyMaxId, FAMILY_SERIAL_NUMBER);
		}
		familyMaxId = familyId;
		ArrayList<Family> familyList = new ArrayList<>();
		for (CreateFamilyListRequest createFamilyListRequest : tempFamilyList) {
			RelationshipType relationshipType = RelationshipType.indexOf(createFamilyListRequest.getRelationship());
			Family family = new Family(Date.valueOf(createFamilyListRequest.getBirthDate()), employeeId, familyId,
				createFamilyListRequest.getName(), relationshipType, createFamilyListRequest.isSurvival());
			familyList.add(family);
			familyId = NextIdGetter.getNextId(familyMaxId, FAMILY_SERIAL_NUMBER);
			familyMaxId = familyId;
		}
		return familyList;
	}

	public void deleteEmployee(int id) throws NotExistException {
		if (employeeEntityModel.getById(id) == null)
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
		employeeEntityModel.delete(id);
	}

	public Employee getEmployee(int id) throws NotExistException {
		Employee employee = employeeEntityModel.getById(id);
		if (employee == null)
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
		return employee;
	}

	public void requestAdditionalAllowance() {
		System.out.println("Additional Allowance");
	}

	public void requestBenefit() {
		System.out.println("Request Benefit");
	}

	public void updateEmployee(int index, String input, int employeeId) throws NotExistException {
		Employee employee = employeeEntityModel.getById(employeeId);
		if (employee == null)
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
		switch (index) {
			case 1 -> employee.setName(input);
			case 2 -> employee.setPosition(EmployeePosition.indexOf(Integer.parseInt(input)));
			case 3 -> employee.setAddress(input);
			case 4 -> employee.setPhoneNumber(input);
			case 5 -> employee.setBankName(input);
			case 6 -> employee.setBankAccount(input);
			case 8 -> employee.setDepartmentID(Integer.parseInt(input));
			case 9 -> employee.setSalary(Integer.parseInt(input));
			default -> {
				return;
			}
		}
		employeeEntityModel.update(employee);
	}

	public List<Employee> getAllEmployee() {
		return employeeEntityModel.getAll();
	}

	public List<Department> getAllDepartment() {
		return departmentEntityModel.getAll();
	}

	public Department get(int departmentID) throws NotExistException {
		Department department = departmentEntityModel.getById(departmentID);
		if (department == null)
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		return department;
	}
}
