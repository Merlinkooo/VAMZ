package com.example.hitmonitoring.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hitmonitoring.database.Entities.Checks

@Composable
fun ChecksHistory(checks : List<Checks>) {




    LazyColumn (modifier =
        Modifier
            .wrapContentSize().height(500.dp)
            ){
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Objekt", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                Text("Čas", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                Text("Vykonané", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        items(count = checks.size) {check ->

                CheckRow(checks[check], modifier = Modifier.fillMaxWidth().wrapContentSize())

        }
    }

}


@Composable
fun CheckRow(check: Checks,modifier: Modifier= Modifier) {
    Row(modifier) {
        Text(
            text= check.tagId
        )
        Text(
            text = check.time
        )
        Text(
            text = check.guardID
        )
    }
}