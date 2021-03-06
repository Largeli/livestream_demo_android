package cn.ucai.live.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.live.R;
import cn.ucai.live.data.NetDao;
import cn.ucai.live.data.model.Result;
import cn.ucai.live.data.model.Wallet;
import cn.ucai.live.utils.MFGT;
import cn.ucai.live.utils.OnCompleteListener;
import cn.ucai.live.utils.PreferenceManager;
import cn.ucai.live.utils.ResultUtils;


/**
 * Created by Administrator on 2017/3/6 0006.
 */
public class ChangeActivity extends BaseActivity {
    @BindView(R.id.tv_change_balance)
    TextView tvChangeBalance;
    @BindView(R.id.target_layout)
    LinearLayout targetLayout;
    View loadingView;
    int change;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        Log.e("ChangeActivity", "oncreate");
        loadingView = LayoutInflater.from(ChangeActivity.this)
                .inflate(R.layout.rp_loading, targetLayout, false);
        targetLayout.addView(loadingView);
        initData();
    }

    private void initData() {
        NetDao.loadChangde(ChangeActivity.this, EMClient.getInstance().getCurrentUser(),
                new OnCompleteListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        boolean success = false;
                        if (s != null) {
                            Result result = ResultUtils.getResultFromJson(s, Wallet.class);
                            if (result != null && result.isRetMsg()) {
                                success = true;
                                Wallet wallet = (Wallet) result.getRetData();
                                PreferenceManager.getInstance().setCurrentuserChange(wallet.getBalance());
                                change = wallet.getBalance();
                                setChange();
                            }
                        }
                        if (!success) {
                            PreferenceManager.getInstance().setCurrentuserChange(0);
                        }
                        loadingView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(String error) {
                        PreferenceManager.getInstance().setCurrentuserChange(0);
                        loadingView.setVisibility(View.GONE);
                    }
                });
    }

    private void setChange() {
        change = PreferenceManager.getInstance().getCurrentuserChange();
        tvChangeBalance.setText("￥" + Float.valueOf(String.valueOf(change)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @OnClick({R.id.tv_change_recharge, R.id.tv_change_withdraw,R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change_recharge:
                MFGT.gotoChangeRecharge(ChangeActivity.this);
                break;
            case R.id.tv_change_withdraw:
                break;
            case R.id.iv_back:
                MFGT.finish(ChangeActivity.this);
                break;
        }
    }
}
