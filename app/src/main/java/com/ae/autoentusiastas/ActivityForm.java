package com.ae.autoentusiastas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ae.autoentusiastas.fragments.PageFragment1_Type;
import com.ae.autoentusiastas.fragments.PageFragment2_Preferences;
import com.ae.autoentusiastas.fragments.PageFragment3_Profile;

import java.util.ArrayList;
import java.util.List;

public class ActivityForm extends AppCompatActivity {

    private Button btnNext;
    private Button btnBack;

    private ViewPager pagerForm;
    private PagerAdapter pagerAdapterForm;

    private ProgressBar progressBarForm;
    private LinearLayout layoutLayoutDotsForm;
    private TextView[] txtDots;

    private int currentPageForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        List<Fragment> fragmentListForm = new ArrayList<>();
        fragmentListForm.add(new PageFragment1_Type());
        fragmentListForm.add(new PageFragment2_Preferences());
        fragmentListForm.add(new PageFragment3_Profile());

        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);
        progressBarForm = findViewById(R.id.progressBarForm);
        layoutLayoutDotsForm = findViewById(R.id.linearLayoutForm);
        pagerForm = findViewById(R.id.pagerForm);


        pagerAdapterForm = new SliderPagerAdapter(getSupportFragmentManager(), fragmentListForm);
        pagerForm.setAdapter(pagerAdapterForm);

        addDotsForm(0);

        pagerForm.addOnPageChangeListener(viewPagerListenerForm);


        // OnClickListeners:

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pagerForm.getCurrentItem() == txtDots.length -1) {
                    exitForm();
                } else {
                    pagerForm.setCurrentItem(currentPageForm + 1);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerForm.setCurrentItem(currentPageForm - 1);
            }
        });

    }

    public void addDotsForm(int position) {
        txtDots = new TextView[3];
        layoutLayoutDotsForm.removeAllViews();

        for (int i = 0; i < txtDots.length; i++) {

            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(35);
            txtDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            layoutLayoutDotsForm.addView(txtDots[i]);
        }

        if (txtDots.length > 0) {
            txtDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewPagerListenerForm = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsForm(position);

            currentPageForm = position;

            if (currentPageForm == 0) {
                btnBack.setEnabled(false);
                btnBack.setVisibility(View.INVISIBLE);

                btnNext.setText("AVANÇAR");
            } else if (currentPageForm == txtDots.length - 1) {
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);

                btnNext.setText("FINALIZAR");
            } else {
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);

                btnNext.setText("AVANÇAR");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void exitForm() {
        startActivity(new Intent(getApplicationContext(), ActivityMenu.class));
        finish();
    }
}
