import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.sharp.Send
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@Composable
fun ReportScreen(){
    Scaffold(
        topBar = { Top(false,true) },
        bottomBar = { BottomApp() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalDivider(
                    color = Color.Black,
                    thickness = 2.dp,
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxWidth()
                )
                ReportInfoCard()

        }

    }
    )
}

@Composable
fun BottomApp(modifier: Modifier = Modifier){
    BottomAppBar(modifier = modifier) {
        BottomAppBarButton(
            icon = Icons.Filled.Edit,
            buttonDescription = "Text",
            onClick = {},
            Modifier.weight(1f).padding(2.dp)
        )
        BottomAppBarButton(
            Icons.Default.Warning,
            buttonDescription = "Foto",
            onClick = {},
            Modifier.weight(1f).padding(2.dp)
        )
        BottomAppBarButton(
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
                // Miesto pre obrázok
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Predvolený obrázok, nahraď svojím riešením
                    contentDescription = "Foto incidentu",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Názov obrázka alebo popis",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ďalší detailný popis k obrázku, ktorý sa môže rozprestierať na viacero riadkov.",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
        }

}




@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun ImageCardScreenPreview() {
    ReportInfoCard()
}

