package sbertech.organizer.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sbertech.organizer.beans.Employee;
import sbertech.organizer.beans.EmployeeList;
import sbertech.organizer.enums.ActionType;
import sbertech.organizer.enums.EmployeeParamType;
import sbertech.organizer.services.EmployeeRepositoryService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CreateActionHandler implements EmployeeActionHandler {

    private final EmployeeRepositoryService employeeRepositoryService;

    @Autowired
    public CreateActionHandler(EmployeeRepositoryService employeeRepositoryService) {
        this.employeeRepositoryService = employeeRepositoryService;
    }

    @Override
    public String handle(Map<EmployeeParamType, String> params) {
        EmployeeList data = employeeRepositoryService.read();
        Employee newEmployee = getEmployee(params);
        data.getEmployeeList().add(newEmployee);
        employeeRepositoryService.write(data);

        return "Пользователь успешно добавлен!" + newEmployee;
    }

    @Override
    public List<EmployeeParamType> getParams() {
        return EmployeeParamType.getValues();
    }

    @Override
    public ActionType getAction() {
        return ActionType.CREATE;
    }

    private Employee getEmployee(Map<EmployeeParamType, String> params) {
        Employee employee = new Employee();
        employee.setNumber(params.get(EmployeeParamType.NUMBER));
        employee.setName(params.get(EmployeeParamType.NAME));
        employee.setPosition(params.get(EmployeeParamType.POSITION));
        employee.setOrganization(params.get(EmployeeParamType.ORGANIZATION));
        employee.setAddress(params.get(EmployeeParamType.ADDRESS));
        String stringNumber = params.get(EmployeeParamType.PHONE_NUMBERS);

        employee.setPhoneNumbers(
                Arrays.stream(stringNumber.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList())
        );

        return employee;
    }
}
