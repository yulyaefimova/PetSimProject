package commands.shop;

import Constants.GameConstants;
import Constants.PetSkins;
import Pet.User;
import commands.ICommand;

public class HussieSkinCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null)
            return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getSkin().equals(PetSkins.HUSSIE_DOG))
            return GameConstants.ALREADY_BOUGHT_SKIN;
        if (user.currentPet.getMoney() < 30)
            return GameConstants.NO_MONEY;
        user.currentPet.setSkin(PetSkins.HUSSIE_DOG);
        user.currentPet.petSkins.add(PetSkins.HUSSIE_DOG);
        user.currentPet.setMoney(user.currentPet.getMoney() - 30);
        return GameConstants.SUCCESSFUL_BUY_SKIN;
    }
}
