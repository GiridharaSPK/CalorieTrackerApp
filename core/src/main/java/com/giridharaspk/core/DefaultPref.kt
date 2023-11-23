package com.giridharaspk.core

import android.content.SharedPreferences
import com.giridharaspk.core.domain.model.ActivityLevel
import com.giridharaspk.core.domain.model.Gender
import com.giridharaspk.core.domain.model.GoalType
import com.giridharaspk.core.domain.model.UserInfo
import com.giridharaspk.core.domain.preferences.Preferences
import com.giridharaspk.core.domain.preferences.Preferences.Companion.KEY_ACTIVITY_LEVEL
import com.giridharaspk.core.domain.preferences.Preferences.Companion.KEY_AGE
import com.giridharaspk.core.domain.preferences.Preferences.Companion.KEY_CARB_RATIO
import com.giridharaspk.core.domain.preferences.Preferences.Companion.KEY_FAT_RATIO
import com.giridharaspk.core.domain.preferences.Preferences.Companion.KEY_GENDER
import com.giridharaspk.core.domain.preferences.Preferences.Companion.KEY_GOAL_TYPE
import com.giridharaspk.core.domain.preferences.Preferences.Companion.KEY_HEIGHT
import com.giridharaspk.core.domain.preferences.Preferences.Companion.KEY_PROTEIN_RATIO
import com.giridharaspk.core.domain.preferences.Preferences.Companion.KEY_WEIGHT
import javax.inject.Inject

class DefaultPref @Inject constructor(private val sharedPref: SharedPreferences) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPref.edit().putString(KEY_GENDER, gender.name).apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit().putFloat(KEY_WEIGHT, weight).apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit().putInt(KEY_HEIGHT, height).apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit().putInt(KEY_AGE, age).apply()
    }

    override fun saveActivityLevel(activity: ActivityLevel) {
        sharedPref.edit().putString(KEY_ACTIVITY_LEVEL, activity.name).apply()
    }

    override fun saveGoalType(goal: GoalType) {
        sharedPref.edit().putString(KEY_GOAL_TYPE, goal.name).apply()
    }

    override fun saveCarbRatio(carbRatio: Float) {
        sharedPref.edit().putFloat(KEY_CARB_RATIO, carbRatio).apply()
    }

    override fun saveProteinRatio(proteinRatio: Float) {
        sharedPref.edit().putFloat(KEY_PROTEIN_RATIO, proteinRatio).apply()
    }

    override fun saveFatRatio(fatRatio: Float) {
        sharedPref.edit().putFloat(KEY_FAT_RATIO, fatRatio).apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPref.getInt(KEY_AGE, -1)
        val gender = sharedPref.getString(KEY_GENDER, "")
        val height = sharedPref.getInt(KEY_HEIGHT, -1)
        val weight = sharedPref.getFloat(KEY_WEIGHT, -1f)
        val activityLevel = sharedPref.getString(KEY_ACTIVITY_LEVEL, null)
        val goalType = sharedPref.getString(KEY_GOAL_TYPE, null)
        val carbRatio = sharedPref.getFloat(KEY_CARB_RATIO, -1f)
        val proteinRatio = sharedPref.getFloat(KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharedPref.getFloat(KEY_FAT_RATIO, -1f)

        return UserInfo(
            gender = Gender.fromString(gender ?: "male"),
            age = age,
            height = height,
            weight = weight,
            activityLevel = ActivityLevel.fromString(activityLevel ?: ""),
            goalType = GoalType.fromString(goalType ?: ""),
            carbRatio = carbRatio,
            fatRatio = fatRatio,
            proteinRatio = proteinRatio,
        )
    }

}
