package com.example.shopcart_backend.dao;

import com.example.shopcart_backend.model.Products;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends CrudRepository<Products,Integer> {
    @Query(value="SELECT `id`, `category`, `des`, `img`, `price`, `productname` FROM `products` WHERE `productname` LIKE %:productname% ",nativeQuery = true)
    List<Products>SearchProduct(@Param("productname") String productname);
}
