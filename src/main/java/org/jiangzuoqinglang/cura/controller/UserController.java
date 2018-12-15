package org.jiangzuoqinglang.cura.controller;

import org.jiangzuoqinglang.cura.entity.User;
import org.jiangzuoqinglang.cura.service.UserService;
import org.jiangzuoqinglang.cura.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册
     *
     * @param body
     * @return
     */
    @PostMapping(path = "/register")
    public Object register(@RequestBody Map<String, Object> body) {
        if (body.containsKey("phone") && body.containsKey("password") && body.containsKey("nickname")) {
            String phone = (String) body.get("phone");
            String nickname = (String) body.get("nickname");
            String password = (String) body.get("password");

            boolean result = userService.register(nickname, password, phone);

            return success("success");
        } else {
            return failed(1, "register failed");
        }
    }

//    /**
//     * 用户登录
//     *
//     * @param body
//     * @return
//     */
//    @PostMapping("/login")
//    public Object login(@RequestBody Map<String, Object> body) {
//        if (body.containsKey("username") && body.containsKey("password")) {
//            String username = (String) body.get("username");
//            String password = (String) body.get("password");
//
//            User user = userService.checkUser(username, password);
//
//            if (user == null) {
//                return failed(1, "username or password incorrect");
//            }
//
//            String token = userService.generateToken(user);
//
//            return success("success", token);
//        } else {
//            return failed(1, "register failed");
//        }
//    }
//
//    //获取所有的用户信息
//    @RequireAdmin
//    @GetMapping(path = "/users")
//    public Object findAllUser() {
//        final List<User> users = userService.findAllUser();
//
//        return success("success", users);
//    }
}
