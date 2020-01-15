package com.sajal.tasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PageAdapter adapter;
    private Button back,next;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int currentPage;

    private int[] layouts={R.layout.slider1, R.layout.slider2, R.layout.slider3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        viewPager =findViewById(R.id.viewPager);
        adapter=new PageAdapter(layouts,this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                currentPage=position;
                if (currentPage==0){
                    back.setEnabled(false);
                    next.setEnabled(true);
                    back.setVisibility(View.INVISIBLE);
                }
                else if (currentPage==dots.length-1){
                    back.setEnabled(true);
                    next.setEnabled(true);
                    back.setVisibility(View.VISIBLE);
                    next.setText("FINISH");
                }
                else{
                    back.setEnabled(true);
                    next.setEnabled(true);
                    back.setVisibility(View.VISIBLE);
                    next.setText("NEXT");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        back=findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(currentPage-1);
            }
        });

        next=findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage==dots.length-1){
                    startActivity(new Intent(IntroActivity.this,MainActivity.class));
                }
                else
                    viewPager.setCurrentItem(currentPage+1);
            }
        });
        dotsLayout=findViewById(R.id.dotsLayout);
        createDots(0);
    }

    private void  createDots(int position){
        dots = new TextView[layouts.length];
        dotsLayout.removeAllViews();
        for (int i=0;i<layouts.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            if (i==position){
                dots[i].setTextColor(getResources().getColor(R.color.colorAccent));
            }
            else
                dots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            dotsLayout.addView(dots[i]);
        }
    }
}
