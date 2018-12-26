package org.jiangzuoqinglang.cura.controller;

import org.jiangzuoqinglang.cura.common.JsonResult;
import org.jiangzuoqinglang.cura.entity.*;
import org.jiangzuoqinglang.cura.service.*;
import org.jiangzuoqinglang.cura.utils.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/friend")
public class FriendController extends BaseController {

    @Resource
    private FriendService friendService;

    @Resource
    private ChatService chatService;

    @Resource
    private UserService userService;

    @Resource
    private MessageBoxService messageBoxService;

    @Resource
    private GroupService groupService;

    private JsonResult jsonResult = new JsonResult();

    /**
     * 显示该分组的所有好友
     *
     * @param token
     * @param groupId
     * @return
     */
    @GetMapping(value = "/groupAllFriends")
    public Object getGroupAllFriends(@RequestHeader("Authorization") String token, @RequestParam("groupId") int groupId) {
        return jsonResult.success(friendService.findAllByGroup_id(groupId));
    }

    /**
     * 获取该用户的所有好友
     *
     * @param token
     * @return
     */
    @GetMapping(value = "/all")
    public Object getAllFriends(@RequestHeader("Authorization") String token) {
        User user = getUser();
        List<Friend> friends = friendService.findAllFriends(user.getCuraNumber());
        ArrayList<Map<String, Object>> friendList = new ArrayList<>();
        for (Friend friend : friends) {
            Map<String, Object> map = new HashMap<>();
            User userFriend = userService.findOne(friend.getFriendCuraNumber());
            Group group = groupService.getOne(friend.getGroupId());
            map.put("friendCuraNumber", friend.getFriendCuraNumber());
            map.put("userCuraNumebr", friend.getUserCuraNumber());
            map.put("groupId", friend.getGroupId());
            map.put("remark", friend.getRemark());
            map.put("signature", userFriend.getSignature());
            map.put("nickname", userFriend.getNickname());
            map.put("headUrl", userFriend.getHeadUrl());
            map.put("groupName", group.getGroupName());
            friendList.add(map);
        }
        return jsonResult.success(friendList);
    }

//    /**
//     * 根据cura账号添加好友
//     *
//     * @param token
//     * @param body
//     * @return
//     */
//    @PostMapping(value = "/add")
//    public Object addFriendByCuraNumber(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> body) {
//        if (body.containsKey("groupId") && body.containsKey("curaNumber")) {
//            User user = getUser();
//            List<Friend> friends = friendService.findAllFriends(user.getCuraNumber());
//            for (Friend friend : friends) {
//                if (friend.getFriendCuraNumber() == (int) body.get("curaNumber")) {
//                    return jsonResult.failed(400, "该好友已经存在", HttpStatus.BAD_REQUEST);
//                }
//            }
//            Friend friend = friendService.addFriendByCuraNumber(user.getCuraNumber(), (int) body.get("groupId"), (int) body.get("curaNumber"), (String) body.get("remark"));
//            if (friend == null) {
//                return jsonResult.failed(400, "该账号不存在", HttpStatus.BAD_REQUEST);
//            }
//            return jsonResult.success(friend);
//        } else {
//            return jsonResult.failed(400, "缺少参数", HttpStatus.BAD_REQUEST);
//        }
//    }

    /**
     * 删除好友
     *
     * @param token
     * @param body
     * @return
     */
    @DeleteMapping(value = "/delete")
    public Object removeFriend(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> body) {
        User user = getUser();
        if (body.containsKey("friendCuraNumber")) {
            friendService.removeFriend(user.getCuraNumber(), (int) body.get("friendCuraNumber"));
            friendService.removeFriend((int) body.get("friendCuraNumber"), user.getCuraNumber());
            List<MessageBox> messageBoxesUser = messageBoxService.getMessagesFriends(user.getCuraNumber(), (int) body.get("friendCuraNumber"), 0);
            for (MessageBox messageBox:messageBoxesUser){
                messageBoxService.deleteMessage(messageBox.getMessageBoxId());
            }
            List<MessageBox> messageBoxesFriend = messageBoxService.getMessagesFriends((int) body.get("friendCuraNumber"), user.getCuraNumber(), 0);
            for (MessageBox messageBox:messageBoxesFriend){
                messageBoxService.deleteMessage(messageBox.getMessageBoxId());
            }
            return jsonResult.success();
        } else {
            return jsonResult.failed(400, "缺少参数", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 移动好友的分组
     *
     * @param token
     * @param body
     * @return
     */
    @PutMapping(value = "/move")
    public Object modifyGroup(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> body) {
        User user = getUser();
        if (body.containsKey("friendCuraNumber") && body.containsKey("groupId")) {
            int friendCuraNumber = (int) body.get("friendCuraNumber");
            int groupId = (int) body.get("groupId");
            friendService.modifyGroup(user.getCuraNumber(), friendCuraNumber, groupId);
            return jsonResult.success();
        } else {
            return jsonResult.failed(400, "缺少参数", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 修改好友备注
     *
     * @param token
     * @param body
     * @return
     */
    @PutMapping(value = "/update_remark")
    public Object updateRemark(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> body) {
        User user = getUser();
        if (body.containsKey("friendCuraNumber") && body.containsKey("remark")) {
            int friendCuraNumber = (int) body.get("friendCuraNumber");
            String remark = (String) body.get("remark");
            friendService.modifyRemark(user.getCuraNumber(), friendCuraNumber, remark);
            return jsonResult.success();
        } else {
            return jsonResult.failed(400, "缺少参数", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 清空聊天记录
     * @param token
     * @param body
     * @return
     */
    @DeleteMapping(value = "delete_chat")
    public Object deleteChat(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> body){
        User user = getUser();
        if (body.containsKey("friendCuraNumber")) {
            int friendCuraNumber = (int) body.get("friendCuraNumber");
            chatService.deleteMessages(user.getCuraNumber(), friendCuraNumber);
            return jsonResult.success();
        } else {
            return jsonResult.failed(400, "缺少参数", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 下载与某好友的聊天记录
     * @param token
     * @param body
     * @return
     */
    @PostMapping(value = "download")
    public Object download(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> body){
        User user = getUser();
        if (body.containsKey("friendCuraNumber")) {
            int friendCuraNumber = (int) body.get("friendCuraNumber");
            List<Chat> chats = chatService.chatMessages(user.getCuraNumber(), friendCuraNumber);
            String msg = "";
            for (Chat chat:chats){
                User userFind = userService.findOne(chat.getSendCuraNumber());
                String time = "";
                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                time = sdf.format(chat.getTime());
                String str = time + " " + userFind.getNickname() + "：" + chat.getMessage();
                msg += str + "\n";
            }
            return jsonResult.success(msg);
        } else {
            return jsonResult.failed(400, "缺少参数", HttpStatus.BAD_REQUEST);
        }
    }
}
