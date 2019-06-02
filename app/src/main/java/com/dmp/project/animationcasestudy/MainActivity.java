package com.dmp.project.animationcasestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout clForeground;
    boolean isRevealed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isRevealed  =false;
        Button btnReveal = findViewById(R.id.btn_reveal);
        clForeground = findViewById(R.id.cl_foreground);

        btnReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRevealed){
                    rippleReveal(clForeground);
//                    clForeground.setVisibility(View.VISIBLE);
//                    slideDown(clForeground);
                    isRevealed = true;
//                  slideUpVersion2(clForeground);

                }else{
                    isRevealed =false;
                    clForeground.setVisibility(View.INVISIBLE   );

                    slideDown(clForeground);
                }

            }
        });





    }


    private void rippleReveal(View view){
        view.setVisibility(View.INVISIBLE);


// get the center for the clipping circle
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

// get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

        //Interpolator for giving effect to animation
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        // Duration of the animation
        anim.setDuration(1000);

// make the view visible and start the animation
        anim.start();
        view.setVisibility(View.VISIBLE);
    }


    private void slideDown(View view){
        view.animate().translationY(view.getHeight());
        view.setVisibility(View.INVISIBLE);
    }


    private void slideUpVersion1(View view){
        view.animate().translationY(0);
        view.setVisibility(View.VISIBLE);
    }

    private void slideUpVersion2(final View view){
        view.animate()
                .translationY(0f)
                .alpha(0.7f)
                .setDuration(2200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }
                });
    }
}
