import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

import javax.swing.*;


public class TextEditor extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -3221423848806431762L;
    private Menu menu;
    private JTextArea textArea;
    
    public TextEditor() {
	this.menu = new Menu();
	this.setJMenuBar(menu);
	this.textArea = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(this.textArea);
	this.setBounds(450, 200, 500, 500);
//	this.add(textArea);
	this.add(scrollPane);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
	TEController controller = new TEController();
    }

    private class TEController implements ActionListener {

	public TEController() {
	    TextEditor.this.menu.itemNew.addActionListener(this);
	    TextEditor.this.menu.itemOpen.addActionListener(this);
	    TextEditor.this.menu.itemSave.addActionListener(this);
	    TextEditor.this.menu.itemPrint.addActionListener(this);
	    TextEditor.this.menu.itemCut.addActionListener(this);
	    TextEditor.this.menu.itemCopy.addActionListener(this);
	    TextEditor.this.menu.itemPaste.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    String actionCommand = e.getActionCommand();

	    if(actionCommand.toLowerCase().equals("new")) {
		TextEditor.this.textArea.setText("");
	    }
	    else if(actionCommand.toLowerCase().equals("open")) {
		String fileContents = openFile();
		if(fileContents != null) {
		    TextEditor.this.textArea.setText(fileContents);
		}
		
	    }
	    else if(actionCommand.toLowerCase().equals("save")) {
		saveFile(TextEditor.this.textArea.getText());
	    }
	    else if(actionCommand.toLowerCase().equals("print")) {
		try {
		    TextEditor.this.textArea.print();
		} catch (PrinterException e1) {
		    
		    e1.printStackTrace();
		}
	    }
	    else if(actionCommand.toLowerCase().equals("cut")) {
		TextEditor.this.textArea.cut();
	    }
	    else if(actionCommand.toLowerCase().equals("copy")) {
		TextEditor.this.textArea.copy();
	    }
	    else if(actionCommand.toLowerCase().equals("paste")) {
		TextEditor.this.textArea.paste();
		
	    }
	}

	private String openFile() {
	    JFileChooser fileChooser = new JFileChooser();
	    String fileContents = "";
	    int openDialog = fileChooser.showOpenDialog(null);
	    if(openDialog == JFileChooser.APPROVE_OPTION) {

		File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
		
		StringBuilder stringBuilder = new StringBuilder();
		FileReader fileReader = null; 
		BufferedReader buffReader = null;
		try {
		    fileReader = new FileReader(file);

		    buffReader = new BufferedReader(fileReader);

		    fileContents = buffReader.readLine();

		    while(fileContents != null) {
			stringBuilder.append(fileContents);
			stringBuilder.append(System.lineSeparator());

			fileContents = buffReader.readLine();
		    }

		    fileContents = stringBuilder.toString();
		    fileReader.close();
		    buffReader.close();
		    

		}
		catch(FileNotFoundException ex) {
		    ex.printStackTrace();

		}
		catch(IOException ex) {
		    ex.printStackTrace();

		}
	    }
	    else {
		
		return null;
	    }
	    return fileContents;
	}
	
	private void saveFile(String fileContents) {
	    
	    JFileChooser fileChooser = new JFileChooser();
	    int showDialog = fileChooser.showSaveDialog(null);
	    
	    if(showDialog == JFileChooser.APPROVE_OPTION) {
		File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
		
		try{
		    
		    FileWriter fileWriter = new FileWriter(file);
		    
		    BufferedWriter buffWriter = new BufferedWriter(fileWriter);
		    
		    buffWriter.write(fileContents);
		    
		    buffWriter.flush();
		    buffWriter.close();
		    
		}
		catch(FileNotFoundException ex) {
		    ex.printStackTrace();
		}
		catch(IOException ex) {
		    ex.printStackTrace();
		}
	    }
	}

    }

}
