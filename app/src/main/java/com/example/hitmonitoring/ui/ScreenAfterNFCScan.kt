package com.example.hitmonitoring.ui

import Top
import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column

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
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun ScreenAfterNFCScan(){
    Scaffold(
        topBar = {
            Top(false)
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalDivider(
                    color = Color.Black,
                    thickness = 2.dp,
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxWidth()
                )

                ControlCheckInfo(
                    "Office no.7 08:26:33",
                    Modifier.padding(12.dp).
                    align(Alignment.CenterHorizontally))

            }
        },
        floatingActionButton = {
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
        },
        floatingActionButtonPosition = FabPosition.Start


            )


}

@Composable
fun ControlCheckInfo(info: String, modifier: Modifier = Modifier){
    Column(modifier = modifier
        .border(2.dp, color = Color.Black)
        .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = info,
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