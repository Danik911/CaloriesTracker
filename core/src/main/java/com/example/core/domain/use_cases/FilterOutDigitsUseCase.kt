package com.example.core.domain.use_cases

class FilterOutDigitsUseCase{

    operator fun invoke(text: String) = text.filter { it.isDigit() }
}