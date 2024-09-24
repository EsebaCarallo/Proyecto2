import kotlin.math.roundToInt


class Examen(val nombres: Array<String> = Array(4){""}){
    val plantillas: Array<Char> = arrayOf('a', 'c', 'b', 'a', 'd', 'b', 'b', 'c', 'a', 'a', 'b', 'd')
    var notas: FloatArray = FloatArray(4)
    val respuestas: Array<CharArray> = Array(4) { CharArray(12) }
    var contador: Int = 0

    fun leerrespuestas(respuestasEstudiantes: CharArray) {
        if (contador < 4) {
            respuestas[contador] = respuestasEstudiantes
            contador++
        }
    }

    fun ToString(){
        println("-----------------------------------------------------------------------")
        for (i in 0 until contador) {
            calculaNota(respuestas, plantillas, notas)
            val estados = estadoNota(notas)
            println("Estudiante: ${nombres[i]} Respuestas: ${respuestas[i].joinToString(" ")} Notas: ${(notas[i] * 100.0).roundToInt() / 100.0} ${estados[i]}")
        }
        val promedio = promedioGrupo()
        println()
        println("Promedio del grupo: ${(promedio * 100.0).roundToInt() / 100.0}")
        val mejor = mayorNota()
        println("El estudiante con mayor nota es $mejor,")
        println("------------------------------------------------------------------------")
    }
    fun calculaNota(respuestas: Array<CharArray>, plantilla: Array<Char>, notas: FloatArray) {
        for (fila in respuestas.indices) {
            var puntos = 0
            for (columna in respuestas[fila].indices) {
                if (respuestas[fila][columna] == plantilla[columna]) {
                    puntos++
                }
            }
            notas[fila] = (puntos*100/12.0f)

        }
    }

    fun promedioGrupo(): Float{
        var suma = 0.0f
        for (i in notas.indices) {
            suma += notas[i]
        }
        return suma/notas.size
    }
    fun mayorNota(): String {
        var mayor = notas[0]
        var nombre = 0
        for (i in notas.indices) {
            if (notas[i] > mayor) {
                mayor = notas[i]
                nombre = i
            }
        }
        return nombres[nombre]
    }

    fun estadoNota(notas: FloatArray): Array<String> {
        val estados = Array(notas.size) {""}
        for (i in notas.indices) {
            estados[i] = when {
                notas[i] >= 70.0f -> "Aprobó"
                notas[i] < 70.0f && notas[i] >= 60.0f -> "Aplazó"
                else -> "Reprobó"
            }
        }
        return estados
    }
}

fun main() {
    val Examen = Examen(arrayOf("Marta", "Pedro", "Juan", "Maria"))
    Examen.leerrespuestas(charArrayOf('a', 'c', 'b', 'a', 'd', 'b', 'b', 'c', 'a', 'a', 'b', 'd'))
    Examen.leerrespuestas(charArrayOf('a', 'c', 'b', 'a', 'd', 'b', 'b', 'c', 'a', 'a', 'b', 'd'))
    Examen.leerrespuestas(charArrayOf('a', 'c', 'b', 'a', 'd', 'b', 'b', 'c', 'a', 'a', 'b', 'd'))
    Examen.leerrespuestas(charArrayOf('a', 'c', 'b', 'a', 'd', 'b', 'b', 'c', 'a', 'a', 'b', 'd'))
    Examen.ToString()
}


