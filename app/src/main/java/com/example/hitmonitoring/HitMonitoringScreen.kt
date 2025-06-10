package com.example.hitmonitoring


import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hitmonitoring.data.Control
import com.example.hitmonitoring.network.ConnectionStatus
import com.example.hitmonitoring.ui.AppViewModel
import com.example.hitmonitoring.ui.ConfirmationScreen
import com.example.hitmonitoring.ui.MainScreen
import com.example.hitmonitoring.ui.ReportScreen
import com.example.hitmonitoring.ui.ScreenAfterNFCScan
import com.example.hitmonitoring.ui.theme.HitMonitoringTheme


enum class HitMonitorinScreen {
    Main,
    Scan,
    Report,
    Confirmation
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HitMonitoringTopAppBar(
        isInMainScreen: Boolean,
        allFunctionalityWorks: Boolean,
        navigateTo: () -> Unit,
        viewModel: AppViewModel,
        modifier: Modifier = Modifier
) {

    var showMenu by rememberSaveable { mutableStateOf(false) }

    val context: Context = LocalContext.current
    val connectionResult by viewModel.isOnline.collectAsState(ConnectionStatus.INITIAL)

    var showDiagnosisDialog by rememberSaveable { mutableStateOf(false) }
    var dialogMessage by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(connectionResult) {
        if (connectionResult != ConnectionStatus.INITIAL) {
            when (connectionResult) {
                ConnectionStatus.NO_INTERNET -> {
                    dialogMessage = context.getString(R.string.internet_connection_missing)
                }

                ConnectionStatus.SERVER_ERROR -> {
                    dialogMessage = context.getString(R.string.internet_connection_missing)
                }
                else -> {}
            }
            if (dialogMessage.isNotEmpty()) showDiagnosisDialog = true
        }
    }
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), textAlign = TextAlign.Start) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (!isInMainScreen) {
                IconButton(onClick = navigateTo) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_to_main_screen)
                    )
                }
            }
        },
        modifier = modifier,
        actions = {
            val fillColor = if (allFunctionalityWorks) colorResource(R.color.green_indicator) else colorResource(R.color.red_indikator)

            Canvas(
                modifier = Modifier.size(24.dp)
            ) {
                drawCircle(
                    color = fillColor,
                    radius = size.minDimension / 2,
                    center = Offset(size.width / 2, size.height / 2),
                    style = Fill
                )

            }
            IconButton(
                modifier = Modifier.wrapContentSize(),
                onClick = {showMenu = !showMenu}) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(R.dimen.icon_size))
                )
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false}
            ) {
                DropdownMenuItem(
                    onClick = {
                        viewModel.diagnoseConnection(context)

                        showMenu = false
                              },
                    text = { Text(stringResource(R.string.identify_problem)) }
                )


            }

        }
    )
    if (showDiagnosisDialog) {
        com.example.hitmonitoring.AlertDialog(dialogMessage)
    }

}

@Composable
fun AlertDialog(dialogMessage: String) {
    Dialog(
        onDismissRequest = {}
    ) {
        Text(text = dialogMessage)
    }

}


@Composable
fun HitMonitoringScreen(
    viewModel: AppViewModel,
    navController: NavHostController = rememberNavController()
) {
    val appUiState by viewModel.uiState.collectAsState()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route


    LaunchedEffect(appUiState.newObjectDetected) {
        if (appUiState.newObjectDetected) {
            navController.navigate(HitMonitorinScreen.Scan.name)
            viewModel.clearNewObjectDetected()
        }
    }


    Scaffold (
        topBar = {
            HitMonitoringTopAppBar(
                isInMainScreen = currentRoute == HitMonitorinScreen.Main.name,
                allFunctionalityWorks = viewModel.isOnline.collectAsState().value == ConnectionStatus.CONNECTED,
                viewModel = viewModel,
                navigateTo = {
                    if (navController.previousBackStackEntry != null) {
                    navController.popBackStack()
                }}
            )
        }
    ) { paddingValues ->


        NavHost(
            navController = navController,
            startDestination = HitMonitorinScreen.Main.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = HitMonitorinScreen.Main.name){
                MainScreen(
                        nameOfTheGuard = appUiState.nameOfGuard,
                        lastControl = appUiState.lastControl,
                        onReportButtonClicked = {navController.navigate(HitMonitorinScreen.Report.name)}
                    )
            }
            composable(route = HitMonitorinScreen.Scan.name) {
                ScreenAfterNFCScan(appUiState.lastControl)
            }

            composable(route = HitMonitorinScreen.Report.name) {
                ReportScreen(
                    viewModel,
                    {newText -> viewModel.updateIncidentDescription(newText) }
                    )
            }

            composable(route = HitMonitorinScreen.Confirmation.name) {
                ConfirmationScreen()
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun HitMonitoringScreenPreview(){
    HitMonitoringTheme {
        HitMonitoringScreen(viewModel = viewModel(),navController = rememberNavController())
    }
}