package commands.shop;

import Constants.GameConstants;
import Constants.Improvements;
import Pet.User;
import commands.ICommand;

/**
 * класс с методом для покупки всех улучшений
 */
public class AllCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null) return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getMoney() < 90)
            return GameConstants.NO_MONEY;
        if (user.currentPet.getImprovements().contains(Improvements.ALL))
            return GameConstants.ALREADY_BOUGHT_IMPROVEMENT;
        user.currentPet.setImprovements(Improvements.ALL);
        user.currentPet.setMoney(user.currentPet.getMoney() - 90);
        return GameConstants.BUY_ALL;
    }
}