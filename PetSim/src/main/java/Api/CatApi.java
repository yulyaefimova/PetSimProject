package Api;

import Pet.Cat;
import Pet.User;

public class CatApi extends PetApi {
    @Override
    public Cat create(String name, User user) {
        user.setPet(name, new Cat(name));
        return (Cat) user.getPets().get(name);
    }
}
