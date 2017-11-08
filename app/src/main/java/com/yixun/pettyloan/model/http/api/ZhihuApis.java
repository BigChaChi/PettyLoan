package com.yixun.pettyloan.model.http.api;
import com.yixun.pettyloan.model.bean.HotListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * zhihu提供的开源api,供测试用
 * Created by zongkaili on 2017/8/29.
 */
public interface ZhihuApis {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 热门日报
     */
    @GET("news/hot")
    Flowable<HotListBean> getHotList();

}
