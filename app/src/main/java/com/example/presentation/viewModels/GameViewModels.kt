package com.example.presentation.viewModels

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.RepositoryImpl
import com.example.domain.models.GameResult
import com.example.domain.models.GameSettings
import com.example.domain.models.Level
import com.example.domain.usecases.GenerateQuestionUseCase
import com.example.domain.usecases.GetGameUseCase
import com.example.domain.usecases.Question
import com.example.domain.usecases.Type

class GameViewModels : ViewModel() {
    private val repository = RepositoryImpl()
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameUseCase(repository)

    private lateinit var _gameSettings: GameSettings
    val gameSettings: GameSettings get() = _gameSettings

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> get() = _question

    private var _percentOfCorrectAnswer: MutableLiveData<Int> = MutableLiveData()
    val percentOfCorrectAnswer: LiveData<Int> get() = _percentOfCorrectAnswer

    private var _enoughPercentOfRightAnswer: MutableLiveData<Boolean> = MutableLiveData()
    val enoughPercentOfRightAnswer: LiveData<Boolean> get() = _enoughPercentOfRightAnswer


    private var timer: CountDownTimer? = null

    private var _formattedTime: MutableLiveData<String> = MutableLiveData()
    val formattedTime: LiveData<String> get() = _formattedTime

    private val _enoughCountOfRightAnswer: MutableLiveData<Boolean> = MutableLiveData()
    val enoughCountOfRightAnswer: LiveData<Boolean> get() = _enoughCountOfRightAnswer

    private val _gameResult: MutableLiveData<GameResult> = MutableLiveData()
    val gameResult: LiveData<GameResult> = _gameResult


    private var minPercentOfCorrectAnswer: Double = 0.0
    private var _correctAnswer: Int = 0
    val correctAnswers: Int get() = _correctAnswer
    private var _answer = 0
    val answer: Int get() = _answer
    private lateinit var type: Type

    fun getGameSettings(level: Level, type: Type) {
        this.type = type
        _gameSettings = getGameSettingsUseCase.execute(level)
        minPercentOfCorrectAnswer = _gameSettings.minPercentOfRightAnswer
        generateQuestion()
        startTimer()
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase.execute(_gameSettings.maxSumValue, type)
    }


    fun makeChoose(answer: Int) {
        _answer++
        if (_question.value?.correctAnswer == answer) {
            _correctAnswer++
        }
        countPrecentage()
        generateQuestion()
    }

    private fun countPrecentage() {
        val persintage = (100 / _answer.toDouble()) * _correctAnswer
        _percentOfCorrectAnswer.value = persintage.toInt()
        updateProgress(persintage)
    }

    private fun updateProgress(persentege: Double) {
        _enoughCountOfRightAnswer.value = _correctAnswer >= _gameSettings.minCountOfRightAnswer
        _enoughPercentOfRightAnswer.value = persentege >= _gameSettings.minPercentOfRightAnswer
    }


    private fun startTimer() {
        timer = object : CountDownTimer(
            _gameSettings.gameTimeInSecond * MILLIS_IN_SECOND, MILLIS_IN_SECOND
        ) {
            override fun onTick(millis: Long) {
                _formattedTime.value = formatTime(millis)
            }
            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()

    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            isWinner = _enoughCountOfRightAnswer.value == true &&
                    _enoughPercentOfRightAnswer.value == true,
            countOfRightAnswer = _correctAnswer,
            percentOfRightAnswer = percentOfCorrectAnswer.value!!,
            countOfQuestion = _answer,
            gameSettings = _gameSettings
        )
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    private fun formatTime(millis: Long): String {
        val seconds = millis / MILLIS_IN_SECOND
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSecond = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSecond)
    }


    companion object {
        const val MILLIS_IN_SECOND = 1000L
        const val SECONDS_IN_MINUTES = 60
    }
}