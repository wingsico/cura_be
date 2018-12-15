package org.jiangzuoqinglang.cura.utils;

import org.jiangzuoqinglang.cura.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    @Resource
    protected HttpServletRequest request;

    /**
     * 获取当前用户
     * @return
     */
    protected User getUser() {
        return (User) request.getAttribute("user");
    }

    /**
     * 操作成功时返回的状态和数据
     * @param msg
     * @return
     */
    protected Object success(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", msg);

        return map;
    }

    protected Object success(String msg, Object data) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) success(msg);
        map.put("data", data);

        return map;
    }

    /**
     * 操作失败时返回的状态和数据
     * @param status
     * @param msg
     * @return
     */
    protected ResponseEntity<Object> failed(Integer status, String msg, HttpStatus httpStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("message", msg);

        return new ResponseEntity<Object>(map, httpStatus);
    }

    protected Object failed(Integer status, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("message", msg);

        return map;
    }
}
