package com.example.hongaer.shoppingmall2.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongaer.shoppingmall2.R;
import com.example.hongaer.shoppingmall2.base.BaseFragment;
import com.example.hongaer.shoppingmall2.home.adapter.HomeFragmentAdapter;
import com.example.hongaer.shoppingmall2.home.bean.ResultDataBean;

/**
 * Created by hongaer on 2017/7/1.
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private ResultDataBean.ResultBean resultbean;
    private HomeFragmentAdapter adapter;
    private WebView webview;

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home,null);
        webview= (WebView) view.findViewById(R.id.wb);
        WebSettings webSettings=webview.getSettings();
         webSettings.setUseWideViewPort(true);
         webSettings.setJavaScriptEnabled(true);
         webview.setWebViewClient(new WebViewClient());
         webview.loadUrl(  "http://killsound888.oicp.net:118/jinque/debug/jqmall/index.php?controller=site&action=index");
         webview.setWebViewClient(new HelloWebViewClient());
        //Web视图


       /* rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);*/
//设置点击事件
        /*initListener();*/
        return view;
    }

    private class HelloWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    /*public void initData() {
        super.initData();
        Log.e(TAG, "主页数据被初始化了");
        String url = Constans.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                           Log.e(TAG,"首页请求失败==="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("tag","首页请求成功==="+response);
                        processData(response);
                    }

                });
    }

    private void processData(String json) {
        ResultDataBean resultdatabean = JSON.parseObject(json, ResultDataBean.class);
        resultbean = resultdatabean.getResult();
        //Log.e(TAG,"解析成功===="+ resultbean.getBanner_info().get(1).getImage();
        if (resultbean != null) {
            adapter = new HomeFragmentAdapter(mContext, resultbean);
            rvHome.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
                 //设置滑动到哪个位置了的监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position <= 3) {
                        ib_top.setVisibility(View.GONE);
                    } else {
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    //只能返回 1
                    return 1;
                }
            });
                      //设置网格布局
            rvHome.setLayoutManager(manager);
         }

         else{}

    }

    private void initListener() {
//置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//回到顶部
                rvHome.scrollToPosition(0);
            }
        });
//搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索",
                        Toast.LENGTH_SHORT).show();
            }
        });
//消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}