package com.example.snape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserInformation userInformationListener = new UserInformation();
        userInformationListener.startFetching();

        ViewPager viewPager = findViewById(R.id.viewpager);

        adapterViewPager  = new MyPageAdapter(getSupportFragmentManager());//adapter controls the viewpager
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(1);//for camera to appear first

    }
    public static class MyPageAdapter extends FragmentPagerAdapter{

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ChatFragment().newInstance();
                case 1:
                    return new CameraFragment().newInstance();
                case 2:
                    return new StoryFragment().newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;//no of pages or fragments
        }
    }
}