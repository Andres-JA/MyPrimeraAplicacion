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
    // Estados para la calculadora básica
    var inputActual by remember { mutableStateOf("0") }
    var primerNumero by remember { mutableStateOf<Double?>(null) }
    var operacion by remember { mutableStateOf<String?>(null) }
    var limpiarDespues by remember { mutableStateOf(false) }

    // Paleta de colores: Negro y Naranja armónicos
    val colorFondoInicio = Color(0xFF0F0F0F) // Negro profundo
    val colorFondoFin = Color(0xFF1C1917)    // Gris oscuro / Negro carbón
    val colorTarjeta = Color(0xFF262626)     // Superficie de tarjeta oscura
    val colorNaranja = Color(0xFFF97316)     // Naranja vibrante principal
    val colorNaranjaClaro = Color(0xFFFFEDD5) // Texto o acento suave

    // Función para manejar los clicks de los números
    fun onNumeroClick(digito: String) {
        if (inputActual == "0" || limpiarDespues) {
            inputActual = digito
            limpiarDespues = false
        } else {
            inputActual += digito
        }
    }

    // Función para manejar operaciones (+, -, *, /)
    fun onOperacionClick(op: String) {
        primerNumero = inputActual.toDoubleOrNull()
        operacion = op
        limpiarDespues = true
    }

    // Función para calcular el resultado
    fun onCalcular() {
        val segundoNumero = inputActual.toDoubleOrNull()
        if (primerNumero != null && segundoNumero != null && operacion != null) {
            val resultado = when (operacion) {
                "+" -> primerNumero!! + segundoNumero
                "-" -> primerNumero!! - segundoNumero
                "×" -> primerNumero!! * segundoNumero
                "÷" -> if (segundoNumero != 0.0) primerNumero!! / segundoNumero else Double.NaN
                else -> segundoNumero
            }
            inputActual = if (resultado % 1.0 == 0.0) {
                resultado.toInt().toString()
            } else {
                resultado.toString()
            }
            primerNumero = null
            operacion = null
            limpiarDespues = true
        }
    }

    // Limpiar calculadora
    fun onLimpiar() {
        inputActual = "0"
        primerNumero = null
        operacion = null
        limpiarDespues = false
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(colorFondoInicio, colorFondoFin)
                )
            )
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Título Principal Requerido
            Text(
                text = "Aplicación de Jairo",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = colorNaranja,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Calculadora",
                fontSize = 14.sp,
                color = Color(0xFFA3A3A3),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Tarjeta Contenedora de la Calculadora
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(colorTarjeta)
                    .padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Pantalla de la calculadora
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF0A0A0A))
                            .padding(20.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = inputActual,
                            fontSize = 38.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 1,
                            textAlign = TextAlign.End
                        )
                    }

                    // Botonera de la calculadora en filas
                    val botones = listOf(
                        listOf("C", "÷"),
                        listOf("7", "8", "9", "×"),
                        listOf("4", "5", "6", "-"),
                        listOf("1", "2", "3", "+"),
                        listOf("0", "=")
                    )

                    // Estructura simplificada de botones
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Fila 1: C y División
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { onLimpiar() },
                                modifier = Modifier.weight(2f).height(56.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF404040)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(text = "C", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                            Button(
                                onClick = { onOperacionClick("÷") },
                                modifier = Modifier.weight(1f).height(56.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = colorNaranja),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(text = "÷", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }

                        // Filas numéricas (7-9, 4-6, 1-3)
                        val filasNumeros = listOf(
                            listOf("7", "8", "9", "×"),
                            listOf("4", "5", "6", "-"),
                            listOf("1", "2", "3", "+")
                        )

                        filasNumeros.forEach { fila ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                fila.forEach { texto ->
                                    val esOperador = texto == "×" || texto == "-" || texto == "+"
                                    Button(
                                        onClick = {
                                            if (esOperador) onOperacionClick(texto)
                                            else onNumeroClick(texto)
                                        },
                                        modifier = Modifier.weight(1f).height(56.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (esOperador) colorNaranja else Color(0xFF3F3F46)
                                        ),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Text(text = texto, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                    }
                                }
                            }
                        }

                        // Fila final: 0 y =
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { onNumeroClick("0") },
                                modifier = Modifier.weight(2f).height(56.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F3F46)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(text = "0", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                            Button(
                                onClick = { onCalcular() },
                                modifier = Modifier.weight(2f).height(56.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = colorNaranja),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(text = "=", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }
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