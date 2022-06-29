package com.example.Dairyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class notedetails extends AppCompatActivity {


    private TextView mtitleofnotedetail,mcontentofnotedetail,mdateofnotedetail,rateCount,showRating;
    FloatingActionButton mgotoeditnote;
    FloatingActionButton msharenote;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue; String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetails);
        mtitleofnotedetail=findViewById(R.id.titleofnotedetail);
        mcontentofnotedetail=findViewById(R.id.contentofnotedetail);
        mdateofnotedetail=findViewById(R.id.dateofnotedetail);
        mgotoeditnote=findViewById(R.id.gotoeditnote);
        msharenote=findViewById(R.id.sharenote);
        Toolbar toolbar=findViewById(R.id.toolbarofnotedetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rateCount = findViewById(R.id.rateCount);
        ratingBar = findViewById(R.id.ratingBar);
        review = findViewById(R.id.review);
        submit = findViewById(R.id.submitBtn);
        showRating = findViewById(R.id.showRating);

        Intent data=getIntent();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue  = ratingBar.getRating();

                if (rateValue<=1 && rateValue>0) {
                    rateCount.setText("Bad " +rateValue +"/5");
                } else if (rateValue<=2 && rateValue>1) {
                    rateCount.setText("Ok " +rateValue +"/5");
                } else if (rateValue<=3 && rateValue>2) {
                    rateCount.setText("Good " +rateValue +"/5");
                } else if (rateValue<=4 && rateValue>3) {
                    rateCount.setText("Very good " +rateValue +"/5");
                } else if (rateValue<=5 && rateValue>4) {
                    rateCount.setText("Best " +rateValue +"/5");
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = rateCount.getText().toString();
                showRating.setText("Your Rating: \n" +temp +"\n" +review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });

        mgotoeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),editnoteactivity.class);
                intent.putExtra("title",data.getStringExtra("title"));
                intent.putExtra("content",data.getStringExtra("content"));
                intent.putExtra("date",data.getStringExtra("date"));
                intent.putExtra("noteId",data.getStringExtra("noteId"));
                v.getContext().startActivity(intent);
            }
        });

        msharenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, mtitleofnotedetail.getText().toString());
                shareIntent.setType("text/plain");
                shareIntent = Intent.createChooser(shareIntent, "Share via: ");
                startActivity(shareIntent);
            }
        });

        mcontentofnotedetail.setText(data.getStringExtra("content"));
        mtitleofnotedetail.setText(data.getStringExtra("title"));
        mdateofnotedetail.setText((data.getStringExtra("date")));


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}