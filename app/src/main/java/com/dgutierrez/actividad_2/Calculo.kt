package com.dgutierrez.actividad_2

class Calculo {
    var num1 = ""

    var num2 = ""

    var resultado = ""

    var operacion = ""

    /**
     *  En base del signo se elige el método de la operación requerida.
     *
     *  @param Recibe el signo de la operación a realizar.
     */
    fun calculo(signo: String) {
        when(signo){
            "+" -> suma()
            "-" -> resta()
            "*" -> multiplicacion()
            "/" -> dividir()
        }
    }

    /**
     * Se realiza la operación de suma cambiando el tipo de dato si es decimal o no, almacenandolo en resultado.
     */

    fun suma():String{
        if(this.num1.contains(".") || this.num2.contains(".")){
            this.resultado = (this.num1.toDouble() + this.num2.toDouble()).toString()
        }else{
            this.resultado = (this.num1.toInt() + this.num2.toInt()).toString()
        }
        // El valor retornado corresponde al atributo resultado.
        return this.resultado
    }

    /**
     * Se realiza la operación de resta cambiando el tipo de dato si es decimal o no.
     */
    fun resta():String {
        if(this.num1.contains(".") || this.num2.contains(".")){
            this.resultado = (this.num1.toDouble() - this.num2.toDouble()).toString()
        }else{
            this.resultado = (num1.toInt() - this.num2.toInt()).toString()
        }
        // El valor retornado corresponde al atributo resultado.
        return this.resultado
    }

    /**
     * Se realiza la operación de multiplicar cambiando el tipo de dato si es decimal o no.
     */
    fun multiplicacion():String {
        if(this.num1.contains(".") || this.num2.contains(".")){
            this.resultado = (this.num1.toDouble() * this.num2.toDouble()).toString()
        }else{
            this.resultado = (this.num1.toInt() * this.num2.toInt()).toString()
        }
        // El valor retornado corresponde al atributo resultado.
        return this.resultado
    }

    /**
     * Se realiza la operación de dividir cambiando el tipo de dato si es decimal o no y en este caso si la división es inexacta.
     */

    fun dividir(): String{
        if(this.num1.contains(".") || this.num2.contains(".")){
            this.resultado = (this.num1.toDouble() / this.num2.toDouble()).toString()
        }else if(this.num1.toInt()%this.num2.toInt() != 0){
            this.resultado = (this.num1.toDouble() / this.num2.toDouble()).toString()
        }else{
            this.resultado = (this.num1.toInt() / this.num2.toInt()).toString()
        }
        // El valor retornado corresponde al atributo resultado.
        return this.resultado
    }
}