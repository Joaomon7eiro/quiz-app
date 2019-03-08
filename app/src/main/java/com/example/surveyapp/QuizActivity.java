package com.example.surveyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {
    RadioGroup mAnswers1;
    RadioGroup mAnswers2;
    Button mActionButton;
    TextView mQuestionText;
    TextView mCurrentQuestion;
    ImageView mQuestionImage;

    int mCurrentQuestionNumber = 1;
    int mCorrectAnswers = 0;
    static final int TOTAL_OF_QUESTIONS = 10;

    View.OnClickListener confirmAnswer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mCurrentQuestionNumber) {
                case 1:
                    handlerQuestionOneAnswer();
                    break;
                case 2:
                    handlerQuestionTwoAnswer();
                    break;
                default:
            }
            Toast.makeText(getApplication(), "Total de acertos " + mCorrectAnswers + "/10", Toast.LENGTH_LONG).show();
            if (mCurrentQuestionNumber == TOTAL_OF_QUESTIONS) {
                mActionButton.setText(getString(R.string.result));
                mActionButton.setOnClickListener(showResults);
            } else {
                mActionButton.setText(getString(R.string.next));
                mActionButton.setOnClickListener(nextQuestion);
            }
        }
    };

    View.OnClickListener nextQuestion = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCurrentQuestionNumber++;
            switch (mCurrentQuestionNumber) {
                case 2:
                    findViewById(R.id.q1_answers).setVisibility(View.GONE);
                    setQuestionTwo();
                    break;
                default:
            }
            mCurrentQuestion.setText(getString(R.string.current_question, String.valueOf(mCurrentQuestionNumber)));
            mActionButton.setText(getString(R.string.confirm));
            inactiveActionButton();
        }
    };

    View.OnClickListener showResults = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: implement results
            Toast.makeText(getApplication(), "Mostrar resultados", Toast.LENGTH_LONG).show();
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupOnCheckedChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            activeActionButton();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mAnswers1 = findViewById(R.id.q1_answers);
        mAnswers2 = findViewById(R.id.q2_answers);

        mAnswers1.setOnCheckedChangeListener(radioGroupOnCheckedChange);
        mAnswers2.setOnCheckedChangeListener(radioGroupOnCheckedChange);

        mQuestionText = findViewById(R.id.question_text);
        mQuestionImage = findViewById(R.id.question_image);
        mActionButton = findViewById(R.id.actions_button);
        mCurrentQuestion = findViewById(R.id.current_question);

        mCurrentQuestion.setText(getString(R.string.current_question, String.valueOf(mCurrentQuestionNumber)));
        inactiveActionButton();
    }

    private void inactiveActionButton() {
        mActionButton.setClickable(false);
        mActionButton.setTextColor(getResources().getColor(android.R.color.black));
        mActionButton.setBackgroundResource(R.drawable.inactive_rounded_button);
    }

    private void activeActionButton() {
        mActionButton.setClickable(true);
        mActionButton.setTextColor(getResources().getColor(android.R.color.white));
        mActionButton.setBackgroundResource(R.drawable.rounded_button);
        mActionButton.setOnClickListener(confirmAnswer);
    }

    private void setQuestionTwo() {
        mQuestionText.setText(getString(R.string.question_two));
        mQuestionImage.setImageDrawable(getResources().getDrawable(R.drawable.question_2));
        findViewById(R.id.q2_answers).setVisibility(View.VISIBLE);
    }

    private void handlerQuestionOneAnswer() {
        RadioGroup mAnswers = findViewById(R.id.q1_answers);
        RadioButton mAnswer1 = findViewById(R.id.q1_answer1);
        RadioButton mAnswer2 = findViewById(R.id.q1_answer2);
        RadioButton mAnswer3 = findViewById(R.id.q1_answer3);
        RadioButton mAnswer4 = findViewById(R.id.q1_answer4);

        int mCheckedRadioButtonId = mAnswers.getCheckedRadioButtonId();
        RadioButton mCheckedRadioButton = findViewById(mCheckedRadioButtonId);

        mAnswer1.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        mAnswer2.setButtonTintList(getResources().getColorStateList(R.color.colorGreen));
        mAnswer3.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        mAnswer4.setButtonTintList(getResources().getColorStateList(R.color.colorGray));

        mAnswer1.setTextColor(getResources().getColor(R.color.colorGray));
        mAnswer2.setTextColor(getResources().getColor(R.color.colorGreen));
        mAnswer3.setTextColor(getResources().getColor(R.color.colorGray));
        mAnswer4.setTextColor(getResources().getColor(R.color.colorGray));

        if (mCheckedRadioButtonId != R.id.q1_answer2) {
            mCheckedRadioButton.setTextColor(getResources().getColor(R.color.colorRed));
            mCheckedRadioButton.setButtonTintList(getResources().getColorStateList(R.color.colorRed));
        } else {
            mCorrectAnswers++;
        }
    }

    private void handlerQuestionTwoAnswer() {
        RadioGroup mAnswers = findViewById(R.id.q2_answers);
        RadioButton mAnswer1 = findViewById(R.id.q2_answer1);
        RadioButton mAnswer2 = findViewById(R.id.q2_answer2);
        RadioButton mAnswer3 = findViewById(R.id.q2_answer3);
        RadioButton mAnswer4 = findViewById(R.id.q2_answer4);

        int mCheckedRadioButtonId = mAnswers.getCheckedRadioButtonId();
        RadioButton mCheckedRadioButton = findViewById(mCheckedRadioButtonId);

        mAnswer1.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        mAnswer2.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        mAnswer3.setButtonTintList(getResources().getColorStateList(R.color.colorGreen));
        mAnswer4.setButtonTintList(getResources().getColorStateList(R.color.colorGray));

        mAnswer1.setTextColor(getResources().getColor(R.color.colorGray));
        mAnswer2.setTextColor(getResources().getColor(R.color.colorGray));
        mAnswer3.setTextColor(getResources().getColor(R.color.colorGreen));
        mAnswer4.setTextColor(getResources().getColor(R.color.colorGray));

        if (mCheckedRadioButtonId != R.id.q2_answer3) {
            mCheckedRadioButton.setTextColor(getResources().getColor(R.color.colorRed));
            mCheckedRadioButton.setButtonTintList(getResources().getColorStateList(R.color.colorRed));
        } else {
            mCorrectAnswers++;
        }
    }

    @Override
    public void onBackPressed() {
        if (mCurrentQuestionNumber == 1) {
            super.onBackPressed();
        } else {
            Toast.makeText(getApplication(), getString(R.string.refuse_message), Toast.LENGTH_LONG).show();
        }
    }
}
