package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizResults extends AppCompatActivity {

/*
Метод onCreate
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

/*
Связываем объекты с id
*/
        final AppCompatButton novaiaVictorina = findViewById(R.id.novaiaVictorina);
        final TextView correctAnswears = findViewById(R.id.correctAnswears);
        final TextView inCorrectAnswears = findViewById(R.id.inCorrectAnswears);

        final int getCorrectAnswears = getIntent().getIntExtra("correct", 0);
        final int getInCorrectAnswears = getIntent().getIntExtra("incorrect", 0);

/*
Отображение правильных и неправильных ответов
*/
        correctAnswears.setText(String.valueOf("Количество правильных ответов - " + getCorrectAnswears));
        inCorrectAnswears.setText(String.valueOf("Количество неправильных ответов - " + getInCorrectAnswears));

/*
Обработка нажатия кнопки "Новая викторина"
*/
        novaiaVictorina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResults.this, MainActivity.class));
                finish();
            }
        });
    }

/*
Обработка нажатия кнопки "Назад"
*/
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        startActivity(new Intent(QuizResults.this, MainActivity.class));
        finish();

    }

}