package commands.shop;

import Constants.GameConstants;
import Constants.Improvements;
import Pet.User;
import commands.ICommand;

/**
 * класс с методом для покупки улучшения moreMoney
 */
public class MoreMoneyCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null) return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getMoney() < 30)
            return GameConstants.NO_MONEY;
        if (user.currentPet.getImprovements().contains(Improvements.MORE_MONEY))
            return GameConstants.ALREADY_BOUGHT_IMPROVEMENT;
        user.currentPet.setImprovements(Improvements.MORE_MONEY);
        user.currentPet.setMoney(user.currentPet.getMoney() - 30);
        return GameConstants.BUY_MORE_MONEY;
    }
}
