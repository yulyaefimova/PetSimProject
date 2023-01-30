package commands;

import Constants.GameConstants;
import Pet.User;

/**
 * класс с методом выполнения команды старт
 */
public class StartCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        return GameConstants.START;
    }
}
