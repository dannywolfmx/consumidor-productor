package main.scala

//Singleton de consumidor


object Consumidor extends Persona("Consumidor","\u001B[34m"){

    def tomar(contenedor:Contenedor):Unit = {
          //Verificamos si esta vacio el contenedor, antes de tomar un elemento
          
          
          cambiaEstado(trabajando = "Si"); 
          
          if(contenedor.estaVacio){
              //Informamos que el contenedor
              cambiaEstado(durmiendo = "Si");
              
              dormir()
              
              tomar(contenedor)
          }else{           
                
            	  
            	  val producto = contenedor.tomar;
            	  if(producto){            	    
            		  cambiaEstado(trabajando = "Si", enContenedor = "Si")
            	  }else{
            	    dormir();
            	    tomar(contenedor)
            	  }
            	  
            	  cambiaEstado(trabajando = "Si");
            	  trabajar();
          }
    }
};