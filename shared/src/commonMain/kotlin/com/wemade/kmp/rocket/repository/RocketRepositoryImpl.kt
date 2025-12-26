package com.wemade.kmp.rocket.repository

import com.wemade.kmp.rocket.RocketRepository
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

    override fun getLaunchDetail(launchId: String): Flow<ListData> {
        TODO("Not yet implemented - Russell")
    }

    override fun getRocketDetail(rocketId: String): Flow<DetailData> {
        return flow {
            val data = rocketComponent.getRocketDetail(rocketId)

            if (data != null) {
                val result = data.toDetailData()
                emit(result)
            } else {
                throw Exception("Rocket not found")
            }
        }.flowOn(Dispatchers.IO)
    }
}