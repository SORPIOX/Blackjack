package blackJackSSS.BlackJack;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class UserController {

    public static String loggedUser;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String loginTest(@RequestParam String username, @RequestParam String password, Model model){

        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedUser=username;
            // Successful login, redirect to a success page or perform any other action
            return "redirect:/home.html";
        } else {
            // Failed login, add an error message to the model and redirect back to the login page
            model.addAttribute("error", "Invalid username or password");
            return "forward:/register.html";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {

        // Check if the username already exists in the database
        if (userRepository.existsByUsername(username)) {
            // Username is taken, add an error message to the model
            model.addAttribute("error", "Username is already taken");
            // Redirect back to the registration page with an error message
            return "register.html";
        }

        // Create a new user with default chips value
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setChips(1000); // Set the default chips value

        loggedUser=username;

        // Save the user to the database or perform other registration actions
        userRepository.save(newUser);
        // Redirect to a success page or login page
        return "redirect:/home.html";

    }

    @PostMapping("/bet")
    public String testBet(@RequestParam int betAmount, Model model){
        String out="";
        User userData = new User();
        if(betAmount<userData.getChips()){
            System.out.println("Not enough chips");
        }else{
            userRepository.updateChips(loggedUser,-betAmount);
            out="redirect:/success.html";
        }
        return out;
    }

}

