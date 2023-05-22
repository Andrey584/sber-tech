package sbertech.organizer.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbertech.organizer.beans.Employee;
import sbertech.organizer.beans.EmployeeList;
import sbertech.organizer.enums.ActionType;
import sbertech.organizer.enums.EmployeeParamType;
import sbertech.organizer.services.EmployeeRepositoryService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UpdateActionHandler implements EmployeeActionHandler {
    private final EmployeeRepositoryService employeeRepositoryService;

    @Autowired
    public UpdateActionHandler(EmployeeRepositoryService employeeRepositoryService) {
        this.employeeRepositoryService = employeeRepositoryService;
    }

    @Override
    public String handle(Map<EmployeeParamType, String> params) {
        EmployeeList data = employeeRepositoryService.read();
        data.getEmployeeList().stream()
                .map(e -> updateEmployeeData(e, params))
                .collect(Collectors.toList());

        employeeRepositoryService.write(data);

        return "Пользователь успешно обновлен!";
    }

    /**
     * Метод для обновления пользователя
     *
     * @param employee пользователь
     * @param params   параметры
     * @return обновленный пользователь
     */
    public Employee updateEmployeeData(Employee employee, Map<EmployeeParamType, String> params) {
        if (!employee.getNumber().equals(params.get(EmployeeParamType.NUMBER))) {
            return employee;
        }
        employee.setNumber(params.get(EmployeeParamType.NUMBER));
        employee.setName(params.get(EmployeeParamType.NAME));
        employee.setPosition(params.get(EmployeeParamType.POSITION));
        employee.setAddress(params.get(EmployeeParamType.ADDRESS));
        employee.setOrganization(params.get(EmployeeParamType.ORGANIZATION));

        String stringNumbers = params.get(EmployeeParamType.PHONE_NUMBERS);
        employee.setPhoneNumbers(
                Arrays.stream(stringNumbers.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList()));
        return employee;
    }

    @Override
    public List<EmployeeParamType> getParams() {
        System.out.println("================================================================================");
        System.out.println(employeeRepositoryService.read().getEmployeeList());
        System.out.println("================================================================================");
        System.out.println("Для обновление пользователя укажите его табельный номер и данные для изменения: ");
        System.out.println("Если вы не хотите менять данные для какого-то поля, нажмите enter");
        System.out.println("================================================================================");

        return EmployeeParamType.getValues();
    }

    @Override
    public ActionType getAction() {
        return ActionType.UPDATE;
    }
}
