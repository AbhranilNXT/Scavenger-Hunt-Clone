package `in`.iot.lab.network.utils

object NetworkConstants {

    // Base Url
//    const val BASE_URL = "http://14.139.221.186:5000"
    const val BASE_URL = "http://localhost:5000"

    // Auth ENDPOINT
    const val AUTH_ENDPOINT = "/auth"

    // Team Endpoints
    const val GET_ALL_TEAMS_ENDPOINT = "/team"
    const val GET_TEAM_BY_ID_ENDPOINT = "/team/{id}"
    const val CREATE_TEAM_ENDPOINT = "/team"
    const val UPDATE_TEAM_ENDPOINT = "/team/{id}"
    const val DELETE_TEAM_ENDPOINT = "/team/{id}"

    // User Endpoints
    const val GET_ALL_USERS_ENDPOINT = "/user"
    const val GET_USER_BY_ID_ENDPOINT = "/user/{id}"
    const val CREATE_A_USER_ENDPOINT = "/user"
    const val UPDATE_USER_ENDPOINT = "/user/{id}"
    const val DELETE_USER_ENDPOINT = "/user/{id}"

    // Leaderboard Endpoint
    const val GET_LEADERBOARD_ENDPOINT = "/leaderboard"
    const val GET_TOP_TEAM_LEADERBOARD_ENDPOINT = "/leaderboard/{num}"
}