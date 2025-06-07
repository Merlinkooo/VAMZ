package com.example.hitmonitoring.ui


import android.graphics.Paint.Align
import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.hitmonitoring.R
import com.example.hitmonitoring.data.Control
import com.example.hitmonitoring.ui.theme.HitMonitoringTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun ScreenAfterNFCScan(control: Control, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ControlCheckInfo(
            control,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.big_padding))
                .padding(dimensionResource(R.dimen.main_padding))
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )


        HitMonitoringButton(
            icon = Icons.Default.Warning,
            buttonDescription = stringResource(R.string.make_report),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
        )

    }
}







@Composable
fun ControlCheckInfo(control: Control, modifier: Modifier = Modifier){

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {


        Text(
            text = stringResource(R.string.control_done),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(dimensionResource(R.dimen.main_padding))
        )

        Row() {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_size))
                    .padding(end = dimensionResource(R.dimen.main_padding))
            )
            Text(
                text = control.nameOfTheObject,
                fontSize = 24.sp,
                textAlign = TextAlign.Start
            )
        }

        Row() {
            Icon(
                painter = painterResource(R.drawable.baseline_access_time_filled_24),
                contentDescription = null
            )
            Text(
                text = control.timeOfControl,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(12.dp)
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun ScreenNFCPriew(){
    HitMonitoringTheme {
        ScreenAfterNFCScan(Control(
            nameOfTheObject =  "Kancelária č.10",
            timeOfControl = "08:46:53",
            gpsCoordinations = ""
            ), Modifier.fillMaxSize()
        )
    }
}