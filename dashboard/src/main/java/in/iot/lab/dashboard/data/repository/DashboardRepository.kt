package `in`.iot.lab.dashboard.data.repository

import `in`.iot.lab.network.data.models.team.RemoteTeam
import `in`.iot.lab.network.state.ResponseState

interface DashboardRepository {
    suspend fun getCurrentUserTeam(): ResponseState<RemoteTeam>
}