package com.example.elective2_garcia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.squareup.picasso.Picasso;

public class ProductViewActivity2 extends AppCompatActivity {

    // initialize variables
    ImageButton btPrevious,btNext;
    ImageSwitcher imageSwitcher;
    TextView disname, disdesc, disprice;

    int imageList[]={/*pass the image[0], image[1] here using picasso or i dont know hahaha*/};
    int count=imageList.length;
    int currentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view2);

        // assign variables
        btPrevious=findViewById(R.id.bt_previous);
        btNext=findViewById(R.id.bt_next);
        imageSwitcher=findViewById(R.id.image_switcher);
        disname = findViewById(R.id.pdname);
        disdesc = findViewById(R.id.pddesc);
        disprice = findViewById(R.id.pdprice);

        Intent intent = getIntent();
        String p1 = intent.getStringExtra("name");
        String p2 = intent.getStringExtra("desc");
        String p3 = intent.getStringExtra("price");
        /*p4 results to a image url from the firestore firebase array photoUrl [0]*/
        String p4 = intent.getStringExtra("image1");
        /*p4 results to a image url from the firestore firebase array photoUrl [0]*/
        String p5 = intent.getStringExtra("image2");
        Log.d("E100", p4);

        disname.setText(p1);
        disdesc.setText(p2);
        disprice.setText(p3);

        // implement the ViewFactory interface and implement
        // unimplemented method that returns an imageView
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory(){
            @Override
            public View makeView() {
                ImageView imageView= new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setMaxWidth(250);
                imageView.setMaxHeight(250);
                // returning imageview
                return imageView;
            }
        });
        imageSwitcher.setImageResource(imageList[0]);
        // set on click listener on left button
        btPrevious.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setting animation to swipe image from right to left
                imageSwitcher.setInAnimation(ProductViewActivity2.this,R.anim.from_right);
                imageSwitcher.setOutAnimation(ProductViewActivity2.this,R.anim.to_left);
                --currentIndex;
                // condition
                if(currentIndex<0)
                    currentIndex=imageList.length-1;
                imageSwitcher.setImageResource((imageList[currentIndex]));
            }
        });
        // set on click listener on right(next) button
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // setting animation to swipe image from left to right
                imageSwitcher.setInAnimation(ProductViewActivity2.this,R.anim.from_left);
                imageSwitcher.setOutAnimation(ProductViewActivity2.this,R.anim.to_right);
                currentIndex++;
                // condition
                if(currentIndex==count)
                    currentIndex=0;
                imageSwitcher.setImageResource(imageList[currentIndex]);
            }
        });
    }
}
