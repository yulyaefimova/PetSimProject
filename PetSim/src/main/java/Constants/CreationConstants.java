package Constants;

/**
 * константы для создания питомца
 */
public enum CreationConstants {
    CHOOSE_PET("Пиши /dog или /cat для создания своего питомца."),
    NAME_QUESTION("Как ты его назовешь?"),
    NAME_CONFLICT("У тебя уже есть питомец с таким именем!"),
    EMPTY_NAME("У него не может быть пустого имени :("),
    NEW_PET("Теперь у тебя есть новый питомец по имени "),
    NON_EXISTENT_PET("У тебя нет такого питомца!"),
    WHAT_PET("Напиши имя питомца, для которого хочешь купить улучшение.");
    private final String value;

    CreationConstants(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}
