package commands;

import Constants.GameConstants;
import Pet.User;

/**
 * класс с методом переключения между питомцами
 */
public class ShiftCommand implements ICommand{
    @Override
    public String execute(String message, User user) {
        user.shift = true;
        return GameConstants.CHOOSE_TOSHIFT;
    }
}
