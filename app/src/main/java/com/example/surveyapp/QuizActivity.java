package com.example.surveyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {

    TextView mQuestionText;
    TextView mCurrentQuestion;
    ImageView mQuestionImage;
    int mCurrentQuestionNumber = 1;
    Button mConfirmButton;

    View.OnClickListener confirmAnswer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mCurrentQuestionNumber) {
                case 1:
                    handlerQuestionOneAnswer();
                    break;
                case 2:
                    findViewById(R.id.answers_one).setVisibility(View.GONE);
                    setQuestionTwo();
                    break;
                default:
            }
        }
    };


    View.OnClickListener nextQuestion = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCurrentQuestionNumber++;
            switch (mCurrentQuestionNumber) {
                case 1:
                    setQuestionTwo();
                    break;
                case 2:
                    findViewById(R.id.answers_one).setVisibility(View.GONE);
                    setQuestionTwo();
                    mConfirmButton.setOnClickListener(confirmAnswer);
                    mConfirmButton.setText(getString(R.string.confirm_button));
                    break;
                default:
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionText = findViewById(R.id.question_text);
        mCurrentQuestion = findViewById(R.id.current_question);
        mQuestionImage = findViewById(R.id.question_image);
        mConfirmButton = findViewById(R.id.confirm_button);

        mConfirmButton.setOnClickListener(confirmAnswer);

        setQuestionOne();
    }


    private void setQuestionOne() {
        mCurrentQuestion.setText(getString(R.string.current_question, String.valueOf(mCurrentQuestionNumber)));
        mQuestionText.setText(getString(R.string.question_one));
        mQuestionImage.setImageDrawable(getResources().getDrawable(R.drawable.question_1));
        findViewById(R.id.answers_one).setVisibility(View.VISIBLE);
    }

    private void handlerQuestionOneAnswer() {
        RadioButton answer1 = findViewById(R.id.q1_answer_1);
        RadioButton answer2 = findViewById(R.id.q1_answer_2);
        RadioButton answer3 = findViewById(R.id.q1_answer_3);
        RadioButton answer4 = findViewById(R.id.q1_answer_4);

        answer1.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        answer2.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        answer3.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        answer4.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        answer2.setTextColor(getResources().getColor(R.color.colorGreen));
        answer1.setTextColor(getResources().getColor(R.color.colorGray));
        answer3.setTextColor(getResources().getColor(R.color.colorGray));
        answer4.setTextColor(getResources().getColor(R.color.colorGray));

        if (answer1.isChecked()) {
            answer1.setTextColor(getResources().getColor(R.color.colorRed));
        } else if (answer2.isChecked()) {
            // TODO
        } else if (answer3.isChecked()) {
            answer3.setTextColor(getResources().getColor(R.color.colorRed));
        } else {
            answer4.setTextColor(getResources().getColor(R.color.colorRed));
        }

        mConfirmButton.setText(getString(R.string.next_button));
        mConfirmButton.setOnClickListener(nextQuestion);
    }

    private void setQuestionTwo() {
        mCurrentQuestion.setText(getString(R.string.current_question, String.valueOf(mCurrentQuestionNumber)));
        mQuestionText.setText(getString(R.string.question_two));
        mQuestionImage.setImageDrawable(getResources().getDrawable(R.drawable.question_2));
        findViewById(R.id.answers_two).setVisibility(View.VISIBLE);
    }


    @Override
    public void onBackPressed() {
        if (mCurrentQuestionNumber == 1) {
            super.onBackPressed();
        } else {
            Toast.makeText(getApplication(), "Vair voltar não safado", Toast.LENGTH_LONG).show();
        }

    }
}
