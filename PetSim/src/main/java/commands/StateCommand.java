package commands;

import Constants.GameConstants;
import Pet.User;

/**
 * класс с методом вывода состояния питомца
 */
public class StateCommand implements ICommand{
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null) return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getIsBusy())
            return user.currentPet.name + user.currentPet.getwhatPetDoNow();
        return user.currentPet.checkState();
    }
}
