package cn.ucai.live.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.User;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.AddContactActivity;
import cn.ucai.superwechat.ui.ChatActivity;
import cn.ucai.superwechat.ui.DetailsActivity;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.MainActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.SettingActivity;
import cn.ucai.superwechat.ui.SplashActivity;
import cn.ucai.superwechat.ui.UserProfileActivity;
import cn.ucai.superwechat.ui.ValidateActivity;
import cn.ucai.superwechat.ui.VideoCallActivity;
import cn.ucai.superwechat.ui.WelcomeActivity;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class MFGT {
    private static final String TAG = MFGT.class.getSimpleName();
    public static void finish(Activity context){
        context.finish();
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public static void startActivity(Activity context,Class<?> clz){
        context.startActivity(new Intent(context,clz));
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }

    public static void startActivity(Activity context,Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void gotoLogin(Context context){
        startActivity((Activity)context, LoginActivity.class);
    }
    public static void gotoLoginCleanTask(Context context){
        startActivity((Activity)context, new Intent(context,LoginActivity.class)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
    public static void gotoRegister(Context context) {
        startActivity((Activity)context, RegisterActivity.class);
    }

    public static void gotoWelcome(SplashActivity splashActivity) {
        startActivity(splashActivity, WelcomeActivity.class);
    }


    public static void gotoSettings(Activity activity) {
        startActivity(activity, SettingActivity.class);
    }

    public static void gotoUserProfile(Activity activity) {
        startActivity(activity, UserProfileActivity.class);
    }
    public static void gotoAddContact(Activity activity){
        startActivity(activity, AddContactActivity.class);
    }

    public static void gotoDetails(Context context , User user){
        startActivity((Activity)context, new Intent(context,DetailsActivity.class)
                .putExtra(I.User.TABLE_NAME,user));
    }
    public static void gotoDetails(Activity activity , String username){
        if (username.equals(EMClient.getInstance().getCurrentUser())) {
            gotoUserProfile(activity);
        }else {
            startActivity(activity, new Intent(activity,DetailsActivity.class)
                    .putExtra(I.User.USER_NAME,username));
        }
    }
    public static void gotoValidate(DetailsActivity activity, String userName) {
        startActivity(activity,new Intent(activity, ValidateActivity.class)
        .putExtra(I.User.USER_NAME,userName));
    }

    public static void gotoChat(Activity activity, String username) {
        startActivity(activity,new Intent(activity, ChatActivity.class)
                .putExtra("userId", username));

    }
    public static void gotoMain(Activity activity){
        startActivity(activity,new Intent(activity,MainActivity.class)
        .putExtra(I.BACK_MAIN_FROM_CHAT,true));
    }

    public static void gotoVideo(DetailsActivity activity, String mUserName) {
        startActivity(activity,new Intent(activity, VideoCallActivity.class).putExtra("username", mUserName)
                .putExtra("isComingCall", false));
    }
}

