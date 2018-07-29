package framePackage;

import java.io.StringWriter;

import javax.swing.JTextArea;

public class LogTextPane extends JTextArea{
	private static final long serialVersionUID = -7663940864168854669L;
	
	private StringWriter logStringWriter = new StringWriter();
	
	public LogTextPane() {
		writeToLog("Log Started");
	}
	
	public void writeToLog(String message) {
		logStringWriter.write(message+"\n-------------------\n");
		this.setText(logStringWriter.toString());
	}
}
