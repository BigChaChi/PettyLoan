package com.yixun.pettyloan.ui.fragment;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.ui.LoginActivity;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MineFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;

    public static MineFragment getInstance(String title) {
        MineFragment sf = new MineFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, rootView, R.color.blue_medium);

        initToolbar();

        configRefresh();
    }

    @Override
    protected void initData() {

    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.tool_bar_menu_mine);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LoginActivity.class));
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sign:
                        Toast.makeText(getContext(), "功能开发中...", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }

    private void configRefresh(){
        mRefresh.setColorSchemeColors(getResources().getColor(R.color.blue_dark));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRefreshStatus();
            }
        });
    }

    public void updateRefreshStatus(){
        Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                SystemClock.sleep(1000);
                subscriber.onNext("refresh");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mRefresh.setRefreshing(false);
                    }
                });
    }

    @OnClick({R.id.ll_total_assets,R.id.tv_recharge})
    public void onClick(View view){
         switch (view.getId()){
             case R.id.ll_total_assets:
                 EventBus.getDefault().post(new StartBrotherEvent(TotalAssetsFragment.getInstance("总资产")));
                 break;
             case R.id.tv_recharge:
                 Toast.makeText(context,"功能开发中．．．",Toast.LENGTH_SHORT).show();
                 break;
             default:
                 break;
         }
    }
}