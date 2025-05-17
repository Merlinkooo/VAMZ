import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitmonitoring.R
import com.example.hitmonitoring.data.Control
import com.example.hitmonitoring.ui.theme.GreenIndikator
import com.example.hitmonitoring.ui.theme.HitMonitoringTheme
import com.example.hitmonitoring.ui.theme.Purple80
import com.example.hitmonitoring.ui.theme.PurpleGrey40
import com.example.hitmonitoring.ui.theme.RedIndikator
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun MainScreen(
    nameOfTheGuard: String,
    lastControl: Control?,
    modifier : Modifier = Modifier) {
    val mainPadding = dimensionResource(R.dimen.main_padding)
    Column(
        modifier = Modifier
        .statusBarsPadding()
        .verticalScroll(rememberScrollState())
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 5.dp, shape = MaterialTheme.shapes.large),
            thickness = dimensionResource(R.dimen.line_thicknes),
            color = Color.Black
        )
        Info(
            nameOfTheGuard = " Mike Madison",
            control = lastControl,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = dimensionResource(R.dimen.big_padding))
                .padding(dimensionResource(R.dimen.main_padding))
        )

        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.big_padding))
                ) {

                BottomAppBarButton(
                    Icons.AutoMirrored.Outlined.List,
                    buttonDescription = "History",
                    onClick = {},
                    modifier =  Modifier
                        .padding(dimensionResource(R.dimen.small_padding))
                        .wrapContentSize()
                )
                BottomAppBarButton(
                    icon = Icons.Default.Warning,
                    buttonDescription = "Incident",
                    onClick = {},
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.small_padding))
                        .wrapContentSize()
                )

        }

    }

}



@Composable
fun Info(nameOfTheGuard: String,
         control: Control?,
         modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        // Guard info
        Column(
            modifier = Modifier.wrapContentSize(),

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.signed),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.main_padding)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(dimensionResource(R.dimen.icon_size))
                )
                Text(
                    text = nameOfTheGuard,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.small_padding),
                    end = dimensionResource(R.dimen.small_padding)),
            thickness = dimensionResource(R.dimen.line_thicknes)
        )
        // Last Control Info
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.last_check),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.main_padding)),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(dimensionResource(R.dimen.icon_size))
                )
                Text(
                    text = control?.nameOfTheObject ?: "",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.main_padding)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start // Aligned to start
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_access_time_filled_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(dimensionResource(R.dimen.icon_size))
                )
                Text(
                    text = control?.timeOfControl ?: "",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}



@Composable
fun BottomAppBarButton(icon: ImageVector,
                       buttonDescription: String,
                       onClick: () -> Unit,
                       modifier: Modifier = Modifier) {

    Button(onClick = onClick, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = buttonDescription)
            Text(text = buttonDescription)
        }
    }
}

@Preview (showBackground = true)
@Composable
fun MainScreenPreview() {

  HitMonitoringTheme {
      MainScreen(
          nameOfTheGuard = "Mike Madison",
          lastControl = Control(
              nameOfTheObject = "Office no.6",
              timeOfControl = "08:46:34",
              gpsCoordinations = " 98.73 , 73.79"
          )
      )
  }
}