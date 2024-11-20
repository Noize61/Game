package com.example.game;

/*
Author - Kuklin.A
Studying - RKRIPT
Subject - Capitals & Countries game
*/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

/*
Переменная для хранения выбранной темы
*/
    private String selectedTopic = "";

/*
Метод onCreate
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

/*
Связываем объекты с id
*/
        final LinearLayout strani = findViewById(R.id.straniLayout);
        final LinearLayout stolici = findViewById(R.id.stolicaLayout);
        final Button startButton = findViewById(R.id.startButton);

/*
Обработчик клика для "Страны"
 */
        strani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedTopic = "Страны";
                strani.setBackgroundResource(R.drawable.izmen_strani_stolici);

                stolici.setBackgroundResource(R.drawable.fon);

            }
        });

/*
Обработчик клика для "Столицы"
 */
        stolici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedTopic = "Столицы";
                stolici.setBackgroundResource(R.drawable.izmen_strani_stolici);

                strani.setBackgroundResource(R.drawable.fon);
            }
        });

/*
Обработчик клика для "Выберите викторину"
 */
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTopic.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Выберите викторину", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("selectedTopic", selectedTopic);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}