package com.example.stochia.domain.model.montecarlo

enum class MontecarloType(val key: String) {
    NORMAL("normal"),
    UNIFORM("uniform"),
    EXPONENTIAL("exponential"),
    BETA("beta"),
    BINOMIAL("binomial"),
    GEOMETRICAL("geometrical"),
    POISSON("poisson"),
    MULTINOMIAL("multinomial");
}