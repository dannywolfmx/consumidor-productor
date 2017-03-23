package discoteca

import scala.collection.mutable._
import scala.concurrent

abstract class Semaforo(numeroTickets:Int, colaProcesos:mutable.Queue[Proceso]){
    def down():Unit = {
        colaProcesos.lenght match{
            case 0              => wait()
            case _ if(x != 0)   => {
                println("Salio el proceso " + (colaProcesos.dequeue).nombre)
            }
        }

        if(colaProcesos.lenght == 0){
            wait()
        }if else()
    }
    def up():Unit
}