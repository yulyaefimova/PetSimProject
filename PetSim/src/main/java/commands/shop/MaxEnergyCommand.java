package commands.shop;

import Constants.GameConstants;
import Constants.Improvements;
import Pet.User;
import commands.ICommand;

/**
 * класс с методом для покупки улучшения maxEnergy
 */
public class MaxEnergyCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null) return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getMoney() < 25)
            return GameConstants.NO_MONEY;
        if (user.currentPet.getImprovements().contains(Improvements.MAX_ENERGY))
            return GameConstants.ALREADY_BOUGHT_IMPROVEMENT;
        user.currentPet.setImprovements(Improvements.MAX_ENERGY);
        user.currentPet.setMoney(user.currentPet.getMoney() - 25);
        return GameConstants.BUY_MAX_ENERGY;
    }
}
