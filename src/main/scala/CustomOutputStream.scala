package main.scala

import java.io.OutputStream
import java.io.IOException;
import scala.swing.TextArea
import java.io.PrintStream



class CustomOutputStream(textArea:TextArea,old:PrintStream) extends OutputStream{
  
  
  @throws(classOf[IOException])
  override def write(b:Int) = {
    var ch = b.toChar.toString()
    textArea.append(ch)
    old.print(ch)
    
    textArea.caret.position_=(textArea.caret.position+1)
    
  }
}
