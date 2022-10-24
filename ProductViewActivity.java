package com.example.elective2_garcia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ProductViewActivity extends AppCompatActivity {

    // initialize variables
    ImageButton btPrevious,btNext;
    ImageSwitcher imageSwitcher;

    //images here
    int imageList[]={};
    int count=imageList.length;
    int currentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        // assign variables
        btPrevious = findViewById(R.id.bt_previous);
        btNext = findViewById(R.id.bt_next);
        imageSwitcher = findViewById(R.id.image_switcher);

        // implement the ViewFactory interface and implement
        // unimplemented method that returns an imageView
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView= new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT
                        ,ViewGroup.LayoutParams.MATCH_PARENT
                ));
                // return imageView
                return imageView;
            }
        });
        imageSwitcher.setImageResource(imageList[0]);
        //set on click listener on left button
        btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setting animation to swipe image from right to left
                imageSwitcher.setInAnimation(ProductViewActivity.this,R.anim.from_right);
                imageSwitcher.setOutAnimation(ProductViewActivity.this,R.anim.to_left);
                --currentIndex;
                // condition
                if(currentIndex<0)
                    currentIndex=imageList.length-1;
                imageSwitcher.setImageResource((imageList[currentIndex]));
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setting animation to swipe image from left to right
                imageSwitcher.setInAnimation(ProductViewActivity.this,R.anim.from_left);
                imageSwitcher.setOutAnimation(ProductViewActivity.this,R.anim.to_right);
                currentIndex++;
                // condition
                if(currentIndex==count)
                    currentIndex=0;
                imageSwitcher.setImageResource(imageList[currentIndex]);
            }
        });
    }
    public void openProductView(View v){
        Intent intent = new Intent(this, ProductViewActivity.class);
        startActivity(intent);
    }
}
