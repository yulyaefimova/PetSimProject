package Pet;

import java.io.*;
import java.util.HashMap;

/**
 * класс для сериализации данных пользователя
 */
public class Serializator implements Serializable {
    /**
     * метод, для сохранения данных пользователей
     *
     * @param users пользователи
     */
    public static void serialize(HashMap<String, User> users) {
        try {
            FileOutputStream fos = new FileOutputStream("users.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод для скачивания данных пользователей
     *
     * @return HashMap с пользователями
     */
    public static HashMap<String, User> deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("users.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        HashMap<String, User> users = (HashMap<String, User>) ois.readObject();
        ois.close();
        return users;
    }
}
