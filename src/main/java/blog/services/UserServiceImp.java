package blog.services;

import blog.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Override
    public boolean authenticate(String username, String password) {
        if("test".equals(username) && "test1".equals(password)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean registerNewUser(User user) {
        return true;
    }
}
