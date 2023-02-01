package com.example.core.domain.use_cases

class FilterOutDigits{

    operator fun invoke(text: String) = text.filter { it.isDigit() }
}