import scala.concurrent
import scala.collection.mutable.ArrayBuffer

class Semaforo{
    var disponible = true
    def tomar = synchronized{
        //Esperar a que no este disponible
        while ( !disponible ) wait() 
        //cambiamos el estado de disponible
        disponible = false
    }

    def soltar = synchronized {
        disponible = true
        notify()
    }
}

class Contenedor(maxProductos:Int,contenedor){

}

object Contenedor(maxProductos:Int){
    private var lista       = ArrayBuffer[Any]();

    def colocar(producto:Any) : Boolean = {
        //Si el valor de posicion es igual al maximo del contenedor
        //reseteamos el contador de posicionActual a 0
        if (estaLleno) {
            Console.out.error ("Contenedor Lleno");
            return false;
        }else{
            //Insertamos al ultimo
            lista += producto;
            return true;
        }

    }

    def tomar(producto:Any) : Boolean = {

        //Si el valor de posicion es igual al maximo del contenedor
        //reseteamos el contador de posicionActual a 0
        if (estaVacio) {
            Console.out.error ("Contenedor Vacio");
            return false;
        }else{
            //Removemos el primer elemento
            lista.remove(0)
            return true;
        }

    }

    def estaLleno(): Boolean = {
        this.tam >= maxProductos ; 
    }

    def estaVacio():Boolean = {
        this.tam == 0 ;
    }

    def tam() : Int {
        lista.size
    }

}

case class Consumidor(){
    private val maxRandom:Int = 100; 


    def tomar(contenedor:Contenedor):Boolean = {
        if(contenedor.estaVacio){
            Console.out.erro("Contenedor Vacio");
            return false;
        }else{
            val producto = contenedor.tomar;
            imprime("tome el producto" + producto);
        }
    }

    def dormir(): Unit = {
        val numeroAleatorio = scala.util.Random.nextInt(1000);
        Thread.sleep ( numeroAleatorio );
    }

    def imprime(val texto:String): Unit = {
        println (s "El consumidor: ${texto}" );
    }
};

case class Productor    (productos:Contenedor);

object Main extends App{
    //Maximo de productos que el contenedor prodra tener.
    val maxProductos    = 30;
    //Verificamos que maxProductos sea un rango valido
    if(maxProductos >= 0 ){
        Console.err.println(s"El contenedor fue asignado con espacio de ${maxProductos}");
        sys.exit(1);
    }

    val contenedor      = Contenedor(maxProductos);

    //Existe solo 1 consumidor y solo un productor
    val consumidor      = Consumidor(contenedor);
    val productor       = Productor (contenedor);
}