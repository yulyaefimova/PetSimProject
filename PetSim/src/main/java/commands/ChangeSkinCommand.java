package commands;

import Constants.GameConstants;
import Constants.PetSkins;
import Pet.User;

public class ChangeSkinCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null)
            return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getSkin().equals(PetSkins.STANDART_CAT)
                && user.currentPet.petSkins.contains(PetSkins.GINGER_CAT)) {
            user.currentPet.setSkin(PetSkins.GINGER_CAT);
            return GameConstants.SUCCESSFUL_CHANGE_SKIN;
        }
        if (user.currentPet.getSkin().equals(PetSkins.GINGER_CAT)) {
            user.currentPet.setSkin(PetSkins.STANDART_CAT);
            return GameConstants.SUCCESSFUL_CHANGE_SKIN;
        }
        if (user.currentPet.getSkin().equals(PetSkins.STANDART_DOG)
                && user.currentPet.petSkins.contains(PetSkins.HUSSIE_DOG)) {
            user.currentPet.setSkin(PetSkins.HUSSIE_DOG);
            return GameConstants.SUCCESSFUL_CHANGE_SKIN;
        }

        if (user.currentPet.getSkin().equals(PetSkins.HUSSIE_DOG)) {
            user.currentPet.setSkin(PetSkins.STANDART_DOG);
            return GameConstants.SUCCESSFUL_CHANGE_SKIN;
        }
        return GameConstants.NO_MORE_SKINS;
    }
}