package commands;

import Constants.GameConstants;
import Pet.User;

/**
 * класс для команды получения списка питомцев пользователя
 */
public class GetPetsCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        StringBuilder pets = new StringBuilder();
        if (user.getPets() == null) return GameConstants.ERROR_MESSAGE;
        for (String name: user.getPets().keySet()) {
            pets.append(name).append("\n");
        }
        return pets.toString();
    }
}
