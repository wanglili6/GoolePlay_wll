package com.wll.googleplay_wll.fragment;

import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.wll.googleplay_wll.adapter.HomeAdapter;
import com.wll.googleplay_wll.base.BaseFragment;
import com.wll.googleplay_wll.base.LoadingPager;
import com.wll.googleplay_wll.bean.HomeBean;
import com.wll.googleplay_wll.conf.BuildConfig;
import com.wll.googleplay_wll.utils.HttpUtils;
import com.wll.googleplay_wll.utils.UIUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 王丽丽 on 2017/3/1.
 * 首页
 */
public class HomeFragment extends BaseFragment {

    private List<HomeBean.ItemBean> itemBeen;
    private List<String> picture;

    /**
     * 加载具体数据
     *
     * @return
     */
    @Override
    protected LoadingPager.LoadedResult initData() {
        try {
            //1.创建OkHttpClient对象
            OkHttpClient okHttpClient = new OkHttpClient();
            // 2.拼接要访问的URL--http://localhost:8080/GooglePlayServer/home
            String url = BuildConfig.URl + "home";

            // 定义参数对应的map
            Map<String, Object> params = new HashMap<>();
            params.put("index", 0);//暂时不考虑分页
            // 拼接参数信息 ?index=0
            String urlParamsByMap = HttpUtils.getUrlParamsByMap(params);
            //url拼接参数对应的字符串信息 ：http://localhost:8080/GooglePlayServer/home?index=0
            url = url + "?" + urlParamsByMap;
            // 3.创建请求对象
            Request request = new Request.Builder().get().url(url).build();

            //3.发起请求-->同步请求
            Response response = okHttpClient.newCall(request).execute();
            //4. 解析响应的结果
            if (response.isSuccessful()) {
                //取出响应的内容
                String resJsonString = response.body().string();
                KLog.i("resJsonString-->" + resJsonString);
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(resJsonString, HomeBean.class);
                LoadingPager.LoadedResult state = checkResult(homeBean);
                if (state != LoadingPager.LoadedResult.SUCCESS) {//数据为空
                    return state;

                }
                //数据为集合
                state = checkResult(homeBean.list);//==返回是itembeam
                if (state != LoadingPager.LoadedResult.SUCCESS) {//说明集合为空
                    return state;

                }
                //走到这里来说明是成功的
                //保存数据到成员变量

                itemBeen = homeBean.list;
                picture = homeBean.picture;
                //返回相应的状态
                return state;


            } else {
                return LoadingPager.LoadedResult.ERROR;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResult.ERROR;
        }
    }

    /**
     * 加载成功视图
     *
     * @return
     */
    @Override
    public View initSuccess() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new HomeAdapter(itemBeen));
        return listView;
    }
}
