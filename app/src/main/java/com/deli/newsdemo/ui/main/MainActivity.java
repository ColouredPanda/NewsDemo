package com.deli.newsdemo.ui.main;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.deli.newsdemo.R;
import com.deli.newsdemo.mvpframe.base.BaseFrameActivity;
import com.deli.newsdemo.ui.main.f_main.MainContainerFragment;
import com.deli.newsdemo.ui.mine.MineFragment;
import com.deli.newsdemo.ui.newsdetails.NewsDetailsFragment;
import com.deli.newsdemo.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseFrameActivity<MainPresenter, MainModel> implements MainContract.View, FragmentMessageCallback {
    @BindView(R.id.loading)
    LottieAnimationView loading;

    private static final String TAG = "MainActivity";
    public static final String ADD_NEWS_PAGE = "add_news_page";
    private MainContainerFragment mMainFragment;
    private long lastTime = 0;
    private Unbinder unbinder;

    @Override
    public void onMessage(Bundle bundle, String tag) {
        switch (tag) {
            case MineFragment.TAG:
                break;
            case NewsDetailsFragment.TAG:
                break;
            case NewsDetailsFragment.NEW_DETAILS_RIGHT_CALLBACK:
                getLoading().setVisibility(View.GONE);
                backToFragment();
                break;
            case ADD_NEWS_PAGE:
                NewsDetailsFragment mNewsDetailsFragment = new NewsDetailsFragment();
                toFragment(mNewsDetailsFragment);
                setCurrFragment(mNewsDetailsFragment);
                getLoading().setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        Log.d(TAG, "onMessage: " + tag);
    }

    @Override
    public LottieAnimationView getLoading() {
        return loading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setFragmentId(R.id.container);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
