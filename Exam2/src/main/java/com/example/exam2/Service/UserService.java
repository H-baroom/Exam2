package com.example.exam2.Service;

import com.example.exam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    ArrayList<User> users = new ArrayList<User>();

    public ArrayList<User> getUsers(){
        return users;
    }
    public void addUser(User user){
        users.add(user);
    }
    public boolean updateUser(String ID,User user){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID().equals(ID)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String ID){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID().equals(ID)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> listByBalance(int balance){
        ArrayList<User> ByBalance = new ArrayList();
        for (User u:users){
            if (u.getBalance() >= balance){
                ByBalance.add(u);
            }
        }
        return ByBalance;
    }

    public ArrayList<User> listByAge(int age){
        ArrayList<User> ByAge = new ArrayList();
        for (User u:users){
            if (u.getAge() >= age){
                ByAge.add(u);
            }
        }
        return ByAge;
    }
}
