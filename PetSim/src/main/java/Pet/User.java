package Pet;

import java.io.Serializable;
import java.util.HashMap;

/**
 * класс пользователя
 */
public class User implements Serializable {
    /**
     * список питомцев текущего пользователя
     */
    private HashMap<String, Pet> petHashMap = new HashMap<>();

    /**
     * текущий питомец, с которым можно взаимодействовать
     */
    public Pet currentPet;

    /**
     * вид питомца, необходимо для создания новых питомцев
     */
    public String kind = "";

    /**
     * условие на создание нового питомца
     */
    public boolean havePet = false;

    /**
     * условие на переключение между питомцами
     */
    public boolean shift = false;

    /**
     * условие на покупку в магазине
     */
    public boolean buying = false;

    public HashMap<String, Pet> getPets() {
        return petHashMap;
    }

    public void setPet(String name, Pet pet) {
        if (pet != null)
            petHashMap.put(name, pet);
    }
}
