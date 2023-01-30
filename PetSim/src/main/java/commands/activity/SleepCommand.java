package commands.activity;

import Constants.GameConstants;
import Pet.User;
import commands.ICommand;

/**
 * класс с методом выполнения команды /sleep
 */
public class SleepCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null) return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getIsBusy())
            return user.currentPet.name + user.currentPet.getwhatPetDoNow();
        user.currentPet.sleep();
        if (!user.currentPet.getUnableToDoActionMessage().equals(""))
            return user.currentPet.getUnableToDoActionMessage();
        return user.currentPet.name + GameConstants.PET_SLEEP;
    }
}
