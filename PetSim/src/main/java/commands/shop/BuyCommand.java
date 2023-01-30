package commands.shop;

import Constants.CreationConstants;
import Pet.User;
import commands.ICommand;

/**
 * класс с методом для покупок в магазине
 */
public class BuyCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        user.buying = true;
        return CreationConstants.WHAT_PET.toStringValue();
    }
}
