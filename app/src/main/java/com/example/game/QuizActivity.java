package com.example.game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/*
Объявление класса и переменных
*/
public class QuizActivity extends AppCompatActivity {

    private TextView vopros;
    private TextView question;

    private AppCompatButton otvet1, otvet2, otvet3, otvet4;
    private AppCompatButton nextButton;

    private Timer vremia;

    private int seconds = 0;
    private int minuts = 1;

    private List<QuestionList> questionList;

    private int currentQuestionPosition = 0;
    private String selectedOptionByUser = "";

/*
Метод onCreate
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

/*
Связываем объекты с id
*/
        final ImageView strelkaNazad = findViewById(R.id.strelkaNazad);
        final TextView timer = findViewById(R.id.timer);
        final TextView topicName = findViewById(R.id.topicName);

        vopros = findViewById(R.id.vopros);
        question = findViewById(R.id.question);

        otvet1 = findViewById(R.id.otvet1);
        otvet2 = findViewById(R.id.otvet2);
        otvet3 = findViewById(R.id.otvet3);
        otvet4 = findViewById(R.id.otvet4);

        nextButton = findViewById(R.id.nextButton);

/*
Инициализация вопросов и таймера
*/
        final String selectedTopic = getIntent().getStringExtra("selectedTopic");

        topicName.setText(selectedTopic);

        questionList = voprosBank.getQuestions(selectedTopic);

        startTimer(timer);

/*
Настройка первого вопроса
*/
        vopros.setText((currentQuestionPosition + 1) + "/" + questionList.size());
        question.setText(questionList.get(0).getQuestion());
        otvet1.setText(questionList.get(0).getOtvet1());
        otvet2.setText(questionList.get(0).getOtvet2());
        otvet3.setText(questionList.get(0).getOtvet3());
        otvet4.setText(questionList.get(0).getOtvet4());

/*
Обработчик кнопки "Назад"
*/
        strelkaNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vremia.purge();
                vremia.cancel();

                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });

/*
Обработчик нажатия на кнопку "Ответ1"
*/
        otvet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedOptionByUser.isEmpty()) {

                    selectedOptionByUser = otvet1.getText().toString();
                    otvet1.setBackgroundResource(R.drawable.red_otvet);
                    otvet1.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

/*
Обработчик нажатия на кнопку "Ответ2"
*/
        otvet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedOptionByUser.isEmpty()) {

                    selectedOptionByUser = otvet2.getText().toString();
                    otvet2.setBackgroundResource(R.drawable.red_otvet);
                    otvet2.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

/*
Обработчик нажатия на кнопку "Ответ3"
*/
        otvet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedOptionByUser.isEmpty()) {

                    selectedOptionByUser = otvet3.getText().toString();
                    otvet3.setBackgroundResource(R.drawable.red_otvet);
                    otvet3.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

/*
Обработчик нажатия на кнопку "Ответ4"
*/
        otvet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedOptionByUser.isEmpty()) {

                    selectedOptionByUser = otvet4.getText().toString();
                    otvet4.setBackgroundResource(R.drawable.red_otvet);
                    otvet4.setTextColor(Color.WHITE);

                    revealAnswer();
                    questionList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

/*
Обработчик кнопки "Далее"
*/
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedOptionByUser.isEmpty()) {

                    Toast.makeText(QuizActivity.this, "Сделайте выбор.", Toast.LENGTH_SHORT).show();

                } else {
                    changeNextQuestion();
                }

            }
        });

    }

