package com.example.hitmonitoring



import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.NfcA
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.hitmonitoring.ui.ConfirmationScreen
import com.example.hitmonitoring.HitMonitorinScreen
import com.example.hitmonitoring.database.AppDatabase
import com.example.hitmonitoring.database.DatabaseProvider
import com.example.hitmonitoring.database.Entities.User

import com.example.hitmonitoring.ui.AppViewModel

import com.example.hitmonitoring.ui.theme.HitMonitoringTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {

    private var nfcAdapter: NfcAdapter? = null
    lateinit private var viewModel: AppViewModel

    private lateinit var navController: NavHostController
    private lateinit var fusedLocationClient: FusedLocationProviderClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        setContent {
            navController = rememberNavController()
            HitMonitoringTheme {
               viewModel = viewModel(factory = AppViewModel.Factory)
                HitMonitoringScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }


    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        val techList = arrayOf(arrayOf(NfcA::class.java.name))

        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, null, techList)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }






    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Log.d("NFC_DEBUG", "onNewIntent called with action: ${intent.action}")

        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action) {
            Log.d("NFC_DEBUG", "TAG_DISCOVERED intent received")

            val tag: Tag? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(NfcAdapter.EXTRA_TAG, Tag::class.java)
            } else {
                intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            }

            tag?.let {
                val id = it.id
                val hexId = id.joinToString("") { byte -> "%02x".format(byte) }
                Log.d("NFC", "Tag ID: $hexId")
                Toast.makeText(this, "NFC tag ID: $hexId", Toast.LENGTH_LONG).show()

                viewModel.getTagInfo(hexId)

            }
        }
    }


}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HitMonitoringTheme {

    }
}