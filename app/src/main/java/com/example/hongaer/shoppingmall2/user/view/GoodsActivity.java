package com.example.hongaer.shoppingmall2.user.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.example.hongaer.shoppingmall2.R;
import com.example.hongaer.shoppingmall2.app.BanViewPager;
import com.example.hongaer.shoppingmall2.user.adapter.MainTabAdapter;
import com.example.hongaer.shoppingmall2.user.fragmet.FirstPageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsActivity extends AppCompatActivity {

    @BindView(R.id.ib_goods_back)
    ImageButton ibGoodsBack;
    @BindView(R.id.tab_title)
    TabLayout tabTitle;
    @BindView(R.id.view_pager)
    BanViewPager viewPager;
    private List<FirstPageFragment> mFirstFragments;
    private TabLayout mTablayout;  //顶部标题选项布局
    private ViewPager mViewpager;
    private String[] mList_title;
    private MainTabAdapter mAdapter_title;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods2);
        ButterKnife.bind(this);
        initData();
        initView();
        initListener();
    }
    private void initListener() {
        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //  Log.i("777","ontabselected----------->"+tab.getPosition());
                int position = tab.getPosition();
                for (int i = 0; i < mFirstFragments.size(); i++) {

                    mFirstFragments.get(position).setPosition(position);


                }

             //  mViewpager.setCurrentItem(position);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void initData() {
        mList_title = getResources().getStringArray(R.array.tab_title);

        mFirstFragments = new ArrayList<>();


        //通过标题个数来创建fragmen；

        for (int i = 0; i < mList_title.length; i++) {
            FirstPageFragment first = new FirstPageFragment();
            mFirstFragments.add(first);
            int mPosition=getIntent().getIntExtra("position1", 0);
            mFirstFragments.get(mPosition);
        }


    }

    // 初始化布局
    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.tab_title);
        mViewpager = (ViewPager) findViewById(R.id.view_pager);

        mAdapter_title = new MainTabAdapter(getSupportFragmentManager(), mFirstFragments, mList_title);//设置适配器，即将数据源与适配器绑定
        mViewpager.setAdapter(mAdapter_title);//关联适配器，即将适配器设置到view上

        //  TabLayout 绑定viewpager


        mTablayout.setupWithViewPager(mViewpager);


    }

    @OnClick(R.id.ib_goods_back)
    public void onClick() {
        finish();
    }
}
