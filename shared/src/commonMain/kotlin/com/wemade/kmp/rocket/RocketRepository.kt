package com.wemade.kmp.rocket

import com.wemade.kmp.rocket.model.DetailData
import com.wemade.kmp.rocket.model.ListData
import kotlinx.coroutines.flow.Flow

interface RocketRepository {

    fun getLaunchList(): Flow<List<ListData>>

    fun getRocketLaunchDetail(rocketId: String): Flow<DetailData>

}