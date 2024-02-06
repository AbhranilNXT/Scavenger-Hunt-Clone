package `in`.iot.lab.teambuilding.view.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import `in`.iot.lab.design.R
import `in`.iot.lab.design.components.AppScreen
import `in`.iot.lab.design.components.ErrorDialog
import `in`.iot.lab.design.components.PrimaryButton
import `in`.iot.lab.design.components.SecondaryButton
import `in`.iot.lab.design.components.TheMatrixHeaderUI
import `in`.iot.lab.design.theme.ScavengerHuntTheme
import `in`.iot.lab.network.data.models.user.RemoteUser
import `in`.iot.lab.teambuilding.view.events.TeamBuildingEvent
import `in`.iot.lab.teambuilding.view.navigation.TEAM_BUILDING_CREATE_ROUTE
import `in`.iot.lab.teambuilding.view.navigation.TEAM_BUILDING_JOIN_ROUTE
import `in`.iot.lab.teambuilding.view.navigation.TEAM_BUILDING_REGISTER_ROUTE
import `in`.iot.lab.teambuilding.view.state.UserRegistrationState

// Preview Function
@Preview("Light")
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun DefaultPreview1() {
    ScavengerHuntTheme {
        TeamHomeSuccessScreen(rememberNavController())
    }
}

// Preview Function
@Preview("Light")
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun DefaultPreview2() {
    ScavengerHuntTheme {
        TeamHomeScreenControl(
            navController = rememberNavController(),
            userState = UserRegistrationState.Error("Failed to find User"),
            setEvent = {},
            onTeamRegistered = {}
        )
    }
}


@Composable
internal fun TeamHomeScreenControl(
    navController: NavController,
    userState: UserRegistrationState<RemoteUser>,
    setEvent: (TeamBuildingEvent) -> Unit,
    onTeamRegistered: () -> Unit
) {

    // Checking User state and taking decision accordingly
    when (userState) {

        // Idle State
        is UserRegistrationState.Idle -> {
            setEvent(TeamBuildingEvent.GetUserRegistrationData)
        }

        // Loading State
        is UserRegistrationState.Loading -> {
            CircularProgressIndicator()
        }

        // Not Registered and not in Team State
        is UserRegistrationState.NotRegistered -> {
            TeamHomeSuccessScreen(navController = navController)
        }

        // In a Team and not Registered State
        is UserRegistrationState.InTeam -> {
            navController.navigate(TEAM_BUILDING_REGISTER_ROUTE)
        }

        // User Team is Registered State
        is UserRegistrationState.Registered -> {
            onTeamRegistered()
        }

        // Error State
        is UserRegistrationState.Error -> {
            ErrorDialog(
                text = userState.message,
                onCancel = {
                    navController.popBackStack()
                },
                onTryAgain = {
                    setEvent(TeamBuildingEvent.GetUserRegistrationData)
                }
            )
        }
    }
}


@Composable
private fun TeamHomeSuccessScreen(navController: NavController) {


    // Background Related Customizations
    AppScreen {

        // Background Image
        Image(
            painter = painterResource(id = R.drawable.matrix_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // Parent Composable
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            // Matrix and Scavenger Hunt Image Header
            TheMatrixHeaderUI()

            Spacer(modifier = Modifier.height(150.dp))

            // Create Team Button
            SecondaryButton(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 32.dp)
                    .fillMaxWidth()
                    .height(height = 56.dp),
                onClick = { navController.navigate(TEAM_BUILDING_CREATE_ROUTE) },
            ) {
                Text(text = "CREATE TEAM")
            }

            // Join Team Button
            PrimaryButton(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 32.dp)
                    .fillMaxWidth()
                    .height(height = 56.dp),
                onClick = { navController.navigate(TEAM_BUILDING_JOIN_ROUTE) }) {
                Text(text = "JOIN TEAM")
            }
        }
    }
}