package commands.shop;

import Constants.CreationConstants;
import Constants.GameConstants;
import Pet.User;
import commands.ICommand;

/**
 * класс для вывода доступных к покупке скинов
 */
public class SkinsCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null)
            return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getClass().getSimpleName().equals("Dog"))
            return """
               Скины для твоего питомца:
                /hussie_skin собака - хаски(30 coins).\s""";
        else return """
               Скины для твоего питомца:
                /ginger_skin - рыжий кот(30 coins).\s""";
    }
}