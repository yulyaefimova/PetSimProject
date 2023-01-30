package Pet;

import Constants.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * класс питомца типа Собака
 */
public class Dog extends Pet implements Serializable {
    public Dog(String name) {
        super(name);
    }

    @Override
    public String checkState() {
        if (communication <= 2)
            return DogConstants.SAD_MOOD.toStringValue();
        if (hunger <= 4 && hunger > 1)
            return DogConstants.HUNGRY_MOOD.toStringValue();
        if (hunger <= 1)
            return DogConstants.EXTRA_HUNGRY_MOOD.toStringValue();
        if (leisure <= 2)
            return DogConstants.PLAY_MOOD.toStringValue();
        if (need <= 3)
            return DogConstants.WALK_MOOD.toStringValue();
        if (energy <= 3)
            return DogConstants.SLEEP_MOOD.toStringValue();
        return DogConstants.HAPPY_MOOD.toStringValue();
    }

    @Override
    public String checkStickerState() {
        if (currentSkin.equals(PetSkins.STANDART_DOG)) {
            if (communication <= 2)
                return DogStickerConstants.SAD_STICKER.toStringValue();
            if (hunger <= 4 && hunger > 1)
                return DogStickerConstants.HUNGRY_STICKER.toStringValue();
            if (hunger <= 1)
                return DogStickerConstants.EXTRA_HUNGRY_STICKER.toStringValue();
            if (leisure <= 2)
                return DogStickerConstants.PLAY_STICKER.toStringValue();
            if (need <= 3)
                return DogStickerConstants.WALK_STICKER.toStringValue();
            if (energy <= 3)
                return DogStickerConstants.SLEEP_STICKER.toStringValue();
            return DogStickerConstants.HAPPY_STICKER.toStringValue();
        }
       else {
            if (communication <= 2)
                return HussieDogStickerConstants.SAD_STICKER.toStringValue();
            if (hunger <= 4 && hunger > 1)
                return HussieDogStickerConstants.HUNGRY_STICKER.toStringValue();
            if (hunger <= 1)
                return HussieDogStickerConstants.EXTRA_HUNGRY_STICKER.toStringValue();
            if (leisure <= 2)
                return HussieDogStickerConstants.PLAY_STICKER.toStringValue();
            if (need <= 3)
                return HussieDogStickerConstants.WALK_STICKER.toStringValue();
            if (energy <= 3)
                return HussieDogStickerConstants.SLEEP_STICKER.toStringValue();
            return HussieDogStickerConstants.HAPPY_STICKER.toStringValue();
        }
    }
}
