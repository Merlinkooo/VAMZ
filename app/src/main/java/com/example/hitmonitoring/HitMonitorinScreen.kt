package com.example.hitmonitoring

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HitMonitoringTopAppBar(isInMainScreen: Boolean,
        existsConnectionWithServer: Boolean,
        modifier: Modifier = Modifier
) {

    var showMenu by rememberSaveable { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), textAlign = TextAlign.Start) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (!isInMainScreen) {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_to_main_screen)
                    )
                }
            }
        },
        modifier = modifier,
        actions = {
            val fillColor = if (existsConnectionWithServer) colorResource(R.color.green_indicator) else colorResource(R.color.red_indikator)

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
                    onClick = { showMenu = false }, //doplniť logiku pre diagnostiku
                    text = { Text(stringResource(R.string.identify_problem)) }
                )


            }

        }
    )
}