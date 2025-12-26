package com.wemade.kmp.rocket.repository

import com.wemade.kmp.rocket.RocketRepository
import com.wemade.kmp.rocket.mapToDetailData
import com.wemade.kmp.rocket.model.DetailData
import com.wemade.kmp.rocket.model.ListData
import com.wemade.kmp.rocket.toDetailData
import com.wemade.kmp.rocket.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RocketRepositoryImpl: RocketRepository {

    private val rocketComponent = RocketComponent()

    override fun getLaunchList(): Flow<List<ListData>> {
        return flow {
            val data = rocketComponent.getLaunchList()
            val result = data.map {
                it.toDomain()
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override fun getRocketLaunchDetail(launchId: String): Flow<DetailData> {
        return flow {
            val launchData = rocketComponent.getLaunchDetail(launchId)
                ?: throw Exception("Launch info not found for id: $launchId")

            val rocketId = launchData.rocketId

            val rocketData = rocketComponent.getRocketDetail(rocketId)
                ?: throw Exception("Rocket info not found for id: $rocketId")

            val detailData = mapToDetailData(launchData, rocketData)
            emit(detailData)

        }.flowOn(Dispatchers.IO)
    }
}