package com.giridharaspk.core.domain.preferences

import com.giridharaspk.core.domain.model.ActivityLevel
import com.giridharaspk.core.domain.model.Gender
import com.giridharaspk.core.domain.model.GoalType
import com.giridharaspk.core.domain.model.UserInfo

interface Preferences {
    fun saveGender(gender: Gender)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Int)
    fun saveAge(age : Int)
    fun saveActivityLevel(activity : ActivityLevel)
    fun saveGoalType(goal : GoalType)
    fun saveCarbRatio(carbRatio : Float)
    fun saveProteinRatio(proteinRatio : Float)
    fun saveFatRatio(fatRatio : Float)

    fun loadUserInfo() : UserInfo

    companion object {
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_WEIGHT = "weight"
        const val KEY_HEIGHT = "height"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_GOAL_TYPE = "goal_type"
        const val KEY_FAT_RATIO = "fat_ratio"
        const val KEY_CARB_RATIO = "carb_ratio"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
    }

}