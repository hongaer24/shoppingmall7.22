package com.example.hongaer.shoppingmall2.user.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hongaer.shoppingmall2.user.fragmet.FirstPageFragment;

import java.util.List;

/**
 * Created by hongaer on 2017/7/25.
 */

public class MainTabAdapter extends FragmentPagerAdapter {

    private List<FirstPageFragment> mList_fragment;
    private   String[] mList_title;

    public MainTabAdapter(FragmentManager fm, List<FirstPageFragment> list_fragment, String[] List_title) {
        super(fm);
        mList_fragment = list_fragment;
        mList_title = List_title;


    }


    @Override
    //通过position返回要显示的fragment
    public Fragment getItem(int position) {
        return mList_fragment.get(position);
    }

    @Override
    //返回总滑动的fragment总数
    public int getCount() {
        return mList_fragment.size();

    }
    //根据不同的位置返回不同的标题
    public CharSequence getPageTitle(int position){

        return  mList_title[position];
    }

}
