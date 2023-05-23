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
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ReadActionHandler implements EmployeeActionHandler {

    private final EmployeeRepositoryService employeeRepositoryService;
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Autowired
    public ReadActionHandler(EmployeeRepositoryService employeeRepositoryService) {
        this.employeeRepositoryService = employeeRepositoryService;
    }

    @Override
    public String handle(Map<EmployeeParamType, String> params) {
        EmployeeList employeeList = employeeRepositoryService.read();
        List<Employee> filterEmployees = employeeList.getEmployeeList().stream()
                .filter(e -> isMatchesConstraint(e, params))
                .collect(Collectors.toList());

        EmployeeList resultList = new EmployeeList();
        resultList.setEmployeeList(filterEmployees);

        return resultList.toString();
    }

    @Override
    public List<EmployeeParamType> getParams() {
        System.out.println("================================================================================");
        System.out.println("Введите номера параметров для поиска пользователя через запятую: ");
        EmployeeParamType.getValues().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        String stringParamsCode = scanner.nextLine();

        return Arrays.stream(stringParamsCode.split(","))
                .map(String::trim)
                .filter(this::isNumeric)
                .map(Integer::valueOf)
                .map(EmployeeParamType::getByCode)
                .collect(Collectors.toList());
    }

    @Override
    public ActionType getAction() {
        return ActionType.READ;
    }


    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    private boolean isMatchesConstraint(Employee e, Map<EmployeeParamType, String> params) {
        String numberValue = params.get(EmployeeParamType.NUMBER);
        String nameValue = params.get(EmployeeParamType.NAME);
        String positionValue = params.get(EmployeeParamType.POSITION);
        String organizationValue = params.get(EmployeeParamType.ORGANIZATION);
        String addressValue = params.get(EmployeeParamType.ADDRESS);
        String phoneNumbersValue = params.get(EmployeeParamType.PHONE_NUMBERS);

        return (numberValue == null || numberValue.equals(e.getNumber()))
                && (nameValue == null || nameValue.equals(e.getName()))
                && (positionValue == null || positionValue.equals(e.getPosition()))
                && (organizationValue == null || organizationValue.equals(e.getOrganization()))
                && (addressValue == null || addressValue.equals(e.getAddress()))
                && (phoneNumbersValue == null || e.getPhoneNumbers().contains(phoneNumbersValue));
    }
}
