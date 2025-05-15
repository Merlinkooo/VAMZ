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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hitmonitoring.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            Top(true)
        },
        bottomBar = {
            BottomAp(modifier = Modifier.fillMaxWidth().padding(12.dp))
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                     // Zabezpečí, že Column zaberie celý dostupný priestor
                    .padding(paddingValues)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally // Horizontálne vycentruje obsah Columnu
            ) {
                HorizontalDivider(
                    color = Color.Black,
                    thickness = 2.dp,
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxWidth()
                )

                    Info("Mike Madison", "Office no.6 08:26:33", Modifier.padding(12.dp))

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Top(isInMainScreen: Boolean, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name), textAlign = TextAlign.Start)
        },
        navigationIcon = {
            if (!isInMainScreen) {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_to_main_screen) // Použi string resource pre lokalizáciu
                    )
                }
            }
        },
        modifier = modifier.fillMaxWidth(), // TopAppBar zaberie celú šírku
        actions = {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = Color.Red
            )
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun Info(nameOfTheGuard: String, lastCheckInfo: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center, // Vertikálne vycentruje obsah Columnu
        horizontalAlignment = Alignment.CenterHorizontally, // Horizontálne vycentruje obsah Columnu
        modifier = modifier
            .padding(16.dp)
            .border(2.dp,Color.Black)
            .background(Color.Gray)
            .wrapContentSize()
    ) {
        Row(Modifier.fillMaxWidth().weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(text = nameOfTheGuard)
        }
        Row(Modifier.fillMaxWidth().padding(top = 8.dp).weight(1f)
            ,verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Center) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = lastCheckInfo,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun BottomAp(modifier: Modifier = Modifier) {
    BottomAppBar(modifier = modifier) {
        BottomAppBarButton(
            Icons.AutoMirrored.Outlined.List,
            buttonDescription = "History",
            onClick = {},
            Modifier.weight(1f).padding(2.dp)
        )
        BottomAppBarButton(
            Icons.Default.Warning,
            buttonDescription = "Incident",
            onClick = {},
            Modifier.weight(1f).padding(2.dp)
        )
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

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}