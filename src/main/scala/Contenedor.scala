package main.scala

import scala.collection.mutable.ArrayBuffer
//Semaforo de java
import java.util.concurrent.Semaphore;

class Contenedor(maxProductos:Int){
    type Producto = Any;
    val semaforo = new Semaphore(1,true);
    
    private var lista       = ArrayBuffer[Producto]();
    
    
    //Colocar un elemento al final de la lista
    def colocar(producto:Producto):Boolean = {
      if(semaforo.tryAcquire()){
        
    	  lista += producto;
    	  Thread.sleep(1000);
    	  semaforo.release();
    	  return true
      }else{
        println("No puedes colocar!!");
        return false
      }
    }
    
    //Removemos el primer elemento y devolverlo
    def tomar:Boolean =  {
      if(semaforo.tryAcquire()){        
    	  lista.remove(0);
    	  semaforo.release();
    	  return true;
      }else{
        println("No puedes tomar!!")
        return false;
      }
      
    }
    
    //Determinar si la lista a superado el maximo de elementos permitidos
    def estaLleno = lista.size > maxProductos -1
    
    //Determinar si la lista esta vacia
    def estaVacio = lista.isEmpty;
    
    def tamMax = maxProductos
}

object Contenedor{
	private lazy val _instancia = defineContenedor(tam);
	var tam = 30
	
	
	def instancia:Contenedor =  {
	    
		  _instancia;
	}
  
  private def defineContenedor(tam:Int):Contenedor = {
    return new Contenedor(tam);
  }
}


