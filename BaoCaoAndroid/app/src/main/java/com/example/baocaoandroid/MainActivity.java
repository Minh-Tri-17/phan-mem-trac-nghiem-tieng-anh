package com.example.baocaoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baocaoandroid.model.Question;
import com.example.baocaoandroid.model.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button buttonStart;
    RadioGroup radioGroup;
    RadioButton radioAnswer1, radioAnswer2, radioAnswer3, radioAnswer4;
    TextView textTitle, textContent, textScore, textRight, textTime;
    ArrayList<Question> questionList;
    int questionCount;
    int questionSize;
    boolean answered;
    int count = 0;
    int score = 0;
    int rightSentence = 0;
    Question questionCurient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        addEvent();

        Database database = new Database(this);
        questionList = database.getQuestions();
        questionSize = questionList.size();
        showNextQuestion();
        showTime();
    }

    private void showTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        textTime.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void showNextQuestion() {
        radioAnswer1.setBackground(getDrawable(R.drawable.linner_palegreen));
        radioAnswer2.setBackground(getDrawable(R.drawable.linner_palegreen));
        radioAnswer3.setBackground(getDrawable(R.drawable.linner_palegreen));
        radioAnswer4.setBackground(getDrawable(R.drawable.linner_palegreen));

        radioGroup.clearCheck();

        if (questionCount < questionSize) {
            questionCurient = questionList.get(questionCount);

            textContent.setText(questionCurient.getQuestion());
            radioAnswer1.setText(questionCurient.getOptions1());
            radioAnswer2.setText(questionCurient.getOptions2());
            radioAnswer3.setText(questionCurient.getOptions3());
            radioAnswer4.setText(questionCurient.getOptions4());

            questionCount++;
            textTitle.setText("Question: " + questionCount + " / " + questionSize);
            answered = false;
        } else {
            addResult();
            finishQuestion();
        }
    }

    private void finishQuestion() {
        Intent intent = new Intent(MainActivity.this, Home.class);
        intent.putExtra("score", score);
        setResult(RESULT_OK, intent);
        startActivity(intent);
        finish();
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rdSelect = findViewById(radioGroup.getCheckedRadioButtonId());

        int answer = radioGroup.indexOfChild(rdSelect) + 1;

        if (answer == questionCurient.getAnswer()) {
            score = score + 10;
            textScore.setText("Điểm " + score);
            rightSentence = rightSentence + 1;
            textRight.setText("Số câu đúng " + rightSentence + " / " + questionSize);
        }
        showSolution();
    }

    private void showSolution() {
        radioAnswer1.setBackground(getDrawable(R.drawable.linner_orgrane));
        radioAnswer2.setBackground(getDrawable(R.drawable.linner_orgrane));
        radioAnswer3.setBackground(getDrawable(R.drawable.linner_orgrane));
        radioAnswer4.setBackground(getDrawable(R.drawable.linner_orgrane));

        switch (questionCurient.getAnswer()) {
            case 1:
                radioAnswer1.setBackground(getDrawable(R.drawable.linner_palegreen));
                break;
            case 2:
                radioAnswer2.setBackground(getDrawable(R.drawable.linner_palegreen));
                break;
            case 3:
                radioAnswer3.setBackground(getDrawable(R.drawable.linner_palegreen));
                break;
            case 4:
                radioAnswer4.setBackground(getDrawable(R.drawable.linner_palegreen));
                break;
        }
        if (questionCount < questionSize) {
            buttonStart.setText("Next");
        } else {
            buttonStart.setText("Hoàn thành");
        }
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count >= 1) {
            finishQuestion();
        }
        count += 0;
    }

    public void addResult(){
        Result result = new Result(score,textTime.getText().toString());
        Database database = new Database(this);
        database.insertResult(result);

    }

    public void addEvent() {
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if (radioAnswer1.isChecked() || radioAnswer2.isChecked() || radioAnswer3.isChecked() || radioAnswer4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(MainActivity.this, "Hãy chọn đáp án", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    public void addView() {
        buttonStart = findViewById(R.id.btnStart);

        textTitle = findViewById(R.id.tvQuestion);
        textContent = findViewById(R.id.tvContent);
        textScore = findViewById(R.id.tvScore);
        textRight = findViewById(R.id.tvRight);
        textTime = findViewById(R.id.tvTime);

        radioGroup = findViewById(R.id.rdGroup);

        radioAnswer1 = findViewById(R.id.rdAnswer1);
        radioAnswer2 = findViewById(R.id.rdAnswer2);
        radioAnswer3 = findViewById(R.id.rdAnswer3);
        radioAnswer4 = findViewById(R.id.rdAnswer4);

    }
}