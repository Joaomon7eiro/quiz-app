package com.example.surveyapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    int mCorrectAnswers;
    TextView mResultsTitle;
    RatingBar mRatingBar;
    TextView mResultTextView;
    Button mShareButton;
    Button mHomeButton;
    ImageView mResultImage;
    Drawable mResultImageDrawable;
    int mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final Intent intent = getIntent();

        mCorrectAnswers = intent.getIntExtra(QuizActivity.CORRECT_ANSWERS, 0);

        mResultImage = findViewById(R.id.result_image);
        mResultTextView = findViewById(R.id.text_result);

        mResultsTitle = findViewById(R.id.results);
        mResultsTitle.setText(getString(R.string.results_title, String.valueOf(mCorrectAnswers)));

        mRatingBar = findViewById(R.id.rating);
        mRatingBar.setRating(mCorrectAnswers);

        final String mResultText = getResultText(mCorrectAnswers);
        mResultTextView.setText(mResultText);
        mResultImage.setImageDrawable(mResultImageDrawable);

        mShareButton = findViewById(R.id.share);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = "Acertei " + mCorrectAnswers;

                String mymeType = "text/plain";

                ShareCompat.IntentBuilder
                        .from(ResultsActivity.this)
                        .setText(txt)
                        .setType(mymeType)
                        .startChooser();
            }
        });

        mHomeButton = findViewById(R.id.home);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(getApplication(), MainActivity.class);
                startActivity(intentHome);
            }
        });
    }

    private String getResultText(int mCorrectAnswers) {
        switch (mCorrectAnswers) {
            case 0:
                mImage = R.drawable.r0;
                mResultImageDrawable = getDrawable(R.drawable.r0);
                return getString(R.string.result_very_bad);
            case 1:
                mImage = R.drawable.r1;
                mResultImageDrawable = getDrawable(R.drawable.r1);
                return getString(R.string.result_bad);
            case 2:
                mImage = R.drawable.r2;
                mResultImageDrawable = getDrawable(R.drawable.r2);
                return getString(R.string.result_almost_normal);
            case 3:
                mImage = R.drawable.r3;
                mResultImageDrawable = getDrawable(R.drawable.r3);
                return getString(R.string.result_normal);
            case 4:
                mImage = R.drawable.r4;
                mResultImageDrawable = getDrawable(R.drawable.r4);
                return getString(R.string.result_good);
            case 5:
                mImage = R.drawable.r5;
                mResultImageDrawable = getDrawable(R.drawable.r5);
                return getString(R.string.result_very_good);
            default:
                // TODO
                return getString(R.string.result_very_bad);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
