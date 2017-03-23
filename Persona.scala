abstract class Persona protected( nombrePersona:String = "Persona",
                                   colorTexto:String = "\u001B[31m", 
                                   maxRandom:Int = 100)
{
    
    protected def cambiaEstado(trabajando:String = "No", durmiendo:String = "No", enContenedor:String = "No") : Unit = {
            //println("\n\n\n\n\n\n\n\n\n\n\n")
      
          println( s"${colorTexto}${nombrePersona}: " )
          println( s"${colorTexto}Trabajando: ${trabajando}" )
          println( s"${colorTexto}Durmiendo:	${durmiendo}" )
          println( s"${colorTexto}Entrando en el contenedor: ${enContenedor}" )
          println( )   
      

    }
    
    protected def trabajar() : Unit = {
     		  val numeroAleatorio = scala.util.Random.nextInt(maxRandom);
          Thread.sleep ( numeroAleatorio );
     }
    
    protected def dormir(): Unit = {
        cambiaEstado(durmiendo = "Si")

        val numeroAleatorio = scala.util.Random.nextInt(maxRandom);
        Thread.sleep ( numeroAleatorio );
        
        cambiaEstado(trabajando = "No")
    }
}