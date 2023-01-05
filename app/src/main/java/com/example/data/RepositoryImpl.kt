package com.example.data

import com.example.domain.models.GameSettings
import com.example.domain.models.Level
import com.example.domain.usecases.Question
import com.example.domain.usecases.Repository
import com.example.domain.usecases.Type
import com.example.domain.usecases.Utils
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class RepositoryImpl : Repository {

    private var sum = 1
    private var visibleNum = 1


    override fun generateQuestion(maxSumValue: Int, countOfOption: Int, type: Type): Question {
        sum = Random.nextInt(Utils.MIN_COUNT_VALUE, maxSumValue + 1)
        val rightAnswer = when (type) {
            Type.PLUS -> generatePlusCorrectAnswer()
            Type.MINUS -> generateMinusCorrectAnswer(maxSumValue)
            Type.MULTIPLY -> generateMultiplyCorrectAnswer()
            Type.DELETE -> generateDeleteCorrectAnswer(maxSumValue)
        }

        val from = max(rightAnswer - countOfOption, Utils.MIN_SUM_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOption)
        val options = HashSet<Int>()
        options.add(rightAnswer)
        while (options.size < countOfOption) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNum, options.toList(), rightAnswer)
    }


    private fun generatePlusCorrectAnswer(): Int {
        visibleNum = Random.nextInt(Utils.MIN_SUM_VALUE_PLUS, sum)
        return sum - visibleNum

    }

    private fun generateMinusCorrectAnswer(maxSumValue: Int): Int {
        visibleNum = Random.nextInt(sum, maxSumValue + 1)
        return (visibleNum - sum)


    }

    private fun generateDeleteCorrectAnswer(maxSumValue: Int): Int {
        visibleNum = Random.nextInt(Utils.MIN_SUM_VALUE_MINUS, maxSumValue)
        return sum / visibleNum


    }

    private fun generateMultiplyCorrectAnswer(): Int {
        visibleNum = Random.nextInt(Utils.MIN_SUM_VALUE_PLUS, sum)
        if (sum / visibleNum.toDouble() % 1.0 != 0.0) {
            visibleNum = Random.nextInt(Utils.MIN_SUM_VALUE_PLUS, sum)
        } 
            return sum / visibleNum

    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> GameSettings(
                Utils.MAX_SUM_VALUE_TEST_LEVEL,
                Utils.MIN_COUNT_OF_RIGHT_ANSWER_TEST_LEVEL,
                Utils.MIN_PERCENT_OF_RIGHT_ANSWER_TEST_LEVEL,
                Utils.GAME_TIME_IN_SECOND_TEST_LEVEL
            )
            Level.EASY -> GameSettings(
                Utils.MAX_SUM_VALUE_EASY_LEVEL,
                Utils.MIN_COUNT_OF_RIGHT_ANSWER_EASY_LEVEL,
                Utils.MIN_PERCENT_OF_RIGHT_ANSWER_EASY_LEVEL,
                Utils.GAME_TIME_IN_SECOND_EASY_LEVEL
            )
            Level.STANDARD -> GameSettings(
                Utils.MAX_SUM_VALUE_MEDIUM_LEVEL,
                Utils.MIN_COUNT_OF_RIGHT_ANSWER_MEDIUM_LEVEL,
                Utils.MIN_PERCENT_OF_RIGHT_ANSWER_MEDIUM_LEVEL,
                Utils.GAME_TIME_IN_SECOND_MEDIUM_LEVEL

            )
            Level.HARD -> GameSettings(
                Utils.MAX_SUM_VALUE_HARD_LEVEL,
                Utils.MIN_COUNT_OF_RIGHT_ANSWER_HARD_LEVEL,
                Utils.MIN_PERCENT_OF_RIGHT_ANSWER_HARD_LEVEL,
                Utils.GAME_TIME_IN_SECOND_HARD_LEVEL
            )
        }
    }

}