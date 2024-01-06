package blackJackSSS.BlackJack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private int chips;

}
