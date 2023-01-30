package Api;

import Pet.Dog;
import Pet.User;

public class DogApi extends PetApi {
    @Override
    public Dog create(String name, User user) {
        user.setPet(name, new Dog(name));
        return (Dog) user.getPets().get(name);
    }
}
