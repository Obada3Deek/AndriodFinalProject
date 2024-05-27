package com.example.trip_planningproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<Integer> images;
    private List<String> descriptions;
    private TextView textDescription;
    private TabLayout tabLayout;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.buttonStartAPP);


        images = new ArrayList<>();
        images.add(R.drawable.giza);
        images.add(R.drawable.madrid);
        images.add(R.drawable.roma);
        images.add(R.drawable.paris);

        descriptions = new ArrayList<>();
        descriptions.add("Discover the majestic pyramids and rich history of Egypt's ancient civilization.");
        descriptions.add("Experience lively plazas, world-class museums, and delicious Spanish cuisine.");
        descriptions.add("Explore ancient ruins, stunning architecture, and vibrant culture in the heart of Italy.");
        descriptions.add("Stroll through romantic streets, visit iconic landmarks, and savor exquisite French pastries.");

        textDescription = findViewById(R.id.textDescription);
        // Set initial description
        textDescription.setText(descriptions.get(0));

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this, images);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager, true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                textDescription.setText(descriptions.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, LoginPage.class);
                startActivity(nextActivity);
            }
        });
    }
}