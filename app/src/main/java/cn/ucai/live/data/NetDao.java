package cn.ucai.live.data;

import android.content.Context;

import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.domain.User;

import java.io.File;

import cn.ucai.live.I;
import cn.ucai.live.utils.MD5;
import cn.ucai.live.utils.OkHttpUtils;
import cn.ucai.live.utils.OnCompleteListener;


/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class NetDao {
    public static void register(Context context, String username, String nick, String password, OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.NICK,nick)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .post()
                .targetClass(String.class)
                .execute(listener);
    }
    public static void unRegister(Context context, String username, OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME,username)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }
    public static void login(Context context, String username, String password,
                             OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.PASSWORD,MD5.getMessageDigest(password))
                .targetClass(String.class)
                .execute(listener);
    }
    public static void getUserInfoByUsername(Context context, String username, OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME,username)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void updataNick(Context context,String username, String nick,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.NICK,nick)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void updataAvatar(Context context , String username, File file,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID,username)
                .addParam(I.AVATAR_TYPE,I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .targetClass(String.class)
                .post()
                .execute(listener);
    }
    public static void addCotact(Context context,String username,String cname,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_CONTACT)
                .addParam(I.Contact.USER_NAME,username)
                .addParam(I.Contact.CU_NAME,cname)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void loadContact(Context context,String username,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DOWNLOAD_CONTACT_ALL_LIST)
                .addParam(I.Contact.USER_NAME,username)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void delteContact(Context context ,String name,String cname,OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CONTACT)
                .addParam(I.Contact.USER_NAME,name)
                .addParam(I.Contact.CU_NAME,cname)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void creatGroup(Context context , EMGroup group , File file , OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam(I.Group.HX_ID,group.getGroupId())
                .addParam(I.Group.NAME,group.getGroupName())
                .addParam(I.Group.DESCRIPTION,group.getDescription())
                .addParam(I.Group.OWNER,group.getOwner())
                .addParam(I.Group.IS_PUBLIC,String.valueOf(group.isPublic()))
                .addParam(I.Group.ALLOW_INVITES,String.valueOf(group.isAllowInvites()))
                .addFile2(file)
                .targetClass(String.class)
                .post()
                .execute(listener);
    }

    public static void addGroupMebers(Context context , String mebers,String groupId,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_GROUP_MEMBER)
                .addParam(I.Member.USER_NAME,mebers)
                .addParam(I.Member.GROUP_HX_ID,groupId)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void removeGroupMember(Context context, String hxid, String username,
                                         OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_GROUP_MEMBER)
                .addParam(I.Member.GROUP_HX_ID,hxid)
                .addParam(I.Member.USER_NAME,username)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void updateGroupName(Context context,String hxid,String username,
                                       OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_GROUP_NAME)
                .addParam(I.Group.HX_ID,hxid)
                .addParam(I.Group.NAME,username)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void deleteGroup(Context context,String hxid,
                                       OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_GROUP_BY_HXID)
                .addParam(I.Group.HX_ID,hxid)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void loadLiveList(Context context,OnCompleteListener listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_GET_ALL_CHATROOM)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void createLive(Context context, User user, OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_CHATROOM)
                .addParam("auth","1IFgE")
                .addParam("name",user.getMUserNick()+"的直播")
                .addParam("description",user.getMUserNick()+"的直播")
                .addParam("owner",user.getMUserName())
                .addParam("maxusers","300")
                .addParam("members",user.getMUserName())
                .targetClass(String.class)
                .execute(listener);
    }

    public static void removeLive(Context context, String chatroomId, OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CHATROOM)
                .addParam("auth","1IFgE")
                .addParam("chatRoomId",chatroomId)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void loadAllGift(Context context, OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ALL_GIFTS)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void loadChangde(Context context,String username, OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_GET_BALANCE)
                .addParam("uname",username)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void loadGivingGigt(Context context,String username,String cname,int giftId,int giftNum ,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_GIVING_GIGT)
                .addParam("uname",username)
                .addParam("anchor",cname)
                .addParam("giftId",String.valueOf(giftId))
                .addParam("giftNum",String.valueOf(giftNum))
                .targetClass(String.class)
                .execute(listener);
    }

    public static void recharge(Context context,String username,int rmb ,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_RECHARGE)
                .addParam("uname",username)
                .addParam("rmb",String.valueOf(rmb))
                .targetClass(String.class)
                .execute(listener);
    }

    public static void getGivingGiftStatements(Context context,String username,int pageId,int pageSize ,OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_RECHARGE_STATEMENTS_PAGE)
                .addParam("uname",username)
                .addParam("pageId",String.valueOf(pageId))
                .addParam("pageSize",String.valueOf(pageSize))
                .targetClass(String.class)
                .execute(listener);
    }

}
