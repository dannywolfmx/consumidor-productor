import scala.concurrent
import scala.concurrent.{Await, Future, Promise}
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main extends App{
      
    //Creamos un alias del tipo Any para que sea mas facil de leer
    type Producto = Any;
    
    //Maximo de productos que el contenedor prodra tener.
    val maxProductos    = 30;
    
    //Singleton del contenedor 
    Contenedor.tam = maxProductos;
    val contenedor = Contenedor.instancia
    
    println(s"contenedor max ${contenedor.tamMax}")
    
    //Existe solo 1 consumidor y solo un productor
    val consumidor      = Consumidor;
    val productor       = Productor ;
    
    
    
    var bufferDeProductos = ArrayBuffer[Producto]();
    
    //Verificamos que maxProductos sea un rango valido
    if(maxProductos <= 0 ){
        Console.err.println( s"El contenedor fue asignado con espacio de ${maxProductos}" );
        sys.exit(1);
    }
    
    println(s"Ok dame los ${contenedor.tamMax} productos (Separados por enter)");
    
    for(i <- 0 until maxProductos){
      bufferDeProductos += scala.io.StdIn.readLine( s"Producto ${i}: " );
    }
    
    println(s"Estos son los productos que insertaste \n ${bufferDeProductos}");    
    
    val productores = Future{
    	for(a <- bufferDeProductos){
    		productor.colocar(contenedor,bufferDeProductos.remove(0));  
    	}
    }
    
    val consumidores = Future{      
    	for(a <- 0 until maxProductos){	consumidor.tomar(contenedor);	}
    }
    
    Await.ready(productores,Duration.Inf);
    Await.ready(consumidores,Duration.Inf);
    
}