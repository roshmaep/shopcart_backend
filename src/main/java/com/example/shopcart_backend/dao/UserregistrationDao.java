package com.example.shopcart_backend.dao;

import com.example.shopcart_backend.model.Userregistration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserregistrationDao extends CrudRepository<Userregistration,Integer> {

    @Query(value = "SELECT `id`, `address`, `cpassword`, `email`, `name`, `password`, `phone` FROM `registration` WHERE `email`=:email AND `password`=:password",nativeQuery = true)
    List<Userregistration> UserLogin(@Param("email") String email, @Param("password") String password);

    @Query(value="SELECT `id`, `address`, `cpassword`, `email`, `name`, `password`, `phone` FROM `registration` WHERE `id`= :id",nativeQuery = true)
    List<Userregistration> ViewProfile(@Param("id") Integer id);
}
