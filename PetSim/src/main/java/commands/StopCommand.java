package commands;

import Constants.GameConstants;
import Pet.User;

/**
 * класс с методом выполнения команды стоп
 */
public class StopCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        user.havePet = false;
        return GameConstants.STOP;
    }
}
