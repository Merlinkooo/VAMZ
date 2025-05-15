package com.example.hitmonitoring.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hitmonitoring.ui.theme.HitMonitoringTheme
import androidx.compose.material3.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitmonitoring.R

@Composable
fun ConfirmationScreen(reportSended: Boolean) {
    HitMonitoringTheme {
        Surface( modifier = Modifier.fillMaxSize(),
                ) {
            Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                if (reportSended) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector =  Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = Color.Blue,
                            modifier = Modifier.size(70.dp)
                        )
                        Text (
                            text = stringResource(R.string.report_sended),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    }

                } else {
                    CircularProgressIndicator(
                        modifier = Modifier.size(70.dp),
                        color = Color.Blue
                    )
                }
            }
        }
    }

}