package sbertech.organizer.handlers;

import sbertech.organizer.enums.ActionType;
import sbertech.organizer.enums.EmployeeParamType;

import java.util.List;
import java.util.Map;

public interface EmployeeActionHandler {

    String handle(Map<EmployeeParamType, String> params);

    List<EmployeeParamType> getParams();

    ActionType getAction();


}
