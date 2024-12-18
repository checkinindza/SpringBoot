package kontrolins.lab2weblayer.controllers;

import com.google.gson.Gson;
import kontrolins.lab2weblayer.Model.Client;
import kontrolins.lab2weblayer.Model.User;
import kontrolins.lab2weblayer.errors.UserNotFound;
import kontrolins.lab2weblayer.repos.ClientRepo;
import kontrolins.lab2weblayer.repos.UserRepo;
import kontrolins.lab2weblayer.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClientRepo clientRepo;

    @GetMapping(value  = "allUsers")
    public @ResponseBody
    Iterable<User> getAll() {
        return userRepo.findAll();
    }

    @PostMapping(value = "createClient")
    // @ResponseBody
    public void createUser(@RequestBody Client client) {
        clientRepo.save(client);
        // return new Client();
    }

    @PostMapping(value = "validateClient")
    public Client validateClient(@RequestBody String loginInfo) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(loginInfo, Properties.class);
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        //Client client = clientRepo.getClientByLogin(login);
        Client client = clientRepo.findClientByLoginCustom(login);
        if (client != null && PasswordUtils.verifyPassword(password, client.getPassword())) {
            return client;
        }
        return null;
    }

    @DeleteMapping(value = "deleteUser/{id}")
    @ResponseBody
    public String deleteClient(@PathVariable int id) {
        userRepo.deleteById(id);
        return "Success";
    }

    @PutMapping(value = "/updateUser/{id}")
    public @ResponseBody
    String updateUser(@RequestBody String userInfoToUpdate, @PathVariable int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(userInfoToUpdate, Properties.class);

        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFound(id));
        user.setName(properties.getProperty("name"));
        user.setSurname(properties.getProperty("surname"));
        //...
        userRepo.save(user);
        return "Success";
    }

    @GetMapping("client/{id}")
    EntityModel<Client> getClientById(@PathVariable Integer id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new UserNotFound(id));

        return EntityModel.of(client, linkTo(methodOn(UserController.class).getClientById(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("allUsers"));
    }
}
