package commands;

import Api.CatApi;
import Api.DogApi;
import Constants.CreationConstants;
import Constants.GameConstants;
import Pet.*;

/**
 * класс с методом обработки случая неизвестной команды
 */
public class NoCommand implements ICommand{
    private final CatApi catApi = new CatApi();
    private final DogApi dogApi = new DogApi();
    @Override
    public String execute(String message, User user) {
        if (user.havePet) {
            if (message.equals("")) return CreationConstants.EMPTY_NAME.toStringValue();
            if (user.getPets().containsKey(message))
                return CreationConstants.NAME_CONFLICT.toStringValue();
            user.havePet = false;
            if (user.kind.equals("/dog"))
                dogApi.create(message, user);
            else catApi.create(message, user);
            user.currentPet = user.getPets().get(message);
            return CreationConstants.NEW_PET.toStringValue() + message;
        }
        if (user.buying) {
            if (user.getPets().containsKey(message)) {
                user.buying = false;
                user.currentPet = user.getPets().get(message);
                return GameConstants.CHOOSE_TO_BUY + message;
            }
            else return CreationConstants.NON_EXISTENT_PET.toStringValue();
        }
        if (user.shift) {
            if (user.getPets().containsKey(message)) {
                user.shift = false;
                user.currentPet = user.getPets().get(message);
                return message + GameConstants.HERE;
            } else return CreationConstants.NON_EXISTENT_PET.toStringValue();
        }
        return GameConstants.UNKNOWN_COMMAND;
    }
}
