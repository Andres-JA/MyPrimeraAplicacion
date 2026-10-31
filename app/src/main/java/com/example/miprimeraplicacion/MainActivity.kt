package com.example.miprimeraplicacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miprimeraplicacion.ui.theme.MiPrimerAplicacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPrimerAplicacionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    // 1. Estado en Kotlin: Permite que la app recuerde y actualice valores (como un contador)
    var contador by remember { mutableStateOf(0) }

    // 2. Lógica simple en Kotlin: Cambiar texto condicionalmente según el valor
    val mensajeEstado = if (contador % 2 == 0) "¡Estado Par!" else "¡Estado Impar!"

    // Paleta de colores armónica (Tonos Indigo/Slate modernos)
    val colorFondoInicio = Color(0xFF1E1B4B) // Azul oscuro profundo
    val colorFondoFin = Color(0xFF312E81)    // Índigo elegante
    val colorTarjeta = Color(0xFF3730A3)     // Azul purpúreo suave para tarjetas
    val colorAcento = Color(0xFF38BDF8)      // Celeste brillante para destacar

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(colorFondoInicio, colorFondoFin)
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Título Principal Grande
            Text(
                text = "¡Hola, Jairo!",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Bienvenido a tu app en Android Studio",
                fontSize = 16.sp,
                color = Color(0xFFCBD5E1), // Gris claro sutil
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Tarjeta con funciones principales de Kotlin/Compose
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(colorTarjeta)
                    .padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Funciones Clave en Acción:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorAcento
                    )

                    // Demostración de lógica condicional
                    Text(
                        text = mensajeEstado,
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    // Demostración de estado y eventos (Clicks)
                    Text(
                        text = "Veces presionado: $contador",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )

                    Button(
                        onClick = { contador++ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorAcento,
                            contentColor = Color(0xFF0F172A)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Probar Función (Click)", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MiPrimerAplicacionTheme {
        MainScreen()
    }
}