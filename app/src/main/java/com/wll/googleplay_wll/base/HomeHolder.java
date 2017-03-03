package com.wll.googleplay_wll.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wll.googleplay_wll.R;
import com.wll.googleplay_wll.bean.HomeBean;
import com.wll.googleplay_wll.utils.StringUtils;
import com.wll.googleplay_wll.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 王丽丽 on 2017/3/3.
 * cutroller---
 * 提供视图
 * 加载数据
 * 进行数据和视图间的绑定
 */

public class HomeHolder extends BaseHolder<HomeBean.ItemBean> {


    @BindView(R.id.item_appinfo_iv_icon)
    ImageView itemAppinfoIvIcon;
    @BindView(R.id.item_appinfo_tv_title)
    TextView itemAppinfoTvTitle;
    @BindView(R.id.item_appinfo_rb_stars)
    RatingBar itemAppinfoRbStars;
    @BindView(R.id.item_appinfo_tv_size)
    TextView itemAppinfoTvSize;
    @BindView(R.id.item_appinfo_tv_des)
    TextView itemAppinfoTvDes;


    @Override
    public View initHolder() {
        View itemView = View.inflate(UIUtils.getContext(), R.layout.item_home, null);
        ButterKnife.bind(this, itemView);
        return itemView;
    }

    @Override
    public void refreshHolderView(HomeBean.ItemBean data) {
        //view-->成员变量
        //data-->局部变量,基类还有
        //data+view


        //设置title
        itemAppinfoTvTitle.setText(data.name);
        //设置受欢迎星数
        itemAppinfoRbStars.setRating(data.stars);
        //设置软件的大小
        itemAppinfoTvSize.setText(StringUtils.formatFileSize(data.size));
        //产品介绍
        itemAppinfoTvDes.setText(data.des);


    }
}

    /*
    public View mViewHolder;//返回一个view
    private String datas;//接受数据
    private TextView mTvTmp1;
    private TextView mTvTmp2;

    public HomeHolder() {
        mViewHolder = initHolder();
        //找一个符合条件的holder,绑定在自己身上
        mViewHolder.setTag(this);
    }

    private View initHolder() {
        View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_temp,null);
        //初始化孩子对象
        mTvTmp1 = (TextView) view.findViewById(R.id.tmp_tv_1);
        mTvTmp2 = (TextView) view.findViewById(R.id.tmp_tv_2);

        return view;
    }

    /**
     * @des 1.接收数据
     * @des 2.数据和视图的绑定
     */
    /*
    public void setDataAndRefreshHolderView(String data) {
        //保存数据到成员变量
        datas = data;
        //进行数据的视图的绑定
        refreshHolderView(data);
    }


    private void refreshHolderView(String data) {
        //view-->成员变量
        //data-->局部变量有,成员变量里面也有
        //data+view
        mTvTmp1.setText("我是头-" + data);
        mTvTmp2.setText("我是尾巴-" + data);
    }*/


