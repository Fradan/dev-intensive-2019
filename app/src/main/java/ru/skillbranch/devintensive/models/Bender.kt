package ru.skillbranch.devintensive.models

import android.util.Log

/**
 * Created by 580305 on 06.09.2019.
 */

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    var errorAnswerCounter = 0
    fun askQuestion() : String = when (question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION-> Question.PROFESSION.question
        Question.MATERIAL-> Question.MATERIAL.question
        Question.BDAY-> Question.BDAY.question
        Question.SERIAL-> Question.SERIAL.question
        Question.IDLE-> Question.IDLE.question
    }

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>> {

        return if (question.answer.contains(answer.toLowerCase())) {
            val validationText = question.validateAnswer(answer)
            if (validationText !== "") {
                return "$validationText\n${question.question}" to status.color
            }

            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            errorAnswerCounter += 1
            if (errorAnswerCounter > 3) {
                status = Status.NORMAL
                question = Question.NAME
                errorAnswerCounter = 0
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
                "Это неправильный ответ!\n${question.question}" to status.color
            }
        }
    }

    enum class Status(var color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;

        fun nextStatus() : Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(var question: String, val answer: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun validateAnswer(answer: String): String {
                if (answer.firstOrNull()?.isUpperCase() == true) {
                    return ""
                }
                return "Имя должно начинаться с заглавной буквы"
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun validateAnswer(answer: String): String {
                if (answer.firstOrNull()?.isUpperCase() == false) {
                    return ""
                }
                return "Профессия должна начинаться со строчной буквы"
            }

            override fun nextQuestion(): Question = MATERIAL

        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun validateAnswer(answer: String): String {
                return if (answer.contains(Regex("\\d", RegexOption.IGNORE_CASE))) {
                    "Материал не должен содержать цифр"
                } else ""
            }

            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun validateAnswer(answer: String): String {
                return if (!answer.contains(Regex("^\\d+$"))) {
                    "Год моего рождения должен содержать только цифры"
                } else ""
            }

            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun validateAnswer(answer: String): String {
                return if (answer.length != 7 || !answer.contains(Regex("^\\d+$"))) {
                    "Серийный номер содержит только цифры, и их 7"
                } else {
                    ""
                }
            }

            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun validateAnswer(answer: String) = ""
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion() : Question
        abstract fun validateAnswer(answer: String) : String
    }
}