package Constants;

/**
 * константы с id стикеров кошки
 */
public enum CatStickerConstants {
    HAPPY_STICKER("CAACAgIAAxkBAAEDV_xhnMXKDZOz0iLlL4joDhhvwziQ1QACowADECECEI0MfB_BOTEXIgQ"),
    HUNGRY_STICKER("CAACAgIAAxkBAAEDVyNhm-bHMw65FVBqfafokytgpLxSygACyQADECECEM7XMjapqTitIgQ"),
    EXTRA_HUNGRY_STICKER("CAACAgIAAxkBAAEDVyVhm-bQ_B5LCWeCSuwgt73hi2L0uAACwwADECECEO9cknaA_x7dIgQ"),
    SLEEP_STICKER("CAACAgIAAxkBAAEDV0hhm-1gc2w-_tNEYq6Em3xuW8vsawACvwADECECEBZlpBi_ZOGGIgQ"),
    PLAY_STICKER("CAACAgIAAxkBAAEDV05hm-437Bu-98n8ehkmAT6Nqk26GgACugADECECEH_7IUujSHABIgQ"),
    WALK_STICKER("CAACAgIAAxkBAAEDVylhm-cXCC9kMEIG5Km8oegO6GRZ7gACpgADECECEIR8dV5va9yuIgQ"),
    SAD_STICKER("CAACAgIAAxkBAAEDVydhm-boTPuYQX_VAAGhR1L6MXwlSG4AAqcAAxAhAhAKIXbgQLAcYyIE");

    private final String value;
    CatStickerConstants(String value) {
        this.value = value;
    }
    public String toStringValue() {
        return value;
    }
}
