package com.example.surveyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {
    LinearLayout mAnswers1;
    RadioGroup mAnswers2;
    EditText mAnswer3;
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
                case 3:
                    handlerQuestionThreeAnswer();
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
            mCurrentQuestionNumber++;
        }
    };

    View.OnClickListener nextQuestion = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mCurrentQuestionNumber) {
                case 2:
                    mAnswers1.setVisibility(View.GONE);
                    inactiveActionButton();
                    setQuestionTwo();
                    break;
                case 3:
                    mAnswers2.setVisibility(View.GONE);
                    inactiveActionButton();
                    setQuestionThree();
                    break;
                default:
            }
            mCurrentQuestion.setText(getString(R.string.current_question, String.valueOf(mCurrentQuestionNumber)));
            mActionButton.setText(getString(R.string.confirm));
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
        mAnswers2.setOnCheckedChangeListener(radioGroupOnCheckedChange);
        mAnswer3 = findViewById(R.id.q3_answer);
        mAnswer3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mAnswer3.getText().toString().equals("")){
                    inactiveActionButton();
                } else {
                    activeActionButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mQuestionText = findViewById(R.id.question_text);
        mQuestionImage = findViewById(R.id.question_image);
        mActionButton = findViewById(R.id.actions_button);
        mCurrentQuestion = findViewById(R.id.current_question);

        mCurrentQuestion.setText(getString(R.string.current_question, String.valueOf(mCurrentQuestionNumber)));
        mActionButton.setOnClickListener(confirmAnswer);
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
        mAnswers2.setVisibility(View.VISIBLE);
    }

    private void setQuestionThree() {
        mQuestionText.setText(getString(R.string.question_two));
        mQuestionImage.setImageDrawable(getResources().getDrawable(R.drawable.question_2));
        mAnswer3.setVisibility(View.VISIBLE);
    }

    private void handlerQuestionOneAnswer() {
        CheckBox mAnswer1 = findViewById(R.id.q1_answer1);
        CheckBox mAnswer2 = findViewById(R.id.q1_answer2);
        CheckBox mAnswer3 = findViewById(R.id.q1_answer3);
        CheckBox mAnswer4 = findViewById(R.id.q1_answer4);

        if (mAnswer1.isChecked()) {
            mAnswer1.setTextColor(getResources().getColor(R.color.colorRed));
            mAnswer1.setButtonTintList(getResources().getColorStateList(R.color.colorRed));
        } else {
            mAnswer1.setTextColor(getResources().getColor(R.color.colorGray));
            mAnswer1.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        }
        if (mAnswer2.isChecked()) {
            mAnswer2.setTextColor(getResources().getColor(R.color.colorRed));
            mAnswer2.setButtonTintList(getResources().getColorStateList(R.color.colorRed));
        } else {
            mAnswer2.setTextColor(getResources().getColor(R.color.colorGray));
            mAnswer2.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        }
        mAnswer3.setButtonTintList(getResources().getColorStateList(R.color.colorGreen));
        mAnswer4.setButtonTintList(getResources().getColorStateList(R.color.colorGreen));
        mAnswer3.setTextColor(getResources().getColor(R.color.colorGreen));
        mAnswer4.setTextColor(getResources().getColor(R.color.colorGreen));

        if (mAnswer3.isChecked() && mAnswer4.isChecked() && !mAnswer1.isChecked() && !mAnswer2.isChecked()) {
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

    private void handlerQuestionThreeAnswer() {
        if (mAnswer3.getText().toString().equals("teste")) {
            mAnswer3.setBackgroundTintList(getResources().getColorStateList(R.color.colorGreen));
            mAnswer3.setTextColor(getResources().getColor(R.color.colorGreen));
            mCorrectAnswers++;
        } else {
            mAnswer3.setBackgroundTintList(getResources().getColorStateList(R.color.colorRed));
            mAnswer3.setTextColor(getResources().getColor(R.color.colorRed));
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
