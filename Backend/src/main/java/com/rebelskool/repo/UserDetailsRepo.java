package com.rebelskool.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rebelskool.entity.UserDetails;

import javax.transaction.Transactional;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, String>{
    @Query(value = "select * from userdetails", nativeQuery = true)
    List<UserDetails> getAllUserDetails();

    @Modifying
    @Query(value = "insert into userdetails values(?1,?2,?3)", nativeQuery = true)
    @Transactional
    void insertUser(String Email, String Password, String Username);

    @Query(value = "select * from userdetails where Email=?1", nativeQuery = true)
    List<UserDetails> checkEmailIsExistsAlready(String Email);

    @Query(value = "select * from userdetails where Email=?1 and Password=?2", nativeQuery = true)
    List<UserDetails> login(String Email, String Password);
}
