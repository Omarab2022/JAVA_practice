import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.Scanner;

public class texteditor extends JFrame implements ActionListener {

	JTextArea textarea; // create zone de text
	JScrollPane scrollPane; // scroll for text zone
	JSpinner fontsize ;// spinner for choosing font size

	JLabel label ;

	JButton colorbutton ;

	JComboBox fontbox;
	JMenuBar menuBar;
	JMenu filemenu ;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;

	//-----------------------------------------------------------------------------//
	texteditor(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Text Editor");
		this.setSize(500,500);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);  // display frame in the center

		//--------------------------------- Text area --------------------------------------------//

		textarea = new JTextArea();
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true); // mli kaywsl l l7ad kayrj3 lstar
		textarea.setFont(new Font("Arial",Font.PLAIN,20));

		//------------------------------------ Scroll -----------------------------------------//

		scrollPane = new JScrollPane(textarea);
		scrollPane.setPreferredSize(new Dimension(450,450));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		//------------------------------------- Choose font ----------------------------------------//

		fontsize = new JSpinner();
		fontsize.setPreferredSize(new Dimension(50,25));
		fontsize.setValue(40);
		//add change listener for changing font size :
		fontsize.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				textarea.setFont(new Font(  textarea.getFont().getFamily(),//to get font
						                    Font.PLAIN,
						                    (Integer) fontsize.getValue())); //get value of spinner
			}
		});

		//-------------------------------  label ----------------------------------------------//

		label= new JLabel(" Font Size :");

		//------------------------------------ color button -----------------------------------------//

		colorbutton =new JButton("Color");
		colorbutton.addActionListener(this);

		//------------------------------------ choose font -----------------------------------------//

		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontbox = new JComboBox(fonts);
		fontbox.addActionListener(this);
		fontbox.setSelectedItem("Arial");


		//---------------------------------- menu bar -------------------------------------------//

		menuBar = new JMenuBar();
		filemenu = new JMenu("File");

		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem(" Save");
		exitItem = new JMenuItem(" Exit");

		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);

		filemenu.add(openItem);
		filemenu.add(saveItem);
		filemenu.add(exitItem);
		menuBar.add(filemenu);
		this.add(menuBar);


		//-----------------------------------------------------------------------------//

		this.add(label);
		this.add(fontsize);
		this.add(colorbutton);
		this.add(fontbox);
		this.add(scrollPane);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == colorbutton) {
			JColorChooser colorChooser = new JColorChooser(); // whene we clique to choose color
			Color color = colorChooser.showDialog(null , " choose a color " ,Color.BLACK);
			textarea.setForeground(color);

		}

		if (e.getSource() == fontbox) {
			textarea.setFont(new Font((String) fontbox.getSelectedItem(),Font.PLAIN, (Integer) fontsize.getValue()));
		}

		if (e.getSource() == openItem) { //open file and display his content in text area

			JFileChooser fileChooser =new JFileChooser(); //create file chooser to chose file
			fileChooser.setCurrentDirectory(new File(".")); // set path to save file

			//set filter
			FileNameExtensionFilter filter = new FileNameExtensionFilter( "text" , "txt");
			fileChooser.setFileFilter(filter);

			int response  = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION){
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				Scanner filein = null;
				try {
					filein = new Scanner(file);
					if (file.isFile()) {
						while (filein.hasNextLine()){
							String line =filein.nextLine()+"\n";
							textarea.append(line);
						}
					}
				} catch (FileNotFoundException ex) {
					throw new RuntimeException(ex);
				}
				finally {
					filein.close();
				}
			}


		}
		if (e.getSource() == saveItem) {
			// we gonna save text in a file :

			JFileChooser fileChooser =new JFileChooser(); //create file chooser to chose file
			fileChooser.setCurrentDirectory(new File(".")); // set path to save file

			int responce =fileChooser.showSaveDialog(null);   // responce = 0 or 1

			if (responce == JFileChooser.APPROVE_OPTION /* if response = 0 */ ) {
				File file ; // create new file
				PrintWriter fileout = null;

				file = new File(fileChooser.getSelectedFile().getAbsolutePath()); // create file in the path that we coose
				try {

					fileout = new PrintWriter(file);
					fileout.println(textarea.getText());
					
				} catch (FileNotFoundException ex) {
					throw new RuntimeException(ex);
				}
				finally {
					fileout.close();
				}

			}


		}
		if (e.getSource() == exitItem) {

			System.exit(0);
		}
	}
}
