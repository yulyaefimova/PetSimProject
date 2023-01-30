package Api;

import Pet.Pet;
import Pet.User;

/**
 * абстрактный класс для API методов
 */
public abstract class PetApi {
    /**
     * метод для создания нового питомца
     * @param name имя для питомца
     * @param user текущий пользователь
     * @return новый питомец определенного вида
     */
    public abstract Pet create(String name, User user);

}
