package commands;

import Constants.CreationConstants;
import Pet.User;

/**
 * класс с методом выбора вида питомца
 */
public class PetCommand implements ICommand {

    @Override
    public String execute(String message, User user) {
        user.havePet = true;
        user.kind = message;
        return CreationConstants.NAME_QUESTION.toStringValue();
    }
}
