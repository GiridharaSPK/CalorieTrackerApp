package com.giridharaspk.tracker_data.repository

import com.giridharaspk.tracker_data.local.dao.TrackerDao
import com.giridharaspk.tracker_data.mapper.toTrackableFood
import com.giridharaspk.tracker_data.mapper.toTrackedFood
import com.giridharaspk.tracker_data.mapper.toTrackedFoodEntity
import com.giridharaspk.tracker_data.remote.OpenFoodApi
import com.giridharaspk.tracker_domain.model.TrackableFood
import com.giridharaspk.tracker_domain.model.TrackedFood
import com.giridharaspk.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi,
) : TrackerRepository {
    override suspend fun searchFood(query: String, page: Int, pageSize: Int): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(query, page, pageSize)
            Result.success(
                searchDto.products.mapNotNull {
                    it.toTrackableFood()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackerFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            localDate.dayOfMonth,
            localDate.monthValue,
            localDate.year
        ).map { list ->
            list.map { it.toTrackedFood() }
        }
    }
}