package com.example.hitmonitoring.ui


import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.hitmonitoring.data.Control
import com.example.hitmonitoring.ui.theme.HitMonitoringTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun ScreenAfterNFCScan(control: Control, modifier: Modifier){

    Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ControlCheckInfo(
                    control,
                    Modifier.padding(12.dp).
                    align(Alignment.CenterHorizontally))

                LargeFloatingActionButton(
                    onClick = {},
                    content = {
                        Column {
                            Icon(
                                Icons.Default.Warning,
                                "Vytvor report",
                                modifier = Modifier
                                    .scale(2f)
                                    .align(Alignment.CenterHorizontally)
                                    .padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Report",
                                textAlign = TextAlign.Center)
                        }
                    }
                )
            }
        }








@Composable
fun ControlCheckInfo(control: Control, modifier: Modifier = Modifier){
    Column(modifier = modifier
        .border(2.dp, color = Color.Black)
        .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = control.nameOfTheObject,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp)
        )
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = null,
            modifier = Modifier.background(Color.Green)
        )

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