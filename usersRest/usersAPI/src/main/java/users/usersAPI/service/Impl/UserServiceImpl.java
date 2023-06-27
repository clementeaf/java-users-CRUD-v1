package users.usersAPI.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.usersAPI.model.dto.UserRequest;
import users.usersAPI.model.entity.Phone;
import users.usersAPI.model.entity.User;
import users.usersAPI.model.repository.PhoneRepository;
import users.usersAPI.model.repository.UserRepository;
import users.usersAPI.service.IUserService;

import java.lang.module.ResolutionException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    // create a clock
    Clock cl = Clock.systemUTC();

    // create an LocalDateTime object using now(Clock)
    LocalDateTime lt
            = LocalDateTime.now(cl);

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public void UserRepositoryImp(
            UserRepository userRepository,
            PhoneRepository phoneRepository){
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<UserRequest> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserRequest> usersDto = new ArrayList<>();

        for(User user: users){
            usersDto.add(UserRequest.mapToDto(user));
        }

        return usersDto;
    }

    @Override
    public UserRequest getUser(int id){
        User userFound = userRepository.findById(id).orElseThrow(() -> new ResolutionException("User not found"));
        return UserRequest.mapToDto(userFound);
    }

    @Override
    public UserRequest saveUser(User request) {
        List<User> users = userRepository.findAll();
        for (User user: users){
            if (user.getMail() == request.getMail()) {
                return UserRequest.mapToDto(user);
            }
        }
        for (Phone phone : request.getPhones()) {
            phone.setUser(request);
            phoneRepository.save(phone);
        }
        userRepository.save(request);
        return UserRequest.mapToDto(request);

    }

    @Override
    public UserRequest updateUser(User userRequest, int id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResolutionException("User not found"));
        if(existingUser != null){
            existingUser.setName(userRequest.getName());
            existingUser.setMail(userRequest.getMail());
            existingUser.setPassword(userRequest.getPassword());
            existingUser.setIsActive(userRequest.getIsActive());
            existingUser.setUpDate(lt);
            if(!existingUser.getPhones().isEmpty() && !userRequest.getPhones().isEmpty()){
                for(Phone phone: existingUser.getPhones()){
                    for(Phone requestPhone: userRequest.getPhones()){
                        if(phone.getPhoneId() == requestPhone.getPhoneId()){
                            phone.setNumber(requestPhone.getNumber());
                            phone.setCodCity(requestPhone.getCodCity());
                            phone.setCodCountry(requestPhone.getCodCountry());
                        }
                    }
                }
            }
            userRepository.save(existingUser);
            return UserRequest.mapToDto(existingUser);
        }
        return null;
    }


    @Override
    public String deleteUser(int id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResolutionException("User not found"));
        userRepository.delete(existingUser);
        return "Usuario id: " + Integer.toString(existingUser.getUserId()) + "User name: " + existingUser.getName() + ", eliminado." ;
    }

}
