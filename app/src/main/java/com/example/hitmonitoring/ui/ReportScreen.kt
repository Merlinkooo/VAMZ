package com.example.hitmonitoring.ui

import android.net.Uri
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue

@Composable
fun ReportScreen(
    viewModel: AppViewModel = viewModel(),
    onDescriptionChanged : (String) -> Unit
    ){

    val incidentDescription by viewModel.incidentDescription.collectAsState()

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ReportInfoCard(incidentDescription,null)
                Spacer(modifier = Modifier.padding(dimensionResource(com.example.hitmonitoring.R.dimen.main_padding)))


                TextField(
                    value = incidentDescription,
                    shape = shapes.large,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = onDescriptionChanged,
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
                Spacer(modifier = Modifier.height(dimensionResource(com.example.hitmonitoring.R.dimen.main_padding)))

                HitMonitoringButton(
                    icon = Icons.AutoMirrored.Sharp.Send,
                    buttonDescription = stringResource(com.example.hitmonitoring.R.string.send_report),
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                )

                }

        }






@Composable
fun ReportInfoCard(
    incidentDescription : String,
    imageUri: Uri?
) {
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

            if (imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "Foto incidentu",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = "Foto incidentu",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(com.example.hitmonitoring.R.dimen.main_padding)))

            Text(
                text = incidentDescription,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )






        }

    }
}




@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun ImageCardScreenPreview() {
    ReportScreen(viewModel = viewModel(), { })
}

