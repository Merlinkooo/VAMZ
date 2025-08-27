package com.example.hitmonitoring.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.ImageView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.hitmonitoring.database.Entities.Checks
import com.example.hitmonitoring.database.Entities.Report
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


@Composable
fun ChecksHistory(checks : List<Checks>, reports: List<Report>) {
    var checksVisited by rememberSaveable { mutableStateOf(true) }
    var reportSelected: MutableState<Report?> = rememberSaveable { mutableStateOf(null) }
    var moreReportInfoClicked = rememberSaveable { mutableStateOf(false) }

    Column () {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
        , horizontalArrangement = Arrangement.SpaceBetween

    ) {
        TextButton(onClick = {
            checksVisited = true
        },

            ) {
            Text(text="Kontroly")
        }
        TextButton(onClick = {
            checksVisited = false
        }) {
            Text(text="Reporty")
        }
    }
    if(checksVisited) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Objekt",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Čas",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Vykonané",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth())


        LazyColumn(
            modifier =
                Modifier
                    .wrapContentSize().weight(1f)
        ) {


            items(count = checks.size) { check ->

                CheckRow(checks[check], modifier = Modifier.fillMaxWidth())

            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                "Čas",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Viac",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

        }
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        LazyColumn(
            modifier =
                Modifier
                    .wrapContentSize().weight(1F)
        ) {


            items(count = reports.size) { report ->

                ReportRow(reports[report], modifier = Modifier.fillMaxWidth(),reportSelected,moreReportInfoClicked)

            }
        }
    }
        if(moreReportInfoClicked.value) {
            ReportMoreInfo(reportSelected.value, moreReportInfoClicked)
        }
}
}

@Composable
fun ReportRow(report: Report,
              modifier: Modifier= Modifier,
              reportSelected: MutableState<Report?>,
              moreInfoSelected: MutableState<Boolean>) {
    Row(modifier, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text= report.time,modifier = Modifier.weight(1f), textAlign = TextAlign.Center
        )

        IconButton(onClick = {
            reportSelected.value = report
            moreInfoSelected.value = !moreInfoSelected.value

        },modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Informácie o reporte")
        }
    }
}

@Composable
fun ReportMoreInfo(report: Report?, moreInfoClicked: MutableState<Boolean>) {
    Dialog(onDismissRequest = {
        moreInfoClicked.value = false
    }) {
        Card(Modifier.padding(bottom = 16.dp)) {
            Column(Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
            ) {

                AsyncImage(
                    model = report?.imageUri?.toUri(),
                    contentDescription = "Report image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(16.dp)),

                    contentScale = ContentScale.Crop
                )
                Text(text = "Popis incidentu", fontWeight = FontWeight.Bold)
                Text(text = report?.description ?: "Bez popisu")
            }
        }
    }
}

@Composable
fun CheckRow(check: Checks,modifier: Modifier= Modifier) {
    Row(modifier, horizontalArrangement = Arrangement.Center) {
        Text(
            text= check.tagName,modifier = Modifier.weight(1f), textAlign = TextAlign.Center
        )
        Text(
            text = check.time,modifier = Modifier.weight(1f), textAlign = TextAlign.Center
        )
        Text(
            text = check.guardID,modifier = Modifier.weight(1f), textAlign = TextAlign.Center
        )
    }
}