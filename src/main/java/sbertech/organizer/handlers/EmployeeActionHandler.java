package sbertech.organizer.handlers;

import sbertech.organizer.enums.ActionType;
import sbertech.organizer.enums.EmployeeParamType;

import java.util.List;
import java.util.Map;

/**
 * Общий интерфейс обработчика действия
 */
public interface EmployeeActionHandler {

    /**
     * Выполнение действия
     *
     * @param params входящие параметры от пользователя
     * @return результат обработки в строковом виде
     */
    String handle(Map<EmployeeParamType, String> params);

    /**
     * Возвращает список типов параметров, необходимых обработчику для выполнения действия
     */
    List<EmployeeParamType> getParams();

    /**
     * Возвращает тип действия, выполняющегося обработчиком
     */
    ActionType getAction();


}
