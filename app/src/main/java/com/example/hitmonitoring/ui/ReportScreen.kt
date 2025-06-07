package com.example.hitmonitoring.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.Send
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ReportScreen(){

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ReportInfoCard()
                Spacer(modifier = Modifier.padding(dimensionResource(com.example.hitmonitoring.R.dimen.main_padding)))
                Button (onClick = {},
                    modifier = Modifier.fillMaxWidth()
                    ) {
                    Icon(
                            imageVector = Icons.AutoMirrored.Sharp.Send,
                            contentDescription = null,

                        )
                        Text(text = stringResource(com.example.hitmonitoring.R.string.send_report))
                    }
                }

        }





@Composable
fun BottomApp(modifier: Modifier = Modifier){
    BottomAppBar(modifier = modifier) {
        HitMonitoringButton(
            icon = Icons.Filled.Edit,
            buttonDescription = "Text",
            onClick = {},
            Modifier.weight(1f).padding(2.dp)
        )
        HitMonitoringButton(
            Icons.Default.Warning,
            buttonDescription = "Foto",
            onClick = {},
            Modifier.weight(1f).padding(2.dp)
        )
        HitMonitoringButton(
            Icons.AutoMirrored.Sharp.Send,
            buttonDescription = "Pošli",
            onClick = {},
            Modifier.weight(1f).padding(2.dp)
        )
    }
}
@Composable
fun ReportInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Predvolený obrázok, nahraď svojím riešením
                contentDescription = "Foto incidentu",

                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Názov obrázka alebo popis",
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ďalší detailný popis k obrázku, ktorý sa môže rozprestierať na viacero riadkov.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
            TextField(
                value = "",
                shape = shapes.large,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {},
                label = {
                    Text(text = stringResource(com.example.hitmonitoring.R.string.enter_description))
                }
            )
            Spacer(modifier = Modifier.height(dimensionResource(com.example.hitmonitoring.R.dimen.main_padding)))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {

                    Icon(
                        painter = painterResource(com.example.hitmonitoring.R.drawable.baseline_add_a_photo_24),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(dimensionResource(com.example.hitmonitoring.R.dimen.icon_size))
                    )

                Text(stringResource(com.example.hitmonitoring.R.string.make_photo))
            }
        }

    }
}




@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun ImageCardScreenPreview() {
    ReportScreen()
}