/*
Метод для таймера
*/
    private void startTimer (TextView timerTextView) {
        vremia = new Timer();
        vremia.schedule(new TimerTask() {
            @Override
            public void run() {
                if (seconds == 0) {
                    minuts--;
                    seconds = 59;
                } else if (seconds == 0 && minuts == 0) {
                    vremia.purge();
                    vremia.cancel();

                    Toast.makeText(QuizActivity.this, "Время вышло.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuizActivity.this, QuizResults.class);
                    intent.putExtra("correct", getCorrectAnswers());
                    intent.putExtra("incorrect", getInCorrectAnswers());

                    startActivity(intent);
                    finish();
                }
                else {
                    seconds --;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String finalMinuts = String.valueOf(minuts);
                        String finalSeconds = String.valueOf(seconds);

                        if (finalMinuts.length() ==1) {
                            finalMinuts = "0" + finalMinuts;
                        }
                        if (finalSeconds.length() == 1){
                            finalSeconds = "0" + finalSeconds;
                        }
                        timerTextView.setText(finalMinuts + ":" + finalSeconds);

                    }
                });

            }
        },1000,1000);
    }

/*
Метод для вопросов, считает сколько правильных
*/
    private int getCorrectAnswers () {

        int correctAnswers = 0;

        for (int i = 0; i < questionList.size(); i++) {

            final String getUserSelectedAnswer = questionList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionList.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

/*
Метод для вопросов, считает сколько неправильных
*/
    private int getInCorrectAnswers () {

        int correctAnswers = 0;

        for (int i = 0; i < questionList.size(); i++) {

            final String getUserSelectedAnswer = questionList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionList.get(i).getAnswer();

            if (!getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

/*
Метод отменяет таймер и возвращает пользователя на главный экран
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        vremia.purge();
        vremia.cancel();

        startActivity(new Intent(QuizActivity.this, MainActivity.class));
        finish();
    }

/*
Этот метод показывает правильный ответ, меняя цвет фона
 */
    private void revealAnswer () {
        final String getAnswer = questionList.get(currentQuestionPosition).getAnswer();

        if (otvet1.getText().toString().equals(getAnswer)) {

            otvet1.setBackgroundResource(R.drawable.green_otvet);
            otvet1.setTextColor(Color.WHITE);

        }
        else if (otvet2.getText().toString().equals(getAnswer)) {

            otvet2.setBackgroundResource(R.drawable.green_otvet);
            otvet2.setTextColor(Color.WHITE);
        }
        else if (otvet3.getText().toString().equals(getAnswer)) {

            otvet3.setBackgroundResource(R.drawable.green_otvet);
            otvet3.setTextColor(Color.WHITE);
        }
        else if (otvet4.getText().toString().equals(getAnswer)) {

            otvet4.setBackgroundResource(R.drawable.green_otvet);
            otvet4.setTextColor(Color.WHITE);
        }
    }

    private void changeNextQuestion () {
        currentQuestionPosition++;

        if ((currentQuestionPosition+1) == questionList.size()) {
            nextButton.setText("Готово");
        }

        if (currentQuestionPosition < questionList.size()) {
            selectedOptionByUser = "";

            otvet1.setBackgroundResource(R.drawable.icon);
            otvet1.setTextColor(Color.parseColor("#1F6BB8"));

            otvet2.setBackgroundResource(R.drawable.icon);
            otvet2.setTextColor(Color.parseColor("#1F6BB8"));

            otvet3.setBackgroundResource(R.drawable.icon);
            otvet3.setTextColor(Color.parseColor("#1F6BB8"));

            otvet4.setBackgroundResource(R.drawable.icon);
            otvet4.setTextColor(Color.parseColor("#1F6BB8"));

/*
Показывает позицию вопроса
*/
            vopros.setText((currentQuestionPosition + 1) + "/" + questionList.size());
            question.setText(questionList.get(currentQuestionPosition).getQuestion());
            otvet1.setText(questionList.get(currentQuestionPosition).getOtvet1());
            otvet1.setText(questionList.get(currentQuestionPosition).getOtvet1());
            otvet2.setText(questionList.get(currentQuestionPosition).getOtvet2());
            otvet3.setText(questionList.get(currentQuestionPosition).getOtvet3());
            otvet4.setText(questionList.get(currentQuestionPosition).getOtvet4());
        }
        else {
            Intent intent = new Intent(QuizActivity.this, QuizResults.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getInCorrectAnswers());

            startActivity(intent);
            finish();
        }
    }

}