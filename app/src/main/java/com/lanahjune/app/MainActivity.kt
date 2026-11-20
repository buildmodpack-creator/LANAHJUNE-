package com.lanahjune.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PastelBg = Color(0xFFFFF7FB)
private val PastelCard = Color(0xFFFFFFFF)
private val PastelAccent = Color(0xFFE7A8C7)
private val McBg = Color(0xFF17251B)
private val McCard = Color(0xFF263B2B)
private val McAccent = Color(0xFF75C878)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LanahJuneApp() }
    }
}

@Composable
fun LanahJuneApp() {
    var theme by remember { mutableStateOf("Pastel") }
    var section by remember { mutableStateOf("MOD") }
    var prompt by remember { mutableStateOf("") }
    var showSettings by remember { mutableStateOf(false) }
    val dark = theme == "Minecraft"
    val bg = if (dark) McBg else PastelBg
    val card = if (dark) McCard else PastelCard
    val accent = if (dark) McAccent else PastelAccent
    val fg = if (dark) Color.White else Color(0xFF392D36)
    val muted = if (dark) Color(0xFFB8C9BA) else Color(0xFF8B7884)

    MaterialTheme(
        colorScheme = if (dark) darkColorScheme(
            primary = accent, background = bg, surface = card, onSurface = fg
        ) else lightColorScheme(
            primary = accent, background = bg, surface = card, onSurface = fg
        )
    ) {
        Surface(Modifier.fillMaxSize(), color = bg) {
            Column(Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("LANAHJUNE", fontSize = 25.sp, fontWeight = FontWeight.Black, color = fg)
                        Text("MINECRAFT AI CREATION STUDIO", fontSize = 10.sp, color = muted, letterSpacing = 1.sp)
                    }
                    IconButton(onClick = { showSettings = true }) {
                        Icon(Icons.Default.Settings, "Cài đặt", tint = fg)
                    }
                }
                Spacer(Modifier.height(18.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("MOD", "PACK", "SHADER").forEach { item ->
                        FilterChip(
                            selected = section == item,
                            onClick = { section = item },
                            label = { Text(item, fontWeight = FontWeight.Bold) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = accent,
                                selectedLabelColor = if (dark) Color.Black else Color(0xFF392D36)
                            )
                        )
                    }
                }
                Spacer(Modifier.height(14.dp))
                Card(colors = CardDefaults.cardColors(containerColor = card), shape = RoundedCornerShape(20.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Tạo ${section.lowercase()} Minecraft", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = fg)
                        Spacer(Modifier.height(6.dp))
                        Text("Mô tả ý tưởng bằng tiếng Việt. AI sẽ phân tích phiên bản, nền tảng và cấu trúc cần tạo.", color = muted, fontSize = 13.sp)
                        Spacer(Modifier.height(12.dp))
                        OutlinedTextField(
                            value = prompt,
                            onValueChange = { prompt = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Ví dụ: tạo mod client có đôi cánh và animation...") },
                            minLines = 3,
                            shape = RoundedCornerShape(14.dp)
                        )
                        Spacer(Modifier.height(10.dp))
                        Button(
                            onClick = { /* Backend connection will be added in the next milestone. */ },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = accent)
                        ) {
                            Icon(Icons.Default.AutoAwesome, null)
                            Spacer(Modifier.width(8.dp))
                            Text("TẠO Ý TƯỞNG", color = if (dark) Color.Black else Color(0xFF392D36))
                        }
                    }
                }
                Spacer(Modifier.height(14.dp))
                Text("XEM TRƯỚC & DỰ ÁN", fontWeight = FontWeight.Bold, color = fg)
                Spacer(Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    colors = CardDefaults.cardColors(containerColor = card),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Icon(Icons.Default.ViewInAr, null, tint = accent, modifier = Modifier.size(48.dp))
                        Spacer(Modifier.height(8.dp))
                        Text("Khung xem trước", fontWeight = FontWeight.Bold, color = fg)
                        Text("Ảnh, video, model 3D và file dự án sẽ xuất hiện tại đây khi các mô-đun được kết nối.", color = muted, fontSize = 12.sp)
                    }
                }
                Spacer(Modifier.height(10.dp))
                Text("Java Edition  ·  Bedrock Edition  ·  Cloud + Termux", color = muted, fontSize = 10.sp)
            }

            if (showSettings) {
                AlertDialog(
                    onDismissRequest = { showSettings = false },
                    title = { Text("Giao diện LANAHJUNE") },
                    text = {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("Pastel", "Minecraft").forEach { choice ->
                                Row(
                                    Modifier.fillMaxWidth().clickable { theme = choice },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(selected = theme == choice, onClick = { theme = choice })
                                    Text(if (choice == "Pastel") "C · Pastel" else "B · Minecraft")
                                }
                            }
                        }
                    },
                    confirmButton = { TextButton(onClick = { showSettings = false }) { Text("Đóng") } }
                )
            }
        }
    }
}
