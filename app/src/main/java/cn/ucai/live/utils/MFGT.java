package cn.ucai.live.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import cn.ucai.live.I;
import cn.ucai.live.R;
import cn.ucai.live.ui.activity.ChangeActivity;
import cn.ucai.live.ui.activity.ChangeRechargeActivity;
import cn.ucai.live.ui.activity.LoginActivity;
import cn.ucai.live.ui.activity.MainActivity;
import cn.ucai.live.ui.activity.RegisterActivity;
import cn.ucai.live.ui.activity.SendRecordActivity;


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

    public static void gotoMain(Activity activity){
        startActivity(activity,new Intent(activity,MainActivity.class)
        .putExtra(I.BACK_MAIN_FROM_CHAT,true));
    }

    public static void gotoChange(Activity activity) {
        startActivity(activity,ChangeActivity.class);
    }

    public static void gotoChangeRecharge(Activity activity) {
        startActivity(activity, ChangeRechargeActivity.class);
    }

    public static void gotoSendRecord(Activity activity) {
        startActivity(activity, SendRecordActivity.class);
    }
}

