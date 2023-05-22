package sbertech.organizer.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EmployeeParamType {
    NUMBER(1, "Табельный номер"),
    NAME(2, "Фамилия И.О."),
    POSITION(3, "Должность"),
    ORGANIZATION(4, "Название организации"),
    ADDRESS(5, "Адрес"),
    PHONE_NUMBERS(6, "Список номеров телефонов через запятую"),
    UNKNOWN(-1, "Неизвестный тип параметра");

    private final Integer code;
    private final String name;

    EmployeeParamType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * Метод для определения параметра по коду
     *
     * @param code код параметра
     * @return параметр
     */
    public static EmployeeParamType getByCode(Integer code) {
        return Arrays.stream(EmployeeParamType.values())
                .filter(e -> e.code.equals(code))
                .findAny()
                .orElse(UNKNOWN);
    }

    /**
     * Метод для возврата всех параметров
     *
     * @return список параметров
     */
    public static List<EmployeeParamType> getValues() {
        return Arrays.stream(values())
                .filter(t -> !t.equals(EmployeeParamType.UNKNOWN))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }

}
