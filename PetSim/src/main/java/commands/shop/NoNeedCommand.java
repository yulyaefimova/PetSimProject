package commands.shop;

import Constants.GameConstants;
import Constants.Improvements;
import Pet.User;
import commands.ICommand;

/**
 * класс с методом для покупки улучшения noNeed
 */
public class NoNeedCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null) return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getMoney() < 25)
            return GameConstants.NO_MONEY;
        if (user.currentPet.getImprovements().contains(Improvements.NO_NEED))
            return GameConstants.ALREADY_BOUGHT_IMPROVEMENT;
        user.currentPet.setImprovements(Improvements.NO_NEED);
        user.currentPet.setMoney(user.currentPet.getMoney() - 25);
        return GameConstants.BUY_NO_NEED;
    }
}