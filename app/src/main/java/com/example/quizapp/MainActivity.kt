package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.Models.Question
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var currentQuestion: Question
    var currentQuestionIndex = 0
    var score = 0
    var questions :ArrayList<Question> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.counter.text = "Quiz : ${currentQuestionIndex +1}/5"
         questions = arrayListOf(
             Question(
                 "Q.1 What is the capital of France?",
                 "Delhi",
                 "Berlin",
                 "Paris",
                 "Rome",
                 "Paris",
                 R.drawable.france
             ),
             Question(
                 "Q.2 What is the tallest mountain in the world?",
                 "Mount Everest",
                 "K2",
                 "Kangchenjunga",
                 "Lhotse",
                 "Mount Everest"
                 ,R.drawable.mountain
             ),
             Question(
                 "Q.3 Which is the largest ocean on Earth?",
                 "Pacific Ocean",
                 "Atlantic Ocean",
                 "Indian Ocean",
                 "Arctic Ocean",
                 "Pacific Ocean",
                         R.drawable.ocean
             ),
             Question(
                 "Q.4 What is the currency of Japan?",
                 "Euro",
                 "Dollar",
                 "Yen",
                 "Yuan",
                 "Yen",
                 R.drawable.japan
             ),
             Question(
                 "Q.5 In which year did World War II begin?",
                 "1939",
                 "1941",
                 "1945",
                 "1914",
                 "1939",
                 R.drawable.worldwar
             )

        )
        currentQuestion = questions[currentQuestionIndex]
        updateView()
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeanimation)

        binding.question.startAnimation(fadeInAnimation)
        binding.option1.startAnimation(fadeInAnimation)
        binding.option2.startAnimation(fadeInAnimation)
        binding.option3.startAnimation(fadeInAnimation)
        binding.option4.startAnimation(fadeInAnimation)

        binding.option1.setOnClickListener { checkAnswer(binding.option1.text.toString()) }
        binding.option2.setOnClickListener { checkAnswer(binding.option2.text.toString()) }
        binding.option3.setOnClickListener { checkAnswer(binding.option3.text.toString()) }
        binding.option4.setOnClickListener { checkAnswer(binding.option4.text.toString()) }

        binding.next.setOnClickListener {
            moveToNextQuestion()
        }
    }

    private fun updateView() {
        binding.question.text = currentQuestion.question
        binding.option1.text = currentQuestion.option1
        binding.option2.text = currentQuestion.option2
        binding.option3.text = currentQuestion.option3
        binding.option4.text = currentQuestion.option4
        binding.images.setImageResource(currentQuestion.Image)

        binding.preview.setOnClickListener {
            PreviewQuestion()
        }
    }

    private fun checkAnswer(selectedAnswer: String) {
        if (selectedAnswer == currentQuestion.correctAnswer) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect. The correct answer is ${currentQuestion.correctAnswer}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun moveToNextQuestion() {
        currentQuestionIndex++

        if (currentQuestionIndex < questions.size) {
            binding.counter.text = "Quiz : ${currentQuestionIndex+1}/5"
            currentQuestion = questions[currentQuestionIndex]
            updateView()
        } else {
           var intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("SCORE","$score")
            startActivity(intent)
        }
    }

    private fun PreviewQuestion(){
        currentQuestionIndex--
        if (currentQuestionIndex >=0) {
            binding.counter.text = "Quiz : ${currentQuestionIndex+1}/5"
            currentQuestion = questions[currentQuestionIndex]
            updateView()
        } else {
            Toast.makeText(this, "Cannot go back ", Toast.LENGTH_SHORT).show()
        }
    }
}

