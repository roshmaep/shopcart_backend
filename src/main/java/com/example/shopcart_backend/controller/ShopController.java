package com.example.shopcart_backend.controller;

import com.example.shopcart_backend.dao.ProductDao;
import com.example.shopcart_backend.dao.UserregistrationDao;
import com.example.shopcart_backend.model.Products;
import com.example.shopcart_backend.model.Userregistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShopController {
    @Autowired
    private ProductDao dao;
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/add",consumes = "application/json",produces = "application/json")
    public Map<String,String> AddProduct(@RequestBody Products p){
        System.out.println(p.getProductname().toString());
        System.out.println(p.getImg().toString());
        System.out.println(p.getCategory().toString());
        System.out.println(p.getDes().toString());
        System.out.println(p.getPrice().toString());
        dao.save(p);
        HashMap<String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/view")
    public List <Products> ViewProduct(){
        return (List<Products>) dao.findAll();
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/search",consumes = "application/json",produces = "application/json")
    public List<Products> SearchProduct(@RequestBody Products p)
    {
        String productname= p.getProductname();
        System.out.println(productname);
        return (List<Products>) dao.SearchProduct(p.getProductname());

    }
    @Autowired
    private UserregistrationDao dao1;
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userregistration",consumes = "application/json",produces = "application/json")
    public Map<String,String> UserRegistration(@RequestBody Userregistration u){
        dao1.save(u);
        HashMap <String,String> map=new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userlogin", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> UserLogin(@RequestBody Userregistration u){

        String email=String.valueOf(u.getEmail());
        String password=String.valueOf(u.getPassword());
        List<Userregistration> result=(List<Userregistration>) dao1.UserLogin(email,password);
        HashMap<String,String> st=new HashMap<>();
        if (result.size()==0)
        {
            st.put("status","failed");
        }
        else
        {
            int id=result.get(0).getId();
            st.put("userid",String.valueOf(id));
            st.put("status","success");
        }
        return st;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/getUserById", consumes = "application/json", produces = "application/json")
    public List<Userregistration> GetUserById(@RequestBody Userregistration f){
        return (List<Userregistration>) dao1.FindUser(f.getId());
    }
}
