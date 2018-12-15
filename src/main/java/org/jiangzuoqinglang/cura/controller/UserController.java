package org.jiangzuoqinglang.cura.controller;

import org.jiangzuoqinglang.cura.common.JsonResult;
import org.jiangzuoqinglang.cura.entity.User;
import org.jiangzuoqinglang.cura.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    private JsonResult jsonResult = new JsonResult();

    /**
     * 用户注册
     *
     * @param body
     * @return
     */
    @PostMapping(path = "/register")
    public Object register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return jsonResult.failed(400, bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        if (user.getPhone()!=null && user.getPassword()!=null && user.getNickname()!=null) {
            String phone = user.getPhone();
            String nickname = user.getNickname();
            String password = user.getPassword();
            User userInsert = userService.register(nickname, password, phone);
            userInsert.setPassword(null);
            return jsonResult.success(userInsert);
        } else {
            return jsonResult.failed(400, "缺少参数", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 用户登录
     *
     * @param body
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody Map<String, Object> body) {
        if (body.containsKey("cura_number") && body.containsKey("password")) {
            int cura_number = (Integer) body.get("cura_number");
            String password = (String) body.get("password");

            User user = userService.checkUser(cura_number, password);

            if (user == null) {
                return jsonResult.failed(400, "账号或密码错误", HttpStatus.BAD_REQUEST);
            }

            String token = userService.generateToken(user);

            return jsonResult.success(token);
        } else {
            return jsonResult.failed(400, "缺少参数", HttpStatus.BAD_REQUEST);
        }
    }
}
