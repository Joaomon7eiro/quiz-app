package com.example.surveyapp;

import android.content.Intent;
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
    RadioGroup mAnswers4;
    RadioGroup mAnswers5;
    Button mActionButton;
    TextView mQuestionText;
    TextView mCurrentQuestion;
    ImageView mQuestionImage;

    int mCurrentQuestionNumber = 1;
    int mCorrectAnswers = 0;
    boolean mActionButtonIsClickable = true;
    static final int TOTAL_OF_QUESTIONS = 5;
    public static final String CORRECT_ANSWERS = "correct_answers";

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
                case 4:
                    handlerQuestionFourAnswer();
                    break;
                case 5:
                    handlerQuestionFiveAnswer();
                    break;
                default:
            }
            Toast.makeText(getApplication(), "Total de acertos "
                    + mCorrectAnswers + "/5", Toast.LENGTH_LONG).show();
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
                    mAnswers1.setVisibility(View.GONE);
                    inactiveActionButton();
                    setQuestionTwo();
                    break;
                case 3:
                    mAnswers2.setVisibility(View.GONE);
                    inactiveActionButton();
                    setQuestionThree();
                    break;
                case 4:
                    mAnswer3.setVisibility(View.GONE);
                    inactiveActionButton();
                    setQuestionFour();
                    break;
                case 5:
                    mAnswers4.setVisibility(View.GONE);
                    inactiveActionButton();
                    setQuestionFive();
                default:
            }
            mCurrentQuestion.setText(getString(R.string.current_question, String.valueOf(mCurrentQuestionNumber)));
            mActionButton.setText(getString(R.string.confirm));
        }
    };

    View.OnClickListener showResults = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), ResultsActivity.class);
            intent.putExtra(CORRECT_ANSWERS, mCorrectAnswers);
            startActivity(intent);
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupOnCheckedChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (mActionButton.getText().toString().equals(getString(R.string.confirm))){
                activeActionButton();
            }

        }
    };

    TextWatcher editTextTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mCurrentQuestionNumber == 3) {
                if (mAnswer3.getText().toString().equals("")) {
                    inactiveActionButton();
                } else {
                    activeActionButton();
                }
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("CURRENT_QUESTION", mCurrentQuestionNumber);
        outState.putString("ACTION_BUTTON_TEXT", mActionButton.getText().toString());
        outState.putBoolean("ACTION_BUTTON_IS_CLICKABLE", mActionButtonIsClickable);
        outState.putInt("CORRECT_ANSWERS", mCorrectAnswers);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mAnswers1 = findViewById(R.id.q1_answers);
        mAnswers2 = findViewById(R.id.q2_answers);
        mAnswers2.setOnCheckedChangeListener(radioGroupOnCheckedChange);
        mAnswer3 = findViewById(R.id.q3_answer);
        mAnswer3.addTextChangedListener(editTextTextWatch);
        mAnswers4 = findViewById(R.id.q4_answers);
        mAnswers4.setOnCheckedChangeListener(radioGroupOnCheckedChange);
        mAnswers5 = findViewById(R.id.q5_answers);
        mAnswers5.setOnCheckedChangeListener(radioGroupOnCheckedChange);

        mQuestionText = findViewById(R.id.question_text);
        mQuestionImage = findViewById(R.id.question_image);
        mActionButton = findViewById(R.id.actions_button);
        mCurrentQuestion = findViewById(R.id.current_question);

        if (savedInstanceState != null) {
            mCurrentQuestionNumber = savedInstanceState.getInt("CURRENT_QUESTION");
            mCorrectAnswers = savedInstanceState.getInt("CORRECT_ANSWERS");
            mActionButtonIsClickable = savedInstanceState.getBoolean("ACTION_BUTTON_IS_CLICKABLE");
            String actionButtonText = savedInstanceState.getString("ACTION_BUTTON_TEXT");
            mActionButton.setText(actionButtonText);

            if (mActionButtonIsClickable) {
                activeActionButton();
            } else {
                inactiveActionButton();
            }

            switch (mCurrentQuestionNumber) {
                case 2:
                    mAnswers1.setVisibility(View.GONE);
                    setQuestionTwo();
                    break;
                case 3:
                    mAnswers1.setVisibility(View.GONE);
                    mAnswers2.setVisibility(View.GONE);
                    setQuestionThree();
                    break;
                case 4:
                    mAnswers1.setVisibility(View.GONE);
                    mAnswers2.setVisibility(View.GONE);
                    mAnswer3.setVisibility(View.GONE);
                    setQuestionFour();
                    break;
                case 5:
                    mAnswers1.setVisibility(View.GONE);
                    mAnswers2.setVisibility(View.GONE);
                    mAnswer3.setVisibility(View.GONE);
                    mAnswers4.setVisibility(View.GONE);
                    setQuestionFive();
                default:
            }
            if (actionButtonText.equals(getString(R.string.confirm))) {
                mActionButton.setOnClickListener(confirmAnswer);
            } else {
                mActionButton.setOnClickListener(nextQuestion);
            }
        } else {
            mActionButton.setOnClickListener(confirmAnswer);
        }
        mCurrentQuestion.setText(getString(R.string.current_question,
                String.valueOf(mCurrentQuestionNumber)));
    }

    private void inactiveActionButton() {
        mActionButtonIsClickable = false;
        mActionButton.setClickable(false);
        mActionButton.setTextColor(getResources().getColor(android.R.color.black));
        mActionButton.setBackgroundResource(R.drawable.inactive_rounded_button);
    }

    private void activeActionButton() {
        mActionButtonIsClickable = true;
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
        mQuestionText.setText(getString(R.string.question_three));
        mQuestionImage.setImageDrawable(getResources().getDrawable(R.drawable.question_3));
        mAnswer3.setVisibility(View.VISIBLE);
    }

    private void setQuestionFour() {
        mQuestionText.setText(getString(R.string.question_four));
        mQuestionImage.setImageDrawable(getResources().getDrawable(R.drawable.question_4));
        mAnswers4.setVisibility(View.VISIBLE);
    }

    private void setQuestionFive() {
        mQuestionText.setText(getString(R.string.question_five));
        mQuestionImage.setImageDrawable(getResources().getDrawable(R.drawable.question_5));
        mAnswers5.setVisibility(View.VISIBLE);
    }

    private void handlerQuestionOneAnswer() {
        CheckBox answer1 = findViewById(R.id.q1_answer1);
        CheckBox answer2 = findViewById(R.id.q1_answer2);
        CheckBox answer3 = findViewById(R.id.q1_answer3);
        CheckBox answer4 = findViewById(R.id.q1_answer4);

        if (answer1.isChecked()) {
            answer1.setTextColor(getResources().getColor(R.color.colorRed));
            answer1.setButtonTintList(getResources().getColorStateList(R.color.colorRed));
        } else {
            answer1.setTextColor(getResources().getColor(R.color.colorGray));
            answer1.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        }
        if (answer2.isChecked()) {
            answer2.setTextColor(getResources().getColor(R.color.colorRed));
            answer2.setButtonTintList(getResources().getColorStateList(R.color.colorRed));
        } else {
            answer2.setTextColor(getResources().getColor(R.color.colorGray));
            answer2.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        }
        answer3.setButtonTintList(getResources().getColorStateList(R.color.colorGreen));
        answer4.setButtonTintList(getResources().getColorStateList(R.color.colorGreen));
        answer3.setTextColor(getResources().getColor(R.color.colorGreen));
        answer4.setTextColor(getResources().getColor(R.color.colorGreen));

        if (answer3.isChecked() && answer4.isChecked() && !answer1.isChecked() && !answer2.isChecked()) {
            mCorrectAnswers++;
        }
    }

    private void handlerQuestionTwoAnswer() {
        RadioButton answer1 = findViewById(R.id.q2_answer1);
        RadioButton answer2 = findViewById(R.id.q2_answer2);
        RadioButton answer3 = findViewById(R.id.q2_answer3);
        RadioButton answer4 = findViewById(R.id.q2_answer4);

        int checkedRadioButtonId = mAnswers2.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);

        answer1.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        answer2.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        answer3.setButtonTintList(getResources().getColorStateList(R.color.colorGreen));
        answer4.setButtonTintList(getResources().getColorStateList(R.color.colorGray));

        answer1.setTextColor(getResources().getColor(R.color.colorGray));
        answer2.setTextColor(getResources().getColor(R.color.colorGray));
        answer3.setTextColor(getResources().getColor(R.color.colorGreen));
        answer4.setTextColor(getResources().getColor(R.color.colorGray));

        if (checkedRadioButton != answer3) {
            checkedRadioButton.setTextColor(getResources().getColor(R.color.colorRed));
            checkedRadioButton.setButtonTintList(getResources().getColorStateList(R.color.colorRed));
        } else {
            mCorrectAnswers++;
        }
    }

    private void handlerQuestionThreeAnswer() {
        String answer = getString(R.string.q3_answer);
        if (mAnswer3.getText().toString().toLowerCase().equals(answer.toLowerCase())) {
            mAnswer3.setBackgroundTintList(getResources().getColorStateList(R.color.colorGreen));
            mAnswer3.setTextColor(getResources().getColor(R.color.colorGreen));
            mCorrectAnswers++;
        } else {
            mAnswer3.setBackgroundTintList(getResources().getColorStateList(R.color.colorRed));
            mAnswer3.setTextColor(getResources().getColor(R.color.colorRed));
        }
    }

    private void handlerQuestionFourAnswer() {
        RadioButton answer1 = findViewById(R.id.q4_answer1);
        RadioButton answer2 = findViewById(R.id.q4_answer2);

        int checkedRadioButtonId = mAnswers4.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);

        answer2.setTextColor(getResources().getColor(R.color.colorGreen));
        answer2.setButtonTintList(getResources().getColorStateList(R.color.colorGreen));

        if (checkedRadioButton == answer1) {
            answer1.setTextColor(getResources().getColor(R.color.colorRed));
            answer1.setButtonTintList(getResources().getColorStateList(R.color.colorRed));
        } else {
            answer1.setTextColor(getResources().getColor(R.color.colorGray));
            answer1.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
            mCorrectAnswers++;
        }
    }

    private void handlerQuestionFiveAnswer() {
        mQuestionImage.setImageDrawable(getDrawable(R.drawable.question_5b));
        RadioButton answer1 = findViewById(R.id.q5_answer1);
        RadioButton answer2 = findViewById(R.id.q5_answer2);
        RadioButton answer3 = findViewById(R.id.q5_answer3);
        RadioButton answer4 = findViewById(R.id.q5_answer4);

        int checkedRadioButtonId = mAnswers5.getCheckedRadioButtonId();
        RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);

        answer1.setButtonTintList(getResources().getColorStateList(R.color.colorGreen));
        answer2.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        answer3.setButtonTintList(getResources().getColorStateList(R.color.colorGray));
        answer4.setButtonTintList(getResources().getColorStateList(R.color.colorGray));

        answer1.setTextColor(getResources().getColor(R.color.colorGreen));
        answer2.setTextColor(getResources().getColor(R.color.colorGray));
        answer3.setTextColor(getResources().getColor(R.color.colorGray));
        answer4.setTextColor(getResources().getColor(R.color.colorGray));

        if (checkedRadioButton != answer1) {
            checkedRadioButton.setTextColor(getResources().getColor(R.color.colorRed));
            checkedRadioButton.setButtonTintList(getResources().getColorStateList(R.color.colorRed));
        } else {
            mCorrectAnswers++;
        }
    }

    @Override
    public void onBackPressed() {
        if (mCurrentQuestionNumber == 1 && mActionButton.getText().toString()
                .equals(getString(R.string.confirm))) {
            super.onBackPressed();
        } else {
            Toast.makeText(getApplication(), getString(R.string.refuse_message), Toast.LENGTH_SHORT).show();
        }
    }
}
