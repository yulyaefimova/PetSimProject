package commands;

import Constants.GameConstants;
import Pet.User;

/**
 * класс с методом вывода баланса
 */
public class BalanceCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null) return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getMoney() == 1) return user.currentPet.getMoney() + " coin";
        else return user.currentPet.getMoney() + " coins";
    }
}
