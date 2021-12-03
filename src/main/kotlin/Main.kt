import kotlin.math.pow
import kotlin.math.sqrt

class Punto(var id: String) {
    var x: Int = 0
    var y: Int = 0

    /**
     * Constructor secundario con parámetros x e y
     */
    constructor(id: String, x: Int, y: Int): this(id) {
        this.x = x
        this.y = y
    }

    /**
     * RA2.c: Método que devuelve instancia de la clase Pair, con las coordenadas del punto
     */
    fun obtenerCoordenadas(): Pair<Int,Int> {
        return Pair(x,y)
    }

    /**
     * RA2.d: Método toString, que devuelve un objeto de clase String que representa la instancia de la clase Punto
     */
    override fun toString(): String {
        return "$id -> [$x,$y]"
    }

    companion object {
        /**
         * RA2.e,f: Método estático que realiza la resta entre dos puntos y devuelve un punto como resultado
         */
        fun componenteDeVector(p1: Punto, p2: Punto): Punto {
            val sumid: String = (p1.id + p2.id)
            val res1: Int = (p2.x - p1.x)
            val res2: Int = (p2.y - p1.y)
            val presultado = Punto("$sumid", res1, res2)
            return presultado
        }

        /**
         * RA2.e,f: Método estático que devuelve la distancia entre dos puntos y devuelve un valor Double como resultado
         */
        fun distancia(p1: Punto, p2: Punto): Double {
            val potenciaDos1: Double = (p2.x - p1.x).toDouble().pow(2.toDouble())
            val potenciaDos2: Double = (p2.y - p1.y).toDouble().pow(2.toDouble())
            return sqrt(potenciaDos1 + potenciaDos2)
        }

        /**
         * RA6.a,c: Método estático que clasifica los puntos (los cuales introducimos un array de puntos) en norte y sur
         * El método recibe el susodicho array y devuelve un Map que divide los puntos entre "Norte" y "Sur"
         */
        fun localizacionGeograficaNS(arrPt: Array<Punto>): Map<String, List<Punto>> {
            val listaNorte: MutableList<Punto> = mutableListOf()
            val listaSur: MutableList<Punto> = mutableListOf()
            val mapGeografico: MutableMap<String, List<Punto>> = mutableMapOf()
            
            arrPt.forEachIndexed { i, punto -> if (punto.y >= 0) listaNorte.add(arrPt[i]) else listaSur.add(arrPt[i]) }
            //mapGeografico.map { "Norte" to listaNorte; "Sur" to listaSur}
            mapGeografico["Norte"] = listaNorte
            mapGeografico["Sur"] = listaSur
            return mapGeografico
        }
    }
}

fun main() {
    val p1 = Punto("ab",1,2)
    val p2 = Punto("cd",3,-1)
    val p3 = Punto("ef",2,1)
    val p4 = Punto("gh",5,-4)
    val arrayPuntos: Array<Punto> = arrayOf(p1,p2,p3,p4)

    println("Coordenadas del punto p1: ${p1.obtenerCoordenadas()}\n")
    println("${Punto.componenteDeVector(p1,p2)}\n")
    println("d(p1, p2) = ${Punto.distancia(p1,p2)}\n")

    /**
     * RA2.b,d,h
     */
    println("Punto p1 -> ${p1.obtenerCoordenadas()}\n" +
            "Punto p2 -> ${p2.obtenerCoordenadas()}\n" +
            "Componente 12: punto ${Punto.componenteDeVector(p1,p2)}\n")

    /**
     * RA6.a,c
     */
    println("Lista de puntos: ${arrayPuntos.contentToString()}\n" +
            "Localización Geográfica NS: ${Punto.localizacionGeograficaNS(arrayPuntos)}")
}