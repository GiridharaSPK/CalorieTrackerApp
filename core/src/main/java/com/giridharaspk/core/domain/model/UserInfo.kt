package com.giridharaspk.core.domain.model

data class UserInfo(
    val gender: Gender,
    val age: Int,
    val height: Int,
    val weight: Float,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val fatRatio: Float,
    val carbRatio: Float,
    val proteinRatio: Float,
)
