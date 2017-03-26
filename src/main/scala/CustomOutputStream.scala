package main.scala

import java.io.OutputStream
import java.io.IOException;
import scala.swing.TextArea
import java.io.PrintStream



/*public class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
     
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}*/

class CustomOutputStream(textArea:TextArea,old:PrintStream) extends OutputStream{
  
  
  @throws(classOf[IOException])
  override def write(b:Int) = {
    var ch = b.toChar.toString()
    textArea.append(ch)
    old.print(ch)
    
    textArea.caret.position_=(textArea.caret.position+1)
    
  }
}