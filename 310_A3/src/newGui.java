import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;


public class newGui extends JFrame {
//	// Initialize required attributes and objects
//	// Used for processing input
//	handleInput inputHandler = new handleInput();
//	// Used to determine output based on processed input
//	determineOutput outputDeterminer = new determineOutput();
//	Personality p = new Personality();
//	questionAsker questionAsker = new questionAsker();
//	// Initialize booleans for determining steps in conversation
//	boolean genderchosen = false;
//	boolean turn = false;
//	boolean nameknown = false;
//	// Initialize names for identifying speakers
//	String username = "Human";
//	String chatbotname = "CHATBOTNAME";
//	String botoutput = "";
//	
//	String data = "";
//	String qdata = "";
//	String qresponse[] = new String[2];
//	String question[] = new String[2];
	public handleInput inputHandler = new handleInput();
	public determineOutput outputDeterminer = new determineOutput();
	public Personality p = new Personality();
	public questionAsker questionAsker = new questionAsker();
	public boolean genderchosen = false;
	public boolean turn = false;
	public boolean nameknown = false;
	public boolean profasker = false;
	public boolean profResponse = false;
	public boolean bye = false;
	public String username = "";
	public String chatbotname = "CHATBOTNAME";
	public String botoutput = "";
	public String data = "";
	public String qdata = "";
	public String qresponse[] = new String[2];
	public String question[] = new String[2];	
	public String userinput = "";
	public boolean askName = false;
	public boolean no = true;
	public String result;
	
	
	
	 
	//Typing Area:
	private JTextField txtEnter = new JTextField();
	
	//Instruction Area
	private JTextArea instruction = new JTextArea();
	private JTextArea instruction2 = new JTextArea();
	
	//Chat Area:
	public JTextArea txtChat = new JTextArea();
//	JScrollPane sp = new JScrollPane(txtChat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
//			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // scrolling 
//	 

	//private JLabel background;
	//private ImageIcon image;
	 
	
	public newGui() {
		//Frame Attributes:
		 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1460,1080);
		this.setVisible(true);
		this.setResizable(true);
		this.setLayout(null);
		this.setTitle("DateBot");
		
		// instructions Attributes:
		instruction.setLocation(1400,5);
		instruction.setSize(30, 900);
		instruction2.setLocation(15,910);
		instruction2.setSize(134, 30);
		
		//txtEnter Attributes:
		txtEnter.setLocation(150, 910);
		txtEnter.setSize(1245, 30);

		//txtEnter Action Event:
		txtEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				//String userinput2 = inputHandler.processInput(userinput);
			String userinput = txtEnter.getText();	
			
			    txtChat.append(" User: " + userinput + "\n");
			

				// Determine desired gender from user
				if (genderchosen == false) {
				    userinput = txtEnter.getText();
					String gender = inputHandler.checkGender(userinput);
					p = new Personality(gender);
					chatbotname = p.getName();
					txtChat.append("You are now on a date with a " + gender + " named " + chatbotname + ". (Press Enter To Continue)");
					genderchosen = true;
				}
				else if (nameknown == false) {
					txtChat.append("\n"+chatbotname + ": Hi! What's your name?");
					userinput = txtEnter.getText();
					nameknown=true;
					
				}
				
				else if (profasker == false) {
					int random = (int) (Math.random() * 2 + 1);
					if (random == 1)
						txtChat.append(chatbotname + ": It's nice to meet you, " + userinput + ". What do you do for a living?");
					else
						txtChat.append(chatbotname + ": That's a lovely name, " + userinput + ". So, what do you do for a living?");
						profasker = true;}
				
				else if (profResponse==false) {
					userinput = txtEnter.getText();
					txtChat.append(chatbotname + ": " + outputDeterminer.occupation(inputHandler.checkOccupation(userinput)));
					profResponse=true;}
				
				else if (genderchosen == true && nameknown == true && profasker == true && profResponse == true) {
					userinput = txtEnter.getText();
					data = inputHandler.parseInput(userinput);
					if (data.equals("nothing")) {
						question = questionAsker.ask();
						txtChat.append(chatbotname + ": " + question[0]);
						qdata = question[1];
						txtEnter.getText();
						userinput = txtEnter.getText();
						qresponse = inputHandler.parseQResponse(userinput.toString(), qdata, p);
						botoutput = questionAsker.afterAsk(qresponse, qdata);
						//txtChat.append(chatbotname + ": " + botoutput);
						if (userinput.endsWith("?")) {
							botoutput = outputDeterminer.respond(inputHandler.keywordConvert(qdata), p);
							txtChat.append(chatbotname + botoutput);
							botoutput = questionAsker.afterAsk(qresponse, qdata);
						}
						//botoutput = questionAsker.afterAsk(qresponse, qdata);
					}
//					else if(no==false) {
//						userinput = txtEnter.getText();
//						qresponse = inputHandler.parseQResponse(userinput.toString(), qdata, p);}
					else {
						botoutput = outputDeterminer.respond(data, p);
						txtChat.append(chatbotname + ": " + botoutput);
					}
					//txtChat.append(chatbotname + ": " + botoutput);
					
				}
//				else if (no=true) {
//					txtChat.append(chatbotname + ": " + botoutput);
//					no=false;
//				}
				
				
								
				
				
				txtEnter.setText("");

				
	
		
				
				
			}
		});
		
		//txtChat and instruction Attributes:
	   
		
		txtChat.setLocation(15, 5);
		txtChat.setSize(1380, 900);
		txtChat.setEditable(false);
		txtChat.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        txtChat.setLineWrap(true);
        txtEnter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        instruction2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        instruction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtChat.setBackground(Color.YELLOW);
        instruction.setBackground(Color.ORANGE);
        txtEnter.setBackground(Color.GREEN);
        instruction2.setBackground(Color.PINK);
       
		
 		
		//Add Items To Frame:
		this.add(txtEnter);
		this.add(txtChat);
		this.add(instruction);
		this.add(instruction2);

		 instruction.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n   P\n   R\n   E\n   S\n   S\n\n   E\n   N\n   T\n   E\n   R\n\n   T\n   O\n\n   S\n   E\n   N\n   D\n\n   M\n   E\n   S\n   S\n   A\n   G\n   E");
		instruction2.setText("Type MSG In Green Box:");
		txtChat.setText("Would you like to date a man or woman?");
	}

	public void botSay(String s){
		txtChat.append("ChatBot: " + s + "\n");
		
	}

	public static void main(String[] args){
		newGui bot = new newGui();
	}
	
}