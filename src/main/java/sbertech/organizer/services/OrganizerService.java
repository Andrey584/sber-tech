package sbertech.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbertech.organizer.enums.ActionType;
import sbertech.organizer.enums.EmployeeParamType;
import sbertech.organizer.handlers.EmployeeActionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrganizerService {

    private final Map<ActionType, EmployeeActionHandler> handlers;

    @Autowired
    public OrganizerService(List<EmployeeActionHandler> handlers) {
        this.handlers = handlers.stream()
                .collect(Collectors.toMap(EmployeeActionHandler::getAction, Function.identity()));
    }

    public void run() {
        while (true) {
            ActionType actionType = chooseAction();
            if (actionType.equals(ActionType.EXIT)) {
                return;
            }
            EmployeeActionHandler employeeActionHandler = handlers.get(actionType);
            Map<EmployeeParamType, String> params = fillParamsData(employeeActionHandler.getParams());
            String result = employeeActionHandler.handle(params);
            System.out.println(result);
            System.out.println("================================================================================");
            System.out.println("================================================================================");
        }
    }

    /**
     * Заполнение данных пользователем для определения типа действия
     *
     * @return тип действия
     */
    public ActionType chooseAction() {
        System.out.println("================================================================================");
        System.out.println("Выберите тип действия: ");
        ActionType.getValues().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        int code = scanner.nextInt();
        ActionType actionType = ActionType.getByCode(code);

        if (actionType.equals(ActionType.UNKNOWN)) {
            System.out.println("================================================================================");
            System.out.println("Неизвестное действие! Пожалуйста, укажите верный код действия");
            chooseAction();
        }

        return actionType;

    }

    /**
     * Заполнение данных пользователем по списку параметров
     *
     * @param employeeParamType список типов параметров
     * @return заполненные параметры
     */
    public Map<EmployeeParamType, String> fillParamsData(List<EmployeeParamType> employeeParamType) {
        Map<EmployeeParamType, String> result = new HashMap<>();
        System.out.println("================================================================================");
        for (EmployeeParamType ec : employeeParamType) {
            System.out.println("Пожалуйста, введите данные: " + ec.getName());
            Scanner scanner = new Scanner(System.in);
            String inputData = scanner.nextLine();
            result.put(ec, inputData);
        }
        return result;
    }

}
