package Pet;

import Constants.CatConstants;
import Constants.CatStickerConstants;
import Constants.GingerCatStickerConstants;
import Constants.PetSkins;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * класс питомца типа Кошка
 */
public class Cat extends Pet implements Serializable {
    public Cat(String name) {
        super(name);
    }

    @Override
    public String checkState() {
        if (communication <= 2)
            return CatConstants.SAD_MOOD.toStringValue();
        if (hunger <= 4 && hunger > 1)
            return CatConstants.HUNGRY_MOOD.toStringValue();
        if (hunger <= 1)
            return CatConstants.EXTRA_HUNGRY_MOOD.toStringValue();
        if (leisure <= 2)
            return CatConstants.PLAY_MOOD.toStringValue();
        if (need <= 3)
            return CatConstants.WALK_MOOD.toStringValue();
        if (energy <= 3)
            return CatConstants.SLEEP_MOOD.toStringValue();
        return CatConstants.HAPPY_MOOD.toStringValue();
    }

    @Override
    public String checkStickerState() {
        if (currentSkin.equals(PetSkins.STANDART_CAT)) {
            if (communication <= 2)
                return CatStickerConstants.SAD_STICKER.toStringValue();
            if (hunger <= 4 && hunger > 1)
                return CatStickerConstants.HUNGRY_STICKER.toStringValue();
            if (hunger <= 1)
                return CatStickerConstants.EXTRA_HUNGRY_STICKER.toStringValue();
            if (leisure <= 2)
                return CatStickerConstants.PLAY_STICKER.toStringValue();
            if (need <= 3)
                return CatStickerConstants.WALK_STICKER.toStringValue();
            if (energy <= 3)
                return CatStickerConstants.SLEEP_STICKER.toStringValue();
            return CatStickerConstants.HAPPY_STICKER.toStringValue();
        } else {
            if (communication <= 2)
                return GingerCatStickerConstants.SAD_STICKER.toStringValue();
            if (hunger <= 4 && hunger > 1)
                return GingerCatStickerConstants.HUNGRY_STICKER.toStringValue();
            if (hunger <= 1)
                return GingerCatStickerConstants.EXTRA_HUNGRY_STICKER.toStringValue();
            if (leisure <= 2)
                return GingerCatStickerConstants.PLAY_STICKER.toStringValue();
            if (need <= 3)
                return GingerCatStickerConstants.WALK_STICKER.toStringValue();
            if (energy <= 3)
                return GingerCatStickerConstants.SLEEP_STICKER.toStringValue();
            return GingerCatStickerConstants.HAPPY_STICKER.toStringValue();
        }
    }
}
