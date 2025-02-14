package com.os.digitalwallet.service;

import com.os.digitalwallet.models.User;
import com.os.digitalwallet.projections.UserProjection;
import com.os.digitalwallet.repo.UserRepository;
import com.os.digitalwallet.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepo;

    @Override
    public ResponseEntity<String>loginUser(User userData) {
        User userCheck = userRepo.findByUserName(userData.getUserName()); //for pass retrieve
        if(userCheck == null){
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }

        try {
            String decryptedPassword = EncryptionUtil.decrypt(userCheck.getPassword());
            if(!decryptedPassword.equals(userData.getPassword())){
                return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Logged in successfully!",HttpStatus.OK);
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

}
