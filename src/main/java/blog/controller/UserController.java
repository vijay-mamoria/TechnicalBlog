package blog.controller;

import blog.form.RegisterNewUser;
import blog.model.Post;
import blog.model.User;
import blog.services.PostService;
import blog.services.UserServiceImp;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import java.nio.charset.StandardCharsets;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private PostService postService;

    @RequestMapping("/users/login")
    public String loginPage(User user){
        return "users/login";
    }

    @RequestMapping(value = "/users/login" , method = RequestMethod.POST)
    public String login(RegisterNewUser user, Model model){
        if(userServiceImp.authenticate(user.getUsername(), user.getPassword())){
            return "redirect:/posts";
        }
            return "redirect:/";
    }

    @RequestMapping(value = "/users/logout", method = RequestMethod.POST)
    public String logout(Model model){

        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts.subList(0,3));
        return "index";
    }

    @RequestMapping(value = "/users/register")
    public String register(Model model){
        return "/users/register";
    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public String registerUser(RegisterNewUser registerNewUser){
        User user = new User(registerNewUser.getUsername(), registerNewUser.getFullName());
        String sha256hex = Hashing.sha256()
                .hashString(registerNewUser.getPassword(), StandardCharsets.UTF_8)
                .toString();
        user.setPasswordHash(sha256hex);
        userServiceImp.registerNewUser(user);
        return "redirect:/";
    }

}
