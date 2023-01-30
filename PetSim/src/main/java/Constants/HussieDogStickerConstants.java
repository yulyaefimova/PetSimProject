package Constants;

/**
 * enum для скина hussie_dog
 */
public enum HussieDogStickerConstants {
    HAPPY_STICKER("CAACAgIAAxkBAAEDbNRhrlZ0KPOqNxkyW38xndeEBoeM0AACBQMAAiTZSQWIqbnwQiVhHSIE"),
    HUNGRY_STICKER("CAACAgIAAxkBAAEDbMphrlYW_I-6lTwYeTmB0X0U_FKLngACIQMAAiTZSQUK54TUGP59NCIE"),
    EXTRA_HUNGRY_STICKER("CAACAgIAAxkBAAEDbMhhrlYDpn_HKZjfoyP4qdEftb99_AACKQMAAiTZSQWcxq67Qy00LSIE"),
    SLEEP_STICKER("CAACAgIAAxkBAAEDbMxhrlYjlQwLwjTn8Bd5GReiilah7gACJwMAAiTZSQW932JM5HWTySIE"),
    PLAY_STICKER("CAACAgIAAxkBAAEDbNJhrlZlB1d84vNUSo7TawABg-nlh4EAAg4DAAIk2UkFhYpBrCuWI34iBA"),
    WALK_STICKER("CAACAgIAAxkBAAEDbMZhrlXSHAKEggR3XdaxR2gd2tSFggACEwMAAiTZSQVnpJW7JdRSsiIE"),
    SAD_STICKER("CAACAgIAAxkBAAEDbNZhrlaovZ6hzk_cjD8j1LNa35Js8wACLAMAAiTZSQVXG3F8mPz0hyIE");

    private final String value;

    HussieDogStickerConstants(String value) {
        this.value = value;
    }

    public String toStringValue() {
        return value;
    }
}
