
package com.edith.smarthome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EdithApp()
        }
    }
}

@Composable
fun EdithApp() {
    var page by remember { mutableStateOf("Home") }
    var deviceIp by remember { mutableStateOf("192.168.4.1") }

    val bg = Color(0xFF080D18)
    val panel = Color(0xFF172337)
    val cyan = Color(0xFF42D9FF)

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bg)
                .padding(18.dp)
        ) {
            Text(
                "E.D.I.T.H.",
                color = cyan,
                fontSize = 30.sp
            )
            Text(
                "PERSONAL SMART HOME SYSTEM",
                color = Color.LightGray,
                fontSize = 11.sp
            )

            Spacer(Modifier.height(20.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Home", "Pixel Studio", "Settings").forEach {
                    Button(onClick = { page = it }) {
                        Text(it, fontSize = 10.sp)
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            when (page) {
                "Home" -> {
                    Text("HOME CONTROL", color = cyan)
                    Spacer(Modifier.height(12.dp))
                    Text("ESP32 IP ADDRESS", color = Color.White)
                    OutlinedTextField(
                        value = deviceIp,
                        onValueChange = { deviceIp = it },
                        label = { Text("Device IP") }
                    )
                    Spacer(Modifier.height(12.dp))

                    repeat(4) { index ->
                        var enabled by remember { mutableStateOf(false) }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = panel
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Relay ${index + 1}",
                                    color = Color.White,
                                    modifier = Modifier.weight(1f)
                                )
                                Switch(
                                    checked = enabled,
                                    onCheckedChange = { enabled = it }
                                )
                            }
                        }
                    }
                }

                "Pixel Studio" -> {
                    Text("EDITH PIXEL STUDIO", color = cyan)
                    Text(
                        "50 WS2812B LEDs",
                        color = Color.White
                    )
                    Text(
                        "RGB effects and Bluetooth controls will be connected next.",
                        color = Color.LightGray
                    )
                }

                "Settings" -> {
                    Text("SETTINGS", color = cyan)
                    Text(
                        "Configure your EDITH device connection.",
                        color = Color.White
                    )
                }
            }

            Spacer(Modifier.weight(1f))
            Text(
                "EDITH • SYSTEM INTERFACE",
                color = Color.Gray,
                fontSize = 10.sp
            )
        }
    }
}
