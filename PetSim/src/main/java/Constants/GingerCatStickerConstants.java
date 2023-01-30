package Constants;

/**
 * enum для скина ginger_cat
 */
public enum GingerCatStickerConstants {
    HAPPY_STICKER("CAACAgIAAxkBAAEDbKhhrlQq5xelW_gtIck555nGi6btkwACEwAD6dgTKMzgTmbUB0_sIgQ"),
    HUNGRY_STICKER("CAACAgIAAxkBAAEDbK5hrlRq2RIZO7AwGOa54va_Njx8KQACCgAD6dgTKGnSgBkGwWd-IgQ"),
    EXTRA_HUNGRY_STICKER("CAACAgIAAxkBAAEDbKxhrlRRIlDUazIxjPRrHloaiJbKUAACEgAD6dgTKP0tlTMOVAKAIgQ"),
    SLEEP_STICKER("CAACAgIAAxkBAAEDbLBhrlR6Oy9oR1VnZt9pvlVcRzE9eQACBQAD6dgTKJt_KcBZLJ0gIgQ"),
    PLAY_STICKER("CAACAgIAAxkBAAEDbKphrlRBGRVdSJMMwgYyWiYbC9cpfwACBwAD6dgTKBP20MIxUQsUIgQ"),
    WALK_STICKER("CAACAgIAAxkBAAEDbLRhrlSgk1iLVosNseDPfiReHhID6wACHwAD6dgTKAgaGXInFJ3iIgQ"),
    SAD_STICKER("CAACAgIAAxkBAAEDbLJhrlSEWD-g0ggO0y8Ex45RWQG-QQACFwAD6dgTKJCo937df3FtIgQ");

    private final String value;

    GingerCatStickerConstants(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}
