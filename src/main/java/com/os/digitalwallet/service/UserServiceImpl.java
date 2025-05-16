package com.os.digitalwallet.service;

import com.os.digitalwallet.models.Response;
import com.os.digitalwallet.models.User;
import com.os.digitalwallet.projections.UserProjection;
import com.os.digitalwallet.repo.UserRepository;
import com.os.digitalwallet.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    public UserRepository userRepo;

    @Override
    public Response loginUser(User userData) {
        Response response = new Response();
        User userCheck = userRepo.findByUserName(userData.getUserName()); //for pass retrieve
        if(userCheck == null){
            response.setMessage("User not found");
            response.setStatusCode(HttpStatus.NOT_FOUND);
            return response;
        }
        try {
            String decryptedPassword = EncryptionUtil.decrypt(userCheck.getPassword());
            if(!decryptedPassword.equals(userData.getPassword())){
                response.setMessage("Incorrect password");
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                return response;
            }
        } catch (Exception e) {
            response.setMessage("Internal server error");
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return response;
        }
        response.setMessage("User logged in successfully");
        response.setStatusCode(HttpStatus.OK);
        return response;
    }

    @Override
    public String logoutUser(String userSession) {
        return "";
    }

    @Override
    public String signUp(User userData){
        UserProjection userCheck = userRepo.findIdByUserName(userData.getUserName());

        User newUser = new User();
        if(userCheck!=null){
            throw new RuntimeException("Given user already exists!");
        } else {
            String userId = userRepo.findMaxUserId();
            if(userId == null){
                newUser.setUserId("1");
            } else {
                String updatedUserId = String.valueOf(Integer.parseInt(userId)+1);
                newUser.setUserId(updatedUserId);
            }
            newUser.setUserName(userData.getUserName());
            try {
                newUser.setPassword(EncryptionUtil.encrypt(userData.getPassword()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            userRepo.save(newUser);
        }

        return "User created successfully!";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), Collections.emptyList());
    }
}
