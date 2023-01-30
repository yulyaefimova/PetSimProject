package Constants;

/**
 * константы для питомца типа Собака
 */
public enum DogConstants {
    PLAY_MOOD("Мой мячик лежит без дела... Я хочу поиграть!"),
    SAD_MOOD("Мне грустно и одиноко!"),
    SLEEP_MOOD("Хочу спать!\uD83D\uDCA4"),
    WALK_MOOD("Мне нужно на улицу! Пойдем гулять!\uD83D\uDC15"),
    HUNGRY_MOOD("Я голодный как волк!\uD83D\uDC3A"),
    EXTRA_HUNGRY_MOOD("Я умираю от голода! Покорми меня!☠"),
    HAPPY_MOOD("Я счастлив!\uD83D\uDC95");

    /**
     * Конструкция присвоения enum констант с их значениями
     */
    private final String value;

    DogConstants(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}
