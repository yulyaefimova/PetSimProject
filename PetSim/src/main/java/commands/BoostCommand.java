package commands;

import Constants.GameConstants;
import Pet.User;

/**
 * класс с методом для ускорения выполнения команд
 */
public class BoostCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null) return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getMoney() < 2)
            return GameConstants.NO_MONEY;
        user.currentPet.setMoney(user.currentPet.getMoney() - 2);
        user.currentPet.isPay = true;
        return GameConstants.BOOSTED;
    }
}
