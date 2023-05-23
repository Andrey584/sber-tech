package sbertech.organizer.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ActionType {
    CREATE(1, "Добавить пользователя"),
    READ_ALL(2, "Получить список пользователей"),
    READ(3, "Получить пользователя по атрибутам"),
    UPDATE(4, "Редактировать пользователя"),
    EXIT(5, "Выйти из программы"),
    UNKNOWN(-1, "Неизвестный тип действия");

    private final Integer code;
    private final String name;

    ActionType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ActionType getByCode(Integer code) {
        return Arrays.stream(ActionType.values())
                .filter(action -> action.code.equals(code))
                .findAny()
                .orElse(UNKNOWN);
    }

    public static List<ActionType> getValues() {
        return Arrays.stream(values())
                .filter(t -> !t.equals(ActionType.UNKNOWN))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }

}
