package com.example.game;

public class QuestionList {

    private String otvet1, otvet2, otvet3, otvet4, question, answer;
    private String userSelectedAnswer;

    public QuestionList(String question, String otvet1, String otvet2, String otvet3, String otvet4, String answer, String userSelectedAnswer) {
        this.otvet2 = otvet2;
        this.otvet1 = otvet1;
        this.otvet3 = otvet3;
        this.otvet4 = otvet4;
        this.question = question;
        this.answer = answer;
        this.userSelectedAnswer = userSelectedAnswer;
    }

    public String getOtvet1() {
        return otvet1;
    }

    public String getOtvet2() {
        return otvet2;
    }

    public String getOtvet3() {
        return otvet3;
    }

    public String getOtvet4() {
        return otvet4;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public void setUserSelectedAnswer(String userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }
}
