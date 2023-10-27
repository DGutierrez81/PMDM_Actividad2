package com.dgutierrez.actividad_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity_dani : AppCompatActivity() {

    private lateinit var pantalla: TextView

    private lateinit var operacionAnterior: String

    private lateinit var calculo: Calculo_dani

    private lateinit var igual: Button

    private lateinit var ca: Button

    private lateinit var numeros: ArrayList<Button>

    private lateinit var signos: ArrayList<Button>

    private lateinit var borrar: Button

    var bandera = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dani)

        // Creo el objeto de la clase cálculo.
        calculo = Calculo_dani()

        Inicio()

        BotonesClick()

    }

    /**
     *  Se inicializa todas la variables de cada componente.
     */
    fun Inicio(){
        pantalla = findViewById<TextView>(R.id.pantalla)
        pantalla.text = "0"
        igual = findViewById<Button>(R.id.igual)
        ca = findViewById<Button>(R.id.CA)
        operacionAnterior = ""

        listaNumeros()

        listaSignos()

    }

    /**
     * ArrayList que contiene las variables numericas y decimal.
     */

    fun listaNumeros(){
        numeros = ArrayList()
        numeros.add(findViewById<Button>(R.id.cero))
        numeros.add(findViewById<Button>(R.id.uno))
        numeros.add(findViewById<Button>(R.id.dos))
        numeros.add(findViewById<Button>(R.id.tres))
        numeros.add(findViewById<Button>(R.id.cuatro))
        numeros.add(findViewById<Button>(R.id.cinco))
        numeros.add(findViewById<Button>(R.id.seis))
        numeros.add(findViewById<Button>(R.id.siete))
        numeros.add(findViewById<Button>(R.id.ocho))
        numeros.add(findViewById<Button>(R.id.nueve))
        numeros.add(findViewById<Button>(R.id.punto))
        numeros.add(findViewById<Button>(R.id.borrar))
    }

    /**
     *  ArrayList que contiene las variables de los signos de las operaciones a realizar.
     */
    fun listaSignos(){
        signos = ArrayList()
        signos.add(findViewById<Button>(R.id.suma))
        signos.add(findViewById<Button>(R.id.resta))
        signos.add(findViewById<Button>(R.id.multiplicar))
        signos.add(findViewById<Button>(R.id.division))
    }

    /**
     * Se accede a los métodos de elección de número y signo al pulsar el botón correspondiente.
     */
    fun BotonesClick(){
        for(i in numeros.indices){
            when(i){
                // Establece el valor del 0.
                0 -> numeros[i].setOnClickListener { ceroPunto(i.toString(), i.toString()) }
                // Establece el valor del punto.
                10 -> numeros[i].setOnClickListener { ceroPunto("0.", ".") }
                // Se dirige a la función de borrado
                11 -> numeros[i].setOnClickListener { borrado() }
                // Resto de números
                else -> numeros[i].setOnClickListener {numero(i.toString())}
            }
        }
        for(n in signos.indices){
            signos[n].setOnClickListener {
                calculo.operacion = Fsignos(n)
                operacion(operacionAnterior)
                operacionAnterior = calculo.operacion
                bandera = true
            }
        }
        ca.setOnClickListener {Ca()}
        igual.setOnClickListener {Igual()}

    }

    /**
     *  Depende del parametro número devuelve la operación a realizar.
     *  @param depende de un número que corresponde entre el 0 y el 3 se le asigna el símbolo correspondiente.
     *  @return retorna un String con el símbolo correspondiente.
     */
    fun Fsignos(numero: Int): String{
        when(numero){
            0 -> calculo.operacion = "+"
            1 -> calculo.operacion = "-"
            2 -> calculo.operacion = "*"
            3 -> calculo.operacion = "/"
        }
        return calculo.operacion
    }

    /**
     *  Se le asigna el valor al atributo correspondiente del objeto cálculo.
     *  @param contiene el número que se le asignará al atributo.
     */
    fun numero(numero: String){
        if(calculo.operacion == "") {
            calculo.num1 += numero
            pantalla.text = getString(R.string.txtpantalla,calculo.num1)
        } else {
            calculo.num2 += numero
            pantalla.text = getString(R.string.txtpantalla,calculo.num1 + calculo.operacion + calculo.num2)
        }
    }

    /**
     * Dependiendo del botón pulsasdo, se asignará al atributo 0 o punto.
     * @param conIF da valor al atributo si está dentro de un if anidado.
     * @param sinIF da valor al atributo si se encuentra fuera de un if anidado.
     */

    fun ceroPunto(conIf: String, sinIf: String){
        // Si el atributo operación está vacio se le asigna un valor al atributo num1. Si la pantalla tiene un cero se le asigna otro valor.
            if(calculo.operacion.isEmpty()){
                if(!calculo.num1.contains('.')){
                    calculo.num1 += sinIf
                    if(pantalla.text == "0" || calculo.num1 == "."){
                        calculo.num1 = conIf
                    }
                    //pantalla.text = calculo.num1
                    pantalla.text = getString(R.string.txtpantalla, calculo.num1)
                }
            }else{
                // Si la pantalla contiene es 0 y el atributo num2 contiene un punto se le asigna un valor al atributo num2, en caso contrario se le asigna otro valor.
                // Ver línea de código 99.
                if(!calculo.num2.contains('.')){
                    calculo.num2 += sinIf
                    if(pantalla.text == "0" || calculo.num2 == "."){
                        calculo.num2 = conIf
                    }
                    //pantalla.text = calculo.num2
                    pantalla.text = getString(R.string.txtpantalla, calculo.num1 + calculo.operacion + calculo.num2)
                }

            }

    }

    /**
     * Indica la acción dependiendo si es una operación anidada o una operación simple dándole al signo igual.
     */
    fun operacion(signo: String){
        if(calculo.num1 == ""){
            Execpcion("debe introducir 2 números y una operación para mostrar un resultado")
        }else{
            if(bandera == false || calculo.num2 == ""){
                pantalla.text = getString(R.string.txtpantalla, calculo.num1 + calculo.operacion)
            }else{
                calculo.calculo(signo)
                pantalla.text = calculo.resultado
                calculo.num1 = calculo.resultado
                calculo.num2 = ""
            }
        }
    }

    /**
     * Comportamiento que se obtiene al pulsar el botón igual.
     */

    fun Igual(){
        // Si cualquiera de los atributos están vacios lanza una exepción.
        if(calculo.num1 == "" || calculo.num2  == ""|| calculo.operacion == ""){
            Execpcion("debe introducir 2 números y una operación para mostrar un resultado")
        }else{
            // Se le asigna al atributo cálculo el símbolo correspondiente.
            when(calculo.operacion){
                "-" -> calculo.calculo("-")
                "+" -> calculo.calculo("+")
                "*" -> calculo.calculo("*")
                "/" -> calculo.calculo("/")
            }
            // Aparece el valor del atributo calculo por pantalla y se reinicia el resto de atributos y variables.
            pantalla.text = calculo.resultado
            calculo.num1 = calculo.resultado
            calculo.num2 = ""
            calculo.operacion = ""
            bandera = false
        }
    }

    /**
     *  Indica un mensaje de aviso.
     */

    fun Execpcion(mensaje: String){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show()
    }

    /**
     *  Restablece todo a su valor inicial.
     */
    fun Ca(){
        calculo.num1 = ""
        calculo.num2 = ""
        calculo.operacion = ""
        calculo.resultado = ""
        pantalla.text = "0"
        bandera = false
    }

    /**
     * Al pulsar el botón < activa la función que borra el útltimo dígito escrito.
     */
    fun borrado(){
        // Si el atributo num1 de la clase cálculo esta vacio lanza un mensaje de error.
        if(calculo.num1 == ""){
            pantalla.text = getString(R.string.txtpantalla, "0")
            Execpcion("No hay nada que borrar")
        }
        else{
            // Borra dígito a dígito lo que tenga el atributo num1.
            if(!calculo.num1.equals("") && calculo.operacion.equals("") && calculo.num2.equals("")){
                calculo.num1 = calculo.num1 .substring(0, calculo.num1 .length - 1)
                pantalla.text = getString(R.string.txtpantalla, calculo.num1)
            }
            // Borra la operación que marque el atributo operación.
            if(!calculo.operacion.equals("") && calculo.num2.equals("")){
                calculo.operacion = calculo.operacion .substring(0, calculo.operacion .length - 1)
                pantalla.text = getString(R.string.txtpantalla, calculo.num1 + calculo.operacion)
            }
            // Borra dígito a dígito lo que tenga el atributo num2.
            if(!calculo.num2.equals("") && !calculo.operacion.equals("") && !calculo.num1.equals("")){
                calculo.num2 = calculo.num2 .substring(0, calculo.num2 .length - 1)
                pantalla.text = getString(R.string.txtpantalla,calculo.num1 + calculo.operacion + calculo.num2)
            }
        }
    }
}