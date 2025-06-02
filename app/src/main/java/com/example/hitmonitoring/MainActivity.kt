package com.example.hitmonitoring




import android.app.PendingIntent
import android.content.Intent
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.hitmonitoring.ui.ConfirmationScreen
import com.example.hitmonitoring.HitMonitorinScreen

import com.example.hitmonitoring.ui.theme.HitMonitoringTheme

class MainActivity : ComponentActivity() {

    private var nfcAdapter: NfcAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            HitMonitoringTheme {
                HitMonitoringScreen(navController = navController)
            }
        }
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

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