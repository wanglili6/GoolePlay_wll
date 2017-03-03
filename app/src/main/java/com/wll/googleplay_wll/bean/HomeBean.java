package com.wll.googleplay_wll.bean;

import java.util.List;

/**
 * Created by 王丽丽 on 2017/3/3.
 */

public class HomeBean {

    public List<String> picture;
    public List<ItemBean> list;

    public static class ItemBean {
        /**
         * id : 1525489
         * name : 黑马程序员
         * packageName : com.itheima.www
         * iconUrl : app/com.itheima.www/icon.jpg
         * stars : 5
         * size : 91767
         * downloadUrl : app/com.itheima.www/com.itheima.www.apk
         * des : 产品介绍：google市场app测试。
         */

        public int id;
        public String name;
        public String packageName;
        public String iconUrl;
        public float stars;
        public long size;
        public String downloadUrl;
        public String des;
    }
}
