package com.example.quizapp.Models

data class Question(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctAnswer: String,
    val Image:Int
)