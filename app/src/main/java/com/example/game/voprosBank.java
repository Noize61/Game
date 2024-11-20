package com.example.game;

import java.util.ArrayList;
import java.util.List;

public class voprosBank {

    private static List<QuestionList> СтраныQuestions () {

        final List<QuestionList> questionLists = new ArrayList<>();
        final QuestionList vopros1 = new QuestionList("Какая страна у столицы Берлин?",
                "Франция", "Германия", "Россия", "Нидерланды",
                "Германия", "");

        final QuestionList vopros2 = new QuestionList("Какая страна у столицы Оттава?",
                "Канада", "Турция", "Швейцария", "Япония",
                "Канада", "");

        final QuestionList vopros3 = new QuestionList("Какая страна у столицы Бразилиа?",
                "Казахстан", "Греция", "Бразилия", "Аргентина",
                "Бразилия", "");

        final QuestionList vopros4 = new QuestionList("Какая страна у столицы Нью-Дели??",
                "Италия", "Испания", "Турция", "Индия",
                "Индия", "");

        final QuestionList vopros5 = new QuestionList("Какая страна у столицы Мехико?",
                "Мексика", "Китай", "Австрия", "Финляндия",
                "Мексика", "");

        questionLists.add(vopros1);
        questionLists.add(vopros2);
        questionLists.add(vopros3);
        questionLists.add(vopros4);
        questionLists.add(vopros5);

        return questionLists;
    }

    private static List<QuestionList> СтолицыQuestions () {

        final List<QuestionList> questionLists = new ArrayList<>();
        final QuestionList vopros1 = new QuestionList("Какая столица у России?",
                "Воронеж", "Москва", "Псков", "Нижний Новгород",
                "Москва", "");

        final QuestionList vopros2 = new QuestionList("Какая столица у Турции?",
                "Стамбул", "Анталья", "Анкара", "Адана",
                "Анкара", "");

        final QuestionList vopros3 = new QuestionList("Какая столица у Китая?",
                "Пекин", "Ганьчжоу", "Гонконг", "Гуйлинь",
                "Пекин", "");

        final QuestionList vopros4 = new QuestionList("Какая столица у Франции?",
                "Марсель", "Тулуза", "Лион", "Париж",
                "Париж", "");

        final QuestionList vopros5 = new QuestionList("Какая столица у Италии?",
                "Венеция", "Рим", "Милан", "Флоренция",
                "Рим", "");

        questionLists.add(vopros1);
        questionLists.add(vopros2);
        questionLists.add(vopros3);
        questionLists.add(vopros4);
        questionLists.add(vopros5);

        return questionLists;
    }

    public static List<QuestionList> getQuestions (String selectedTopic){
        switch (selectedTopic){
            case "Страны" : return СтраныQuestions();
            default: return СтолицыQuestions();
        }
    }

}
