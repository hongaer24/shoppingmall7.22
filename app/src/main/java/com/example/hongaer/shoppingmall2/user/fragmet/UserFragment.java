package com.example.hongaer.shoppingmall2.user.fragmet;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongaer.shoppingmall2.R;
import com.example.hongaer.shoppingmall2.app.LoginActivity;
import com.example.hongaer.shoppingmall2.base.BaseFragment;
import com.example.hongaer.shoppingmall2.home.bean.ResultDataBean;
import com.example.hongaer.shoppingmall2.user.view.GoodsActivity;
import com.example.hongaer.shoppingmall2.user.view.PositionActivity;
import com.example.hongaer.shoppingmall2.utils.Constans;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hongaer on 2017/7/1.
 */

public class UserFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.ib_user_icon_avator)
    ImageButton ibUserIconAvator;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.tv_user_pay)
    TextView tvUserPay;
    @BindView(R.id.tv_doing_pay)
    TextView tvDoingPay;
    @BindView(R.id.tv_user_receive)
    TextView tvUserReceive;
    @BindView(R.id.tv_user_finish)
    TextView tvUserFinish;
    @BindView(R.id.tv_user_drawback)
    TextView tvUserDrawback;
    @BindView(R.id.tv_user_location)
    TextView tvUserLocation;
    @BindView(R.id.tv_user_collect)
    TextView tvUserCollect;
    @BindView(R.id.tv_user_coupon)
    TextView tvUserCoupon;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.ib_user_message)
    ImageButton ibUserMessage;
    Unbinder unbinder1;
    private int height;
    private int mPosition;
    private FirstPageFragment firstPageFragment;
    private ResultDataBean.ResultBean resultbean;
    private String url;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        unbinder1 = ButterKnife.bind(this, view);
        new Thread(){
            @Override
            public void run() {
                postJson();
            }
        }.start();
        return view;
    }
    public void initData() {
        super.initData();
         url = Constans.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("tag","首页请求失败==="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("tag666","首页请求成功==="+response);
                    }

                });
    }
    private void postJson() {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象

        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
         FormBody body = new  FormBody.Builder()
                 .add("login_info","kobe")
                             .add("password","kobekobe").build();

        Request request=new Request.Builder().url(url).post(body).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                     Log.i("taglogin",response.body().string());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




    /*private void processData(String json) {
      LoginBean loginBean = JSON.parseObject(json, LoginBean.class);
        resultbean = resultdatabean.getResult();
        //Log.e(TAG,"解析成功===="+ resultbean.getBanner_info().get(1).getImage();


            //设置网格布局

        }*/







   /* public void initData() {
        super.initData();
      *//*  initListeners();*//*

    }*/
    /*private void initListeners() {

        ViewTreeObserver vto = rlHeader.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                rlHeader.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);

                //得到头部相对布局的高度

                height = rlHeader.getHeight();

                //监听ScrollView滑动监听
                scrollview.setScrollViewListener(UserFragment.this);
            }
        });
    }*/

   /* public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        tvUsercenter.setTextColor(Color.argb((int)0, 255,255,255));
        tvUsercenter.setBackgroundColor(Color.argb((int)0, 0,0,255));

        return rootView;
    }*/


  /*  public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
*/}
  @OnClick({R.id.ib_user_icon_avator, R.id.tv_username, R.id.rl_header, R.id.tv_all_order, R.id.tv_user_pay, R.id.tv_doing_pay, R.id.tv_user_receive, R.id.tv_user_finish, R.id.tv_user_drawback, R.id.tv_user_location, R.id.ib_user_message})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_username:
                Toast.makeText(mContext, "登录注册", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_user_location:
                Intent intent7 = new Intent(mContext, PositionActivity.class);
                startActivity(intent7);
                Toast.makeText(mContext, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_user_message:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_all_order:
                Toast.makeText(mContext, "查看全部订单", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent1);

                break;
            case R.id.tv_user_pay:
                // Toast.makeText(mContext,"待付款",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(mContext, GoodsActivity.class);
                intent2.putExtra("position1", mPosition);
                startActivity(intent2);

                break;
            case R.id.tv_user_receive:
                // Toast.makeText(mContext,"待收货",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_user_finish:
                // Toast.makeText(mContext,"待评价",Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent4);
                break;
            case R.id.tv_user_drawback:
                // Toast.makeText(mContext,"退款",Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent5);
                break;
            case R.id.tv_doing_pay:
                // Toast.makeText(mContext,"生产中",Toast.LENGTH_SHORT).show();
                Intent intent6 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent6);
                break;


        }
    }



    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

   /* @OnClick({R.id.ib_user_icon_avator, R.id.tv_username, R.id.rl_header, R.id.tv_all_order, R.id.tv_user_pay, R.id.tv_doing_pay, R.id.tv_user_receive, R.id.tv_user_finish, R.id.tv_user_drawback, R.id.tv_user_location, R.id.ib_user_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_user_icon_avator:
                break;
            case R.id.tv_username:
                Toast.makeText(mContext, "登录注册", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            case R.id.rl_header:
                break;
            case R.id.tv_all_order:
                Toast.makeText(mContext, "查看全部订单", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_user_pay:
                // Toast.makeText(mContext,"待付款",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(mContext, GoodsActivity.class);
                intent2.putExtra("position1", mPosition);
                startActivity(intent2);

                break;
            case R.id.tv_doing_pay:
                // Toast.makeText(mContext,"生产中",Toast.LENGTH_SHORT).show();
                Intent intent6 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent6);
                break;
            case R.id.tv_user_receive:
                // Toast.makeText(mContext,"待收货",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_user_finish:
                // Toast.makeText(mContext,"待评价",Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent4);
                break;
            case R.id.tv_user_drawback:
                // Toast.makeText(mContext,"退款",Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(mContext, GoodsActivity.class);
                startActivity(intent5);
                break;
            case R.id.tv_user_location:
                Intent intent7 = new Intent(mContext, PositionActivity.class);
                startActivity(intent7);
                Toast.makeText(mContext, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_user_message:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }
*/

   /* public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {
            //设置标题的背景颜色  -透明
            tvUsercenter.setBackgroundColor(Color.argb((int) 0, 0,0,255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //滑动距离 ： 总距离 = 改变的透明度 ： 总透明度
            //改变的透明度 = (滑动距离 ：总距离) *总透明度

            tvUsercenter.setTextColor(Color.argb((int) alpha, 255,255,255));
            tvUsercenter.setBackgroundColor(Color.argb((int) alpha, 0,0,255));
        } else {
            //滑动到banner下面设置普通颜色 - 非透明
            tvUsercenter.setBackgroundColor(Color.argb((int) 255, 0,0,255));
        }
    }*/

}
