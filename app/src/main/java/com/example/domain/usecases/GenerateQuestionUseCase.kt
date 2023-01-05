package com.example.domain.usecases

class GenerateQuestionUseCase(private val repository: Repository) {
    fun execute(maxSumValue: Int, type: Type): Question =
        repository.generateQuestion(maxSumValue, Utils.COUNT_OF_OPTIONS, type)
}