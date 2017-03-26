package main.scala

//Singleton de productor
object Productor extends Persona("Productor"){
    def colocar(contenedor:Contenedor, producto:Any):Unit = {
      
        
        //Verificamos si esta vacio el contenedor, antes de tomar un elemento
        cambiaEstado(trabajando = "Si"); 
        if(contenedor.estaLleno){
            //Informamos que el contenedor
            cambiaEstado(durmiendo = "Si");
            dormir();
            
        }else{
            if(contenedor.colocar(producto)){              
            	cambiaEstado(trabajando = "Si", enContenedor = "Si")
            }else{
              dormir()
              colocar(contenedor, producto);
            }
            
          	
            
            cambiaEstado(trabajando = "Si");
            trabajar(); 
        }
    }
};