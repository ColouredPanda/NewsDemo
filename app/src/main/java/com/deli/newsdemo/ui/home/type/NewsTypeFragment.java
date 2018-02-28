package com.deli.newsdemo.ui.home.type;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        implements NewsTypeContract.View {
    public static final String TAG = "NewsTypeFragment";
    @BindView(R.id.rc_news)
    RecyclerView mRcNews;

    private Unbinder unbinder;

    private NewsRcAdapter adapter;

    private List<NewsHeadlineEntity.T1348647853363Bean> data = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news_type);
        unbinder = ButterKnife.bind(this, getContentView());
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView() {
        super.initView();
        Log.d(TAG, "initView: ");
        adapter = new NewsRcAdapter(getActivity(), data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcNews.setLayoutManager(layoutManager);
        mRcNews.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mPresenter.getNews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void refreshData(List<NewsHeadlineEntity.T1348647853363Bean> bean) {
        Log.d(TAG, "refreshData: " + bean.size());
        data.clear();
        data.addAll(bean);
        adapter.notifyDataSetChanged();
    }
}
