package commands;

import Constants.CreationConstants;
import Pet.User;

/**
 * класс с методом для создания питомца
 */
public class CreateCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        return CreationConstants.CHOOSE_PET.toStringValue();
    }
}
