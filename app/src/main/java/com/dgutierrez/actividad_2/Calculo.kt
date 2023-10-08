package com.dgutierrez.actividad_2

class Calculo {
    var num1 = ""

    var num2 = ""

    var resultado = ""

    var operacion = ""

    fun calculo(signo: String): String {
        when(signo){
            "+" -> if(num1.contains(".") || num2.contains(".")){
                resultado = (num1.toDouble() + num2.toDouble()).toString()
            }else{
                resultado = (num1.toInt() + num2.toInt()).toString()
            }
            "-" -> if(num1.contains(".") || num2.contains(".")){
                resultado = (num1.toDouble() - num2.toDouble()).toString()
            }else{
                resultado = (num1.toInt() - num2.toInt()).toString()
            }
            "*" -> if(num1.contains(".") || num2.contains(".")){
                resultado = (num1.toDouble() * num2.toDouble()).toString()
            }else{
                resultado = (num1.toInt() * num2.toInt()).toString()
            }
            "/" -> if(num1.contains(".") || num2.contains(".")){
                resultado = (num1.toDouble() / num2.toDouble()).toString()
            }else if(num1.toInt()%num2.toInt() != 0){
                resultado = (num1.toDouble() / num2.toDouble()).toString()
            }else{
                resultado = (num1.toInt() / num2.toInt()).toString()
            }
        }
        return resultado
    }
}