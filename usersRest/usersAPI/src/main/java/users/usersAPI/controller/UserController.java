package users.usersAPI.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import users.usersAPI.model.dto.UserRequest;
import users.usersAPI.model.entity.User;
import users.usersAPI.model.repository.PhoneRepository;
import users.usersAPI.model.repository.UserRepository;
import users.usersAPI.service.Impl.UserServiceImpl;

import java.awt.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    // create a clock
    Clock cl = Clock.systemUTC();

    // create an LocalDateTime object using now(Clock)
    LocalDateTime lt
            = LocalDateTime.now(cl);

    @Autowired
    private PhoneRepository phoneRepository;

    public void UserController(UserRepository userRepository, UserServiceImpl userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //GET localhost:8080/api/v1/users/
    @GetMapping("/")
    @Operation(summary = "Get all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(schema = @Schema(implementation = Cursor.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "204", description = "NO CONTENT", content = {
                    @Content(schema = @Schema(implementation = Cursor.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = {
                    @Content(schema = @Schema())
            })
    })
    public ResponseEntity<List<UserRequest>> getALl(){
        List<UserRequest> users = userService.getAllUsers();

        if(!users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //GET localhost:8080/api/v1/users/{1}
    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUserById(@PathVariable int id) {
        UserRequest userFound = userService.getUser(id);
        if(userFound != null){
            return new ResponseEntity<>(userFound, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //POST localhost:8080/api/v1/users/userRequest
    @PostMapping("/")
    public ResponseEntity<UserRequest> createUser(@RequestBody User user) {
        UserRequest userSave = userService.saveUser(user);
        return new ResponseEntity<>(userSave, HttpStatus.CREATED);
    }

    //PUT localhost:8080/api/v1/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UserRequest> updateUser(
            @PathVariable int id, @RequestBody User user) {
        UserRequest userSave = userService.updateUser(user, id);
        return new ResponseEntity<>(userSave, HttpStatus.CREATED);
    }

    //DELETE localhost:8080/api/v1/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        String deletedUser = userService.deleteUser(id);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}
