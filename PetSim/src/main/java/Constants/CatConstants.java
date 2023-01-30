package Constants;

/**
 * Константы для питомца типа Кошка
 */
public enum CatConstants {
    PLAY_MOOD("Поиграй со мной лазерной указкой \uD83D\uDE3A"),
    SAD_MOOD("Мне грустно и одиноко! Мяу.\uD83D\uDE3E"),
    SLEEP_MOOD("Мне пора спать!\uD83D\uDCA4"),
    WALK_MOOD("Мне нужно прогуляться одному. Умыться и сделать свои кошачьи дела.\uD83D\uDC3E"),
    HUNGRY_MOOD("Рыбки хочется... Я голоден!\uD83D\uDE3F"),
    EXTRA_HUNGRY_MOOD("Я умираю от голода! Покорми меня!\uD83D\uDE40"),
    HAPPY_MOOD("Хороша кошачья жизнь.. Я счастлив!\uD83D\uDE38");

    private final String value;

    CatConstants(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}