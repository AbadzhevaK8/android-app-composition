package com.abadzheva.composition.data

import com.abadzheva.composition.domain.entity.GameSettings
import com.abadzheva.composition.domain.entity.Level
import com.abadzheva.composition.domain.entity.Question
import com.abadzheva.composition.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min

object GameRepositoryImpl : GameRepository {
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int,
    ): Question {
        val sum = (MIN_SUM_VALUE..maxSumValue).random() // generate random sum
        val visibleNumber = (MIN_ANSWER_VALUE..sum).random() // generate random visible number
        val options = HashSet<Int>() // generate random options
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add((from..to).random())
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings =
        when (level) {
            Level.TEST ->
                GameSettings(
                    10,
                    3,
                    50,
                    8,
                )
            Level.EASY ->
                GameSettings(
                    10,
                    10,
                    70,
                    60,
                )
            Level.NORMAL ->
                GameSettings(
                    20,
                    20,
                    80,
                    40,
                )
            Level.HARD ->
                GameSettings(
                    30,
                    30,
                    90,
                    40,
                )
        }
}
