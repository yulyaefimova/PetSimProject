package Constants;

/**
 * константы с id стикеров собаки
 */
public enum DogStickerConstants {
    HAPPY_STICKER("CAACAgIAAxkBAAEDV1Jhm_AtBsGJ_jsyvmn5E_XhM2qt4wACWAADW__yCpRmb5wHENngIgQ"),
    HUNGRY_STICKER("CAACAgIAAxkBAAEDV1Zhm_BPIh3N6p1v88uFOSlNE4py9AACTgADW__yCnkO_fpSiemHIgQ"),
    EXTRA_HUNGRY_STICKER("CAACAgIAAxkBAAEDV1hhm_BVcmoWGvxFehp22ImPpIi3SAACWgADW__yCvyGD9nhtz_YIgQ"),
    SLEEP_STICKER("CAACAgIAAxkBAAEDV1xhm_B0dQo2ZYgNHcmxUNteCK_kawACRgADW__yCgWnSbK3tTOWIgQ"),
    PLAY_STICKER("CAACAgIAAxkBAAEDV1Rhm_A5xoUT54J0SH9lqLLhbLhlaAACbwADW__yChI22caWFS9pIgQ"),
    WALK_STICKER("CAACAgIAAxkBAAEDV1phm_Be-vGHUQABgnfqzrh89Xf-7RgAAisCAAJb__IK3pFGa99YOjsiBA"),
    SAD_STICKER("CAACAgIAAxkBAAEDV15hm_CLCZe3Wzp4Kga_ltyjCQKPEQACUgADW__yCmqiDxK4ZxozIgQ");

    /**
     * Конструкция присвоения enum констант с их значениями
     */
    private final String value;
    DogStickerConstants(String value) {
        this.value = value;
    }
    public String toStringValue() {
        return value;
    }
}
