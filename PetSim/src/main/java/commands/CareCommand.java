package commands;

import Constants.GameConstants;
import Pet.User;

/**
 * класс с методом для вывода команд по уходу за питомцем
 */
public class CareCommand implements ICommand {
    @Override
    public String execute(String message, User user) {
        if (user.currentPet == null) return GameConstants.ERROR_MESSAGE;
        if (user.currentPet.getIsBusy())
            return user.currentPet.name + user.currentPet.getwhatPetDoNow();
        return """
                Для ухода за своим питомцем пользуйтесь следующими командами:
                /sleep - отправить его спать
                /eat - покормить
                /stroke - погладить
                /walk - отправить гулять
                /play - поиграть с ним\s""";
    }
}
