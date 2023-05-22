package sbertech.organizer.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbertech.organizer.enums.ActionType;
import sbertech.organizer.enums.EmployeeParamType;
import sbertech.organizer.beans.EmployeeList;
import sbertech.organizer.services.EmployeeRepositoryService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ReadAllActionHandler implements sbertech.organizer.handlers.EmployeeActionHandler {
    private final EmployeeRepositoryService employeeRepositoryService;

    @Autowired
    public ReadAllActionHandler(EmployeeRepositoryService employeeRepositoryService) {
        this.employeeRepositoryService = employeeRepositoryService;
    }


    @Override
    public String handle(Map<EmployeeParamType, String> params) {
        EmployeeList employeeList = employeeRepositoryService.read();
        return employeeList.toString();
    }

    @Override
    public List<EmployeeParamType> getParams() {
        return Collections.emptyList();
    }

    @Override
    public ActionType getAction() {
        return ActionType.READ_ALL;
    }

}
