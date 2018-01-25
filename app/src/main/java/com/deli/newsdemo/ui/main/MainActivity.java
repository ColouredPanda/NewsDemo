package com.deli.newsdemo.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.deli.newsdemo.R;
import com.deli.newsdemo.mvpframe.base.BaseFrameActivity;
import com.deli.newsdemo.ui.main.f_main.MainContainerFragment;
import com.deli.newsdemo.ui.mine.MineFragment;
import com.deli.newsdemo.ui.newsdetails.NewsDetailsFragment;
import com.deli.newsdemo.util.ToastUtils;

public class MainActivity extends BaseFrameActivity<MainPresenter, MainModel> implements MainContract.View, FragmentMssageListener {

    private static final String TAG = "MainActivity";

    private MainContainerFragment mMainFragment;
    private long lastTime = 0;

    @Override
    public void onMessage(Bundle bundle, String tag) {
        switch (tag) {
            case MineFragment.TAG:
                break;
            case NewsDetailsFragment.TAG:
                NewsDetailsFragment mNewsDetailsFragment = new NewsDetailsFragment();
                toFragment(mNewsDetailsFragment);
                setCurrFragment(mNewsDetailsFragment);
                break;
            default:
                break;
        }
        Log.d(TAG, "onMessage: " + tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragmentId(R.id.container);
    }

    @Override
    public void initView() {
        super.initView();
        mMainFragment = new MainContainerFragment();
        setCurrFragment(mMainFragment);
        toFragment(mMainFragment);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: " + getSupportFragmentManager().getBackStackEntryCount());
        if (keyCode == KeyEvent.KEYCODE_BACK && getSupportFragmentManager().getBackStackEntryCount() == 0) {
            int timeDuration = 2000;
            long currentTime = System.currentTimeMillis();
            ToastUtils.showToast("按两次退出程序");
            if (currentTime - lastTime < timeDuration) {
                finish();
            }
            lastTime = currentTime;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
