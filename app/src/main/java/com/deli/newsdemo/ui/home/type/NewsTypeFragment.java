package com.deli.newsdemo.ui.home.type;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.deli.newsdemo.R;
import com.deli.newsdemo.adapter.NewsRcAdapter;
import com.deli.newsdemo.entity.NewsHeadlineEntity;
import com.deli.newsdemo.mvpframe.base.BaseFrameFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 01369557 on 2018/1/27.
 */

public class NewsTypeFragment extends BaseFrameFragment<NewsTypePresenter, NewsTypeModel>
        implements NewsTypeContract.View, Runnable {
    public static final String TAG = "NewsTypeFragment";
    public static final int INT_0 = 0;
    @BindView(R.id.rc_news)
    RecyclerView mRcNews;
    @BindView(R.id.rl_loading)
    RelativeLayout mRlLoding;


    private Unbinder unbinder;

    private NewsRcAdapter adapter;

    private List<NewsHeadlineEntity.T1348647853363Bean> data = new ArrayList<>();

    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news_type);
        unbinder = ButterKnife.bind(this, getContentView());
        adapter = new NewsRcAdapter(getActivity(), data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcNews.setLayoutManager(layoutManager);
        mRcNews.setAdapter(adapter);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView() {
        super.initView();
        Log.d(TAG, "initView: ");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.clearRequest();
    }

    @Override
    public void refreshData(List<NewsHeadlineEntity.T1348647853363Bean> bean) {
        Log.d(TAG, "refreshData: " + bean.size());
        data.clear();
        data.addAll(bean);
        adapter.notifyDataSetChanged();
        setLoadingVisible(false);
    }

    @Override
    public void setLoadingVisible(boolean show) {
        if (show) {
            mRlLoding.setVisibility(View.VISIBLE);
        } else {
            mRlLoding.setVisibility(View.GONE);
        }
    }

    @Override
    public void backToTop() {
        mRcNews.smoothScrollToPosition(INT_0);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d(TAG, "setUserVisibleHint: visible");
            handler = new Handler();
            handler.postDelayed(this, 500);
        } else {
            Log.d(TAG, "setUserVisibleHint: unvisible");
            if (this.isVisible()) {
                data.clear();
                adapter.notifyDataSetChanged();
                setLoadingVisible(true);
            }
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void run() {
        Log.d(TAG, "setUserVisibleHint: ");
        mPresenter.getNews();
    }
}
