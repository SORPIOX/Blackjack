package blackJackSSS.BlackJack;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUsername(String username);

    boolean existsByUsername(String username);

    public default void updateChips(String username, int amount){
        User user = findByUsername(username);
        System.out.println(user);
        if(user != null){
            System.out.println("user found");
            user.setChips(user.getChips() + amount);
            save(user);
        }
    }

}
