package com.dgutierrez.actividad_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculo = Calculo()

        var operacionAnterior = ""

        var bandera = false

        var pantalla = findViewById<TextView>(R.id.pantalla)

        pantalla.text = "0"


        val igual = findViewById<Button>(R.id.igual)
        igual.setOnClickListener {
            if(calculo.num1 == "" || calculo.num2  == ""|| calculo.operacion == ""){
                Toast.makeText(this,"debe introducir 2 números y una operación para mostrar un resultado",Toast.LENGTH_LONG).show()
            }else{
                when(calculo.operacion){
                    "-" -> calculo.calculo("-")
                    "+" -> calculo.calculo("+")
                    "*" -> calculo.calculo("*")
                    "/" -> calculo.calculo("/")
                }
                pantalla.text = calculo.resultado
                calculo.num1 = ""
                calculo.num2 = ""
                calculo.operacion = ""
                bandera = false
            }
        }

        val ca = findViewById<Button>(R.id.CA)
        ca.setOnClickListener {
            calculo.num1 = ""
            calculo.num2 = ""
            calculo.operacion = ""
            calculo.resultado = ""
            pantalla.text = "0"
            bandera = false
        }

        val resta = findViewById<Button>(R.id.resta)
        resta.setOnClickListener {
            calculo.operacion = "-"
            operacion(operacionAnterior, pantalla, calculo, bandera)
            operacionAnterior = calculo.operacion
            bandera = true
        }

        val uno = findViewById<Button>(R.id.uno)

        uno.setOnClickListener {
            numero(calculo, pantalla, "1")
        }

        val dos = findViewById<Button>(R.id.dos)

        dos.setOnClickListener {
            numero(calculo, pantalla, "2")
        }

        val tres = findViewById<Button>(R.id.tres)
        tres.setOnClickListener {
            numero(calculo, pantalla, "3")
        }

        val suma = findViewById<Button>(R.id.suma)

        suma.setOnClickListener {
            calculo.operacion = "+"
            operacion(operacionAnterior, pantalla, calculo, bandera)
            operacionAnterior = calculo.operacion
            bandera = true
        }

        val cuatro = findViewById<Button>(R.id.cuatro)
        cuatro.setOnClickListener {
            numero(calculo, pantalla, "4")
        }

        val cinco = findViewById<Button>(R.id.cinco)
        cinco.setOnClickListener {
            numero(calculo, pantalla, "5")
        }

        val seis = findViewById<Button>(R.id.seis)
        seis.setOnClickListener {
            numero(calculo, pantalla, "6")
        }

        val multiplicacion = findViewById<Button>(R.id.multiplicar)
        multiplicacion.setOnClickListener {
            calculo.operacion = "*"
            operacion(operacionAnterior, pantalla, calculo, bandera)
            operacionAnterior = calculo.operacion
            bandera = true
        }

        val siete = findViewById<Button>(R.id.siete)
        siete.setOnClickListener {
            numero(calculo, pantalla, "7")
        }

        val ocho = findViewById<Button>(R.id.ocho)
        ocho.setOnClickListener {
            numero(calculo, pantalla, "8")
        }

        val nueve = findViewById<Button>(R.id.nueve)
        nueve.setOnClickListener {
            numero(calculo, pantalla, "9")
        }

        val cero = findViewById<Button>(R.id.cero)
        cero.setOnClickListener {
            ceroPunto(calculo, pantalla, "0", "0")
        }

        val punto = findViewById<Button>(R.id.punto)
        punto.setOnClickListener {
            ceroPunto(calculo, pantalla, "0.", ".")
        }

        val dividir = findViewById<Button>(R.id.division)
        dividir.setOnClickListener {
            calculo.operacion = "/"
            operacion(operacionAnterior, pantalla, calculo, bandera)
            operacionAnterior = calculo.operacion
            bandera = true
        }
    }

    fun numero(calculo: Calculo, pantalla: TextView, numero: String){
        if(calculo.operacion == "") {
            calculo.num1 += numero
            pantalla.text = calculo.num1
        } else {
            calculo.num2 += numero
            pantalla.text = calculo.num2
        }
    }

    fun ceroPunto(calculo: Calculo, pantalla: TextView, conIf: String, sinIf: String){
        if(calculo.operacion.isEmpty()){
            calculo.num1 += sinIf
            if(pantalla.text == "0"){
                calculo.num1 = conIf
            }
            pantalla.text = calculo.num1
        }else{
            calculo.num2 += sinIf
            if(pantalla.text == "0" || calculo.num2 == "."){
                calculo.num2 = conIf
            }
            pantalla.text = calculo.num2
        }
    }

    fun operacion(signo: String, pantalla: TextView, calculo: Calculo, bandera: Boolean){

        if(bandera == false || calculo.num2 == ""){
            pantalla.text = calculo.num1
        }else{
            calculo.calculo(signo)
            pantalla.text = calculo.resultado
            calculo.num1 = calculo.resultado
            calculo.num2 = ""
        }
    }
}