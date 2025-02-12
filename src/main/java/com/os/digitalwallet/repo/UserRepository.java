package com.os.digitalwallet.repo;

import com.os.digitalwallet.models.User;
import com.os.digitalwallet.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String>{

    @Query("SELECT MAX(u.userId) FROM User u")
    public String findMaxUserId();

    public User findByUserName(String userName);

    @Query("SELECT u.userId AS userId, u.userName AS userName FROM User u WHERE u.userName = ?1")
    public UserProjection findIdByUserName(String userName);


}
