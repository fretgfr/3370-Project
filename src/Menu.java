import java.awt.event.*;

import javax.swing.*;

/**
 * 
 * @author Brett Demoe
 *
 */
public class Menu extends JMenuBar{
    /**
     * 
     */
    private static final long serialVersionUID = -2297541010052515708L;
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenuItem itemNew = new JMenuItem("New");
    JMenuItem itemOpen = new JMenuItem("Open");
    JMenuItem itemSave = new JMenuItem("Save");
    JMenuItem itemPrint = new JMenuItem("Print");
    JMenuItem itemCut = new JMenuItem("Cut");
    JMenuItem itemCopy = new JMenuItem("Copy");
    JMenuItem itemPaste = new JMenuItem("Paste");
    
    /**
     * Menu Constructor. 
     */
    public Menu() {
	
	this.fileMenu.add(itemNew);
	this.fileMenu.add(itemOpen);
	this.fileMenu.add(itemSave);
	this.fileMenu.add(itemPrint);
	
	this.editMenu.add(itemCut);
	this.editMenu.add(itemCopy);
	this.editMenu.add(itemPaste);
	
	this.add(fileMenu);
	this.add(editMenu);
	
    }
}
