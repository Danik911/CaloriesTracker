package com.example.core.domain.model

enum class Gender() {
    MALE,
    FEMALE,
}

fun String.getGenderFromString(): Gender {
    return when (this) {
        "male" -> Gender.MALE
        "female" -> Gender.FEMALE
        else -> Gender.MALE
    }
}