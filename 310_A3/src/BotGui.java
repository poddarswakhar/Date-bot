import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.control.TextField;


public class BotGui extends JFrame {
	// Initialize required attributes and objects
	// Used for processing input
	handleInput inputHandler = new handleInput();
	// Used to determine output based on processed input
	determineOutput outputDeterminer = new determineOutput();
	Personality p = new Personality();
	questionAsker questionAsker = new questionAsker();
	// Initialize booleans for determining steps in conversation
	boolean genderchosen = false;
	boolean turn = false;
	boolean nameknown = false;
	// Initialize names for identifying speakers
	String username = "Human";
	String chatbotname = "CHATBOTNAME";
	String botoutput = "";
	
	String data = "";
	String qdata = "";
	String qresponse[] = new String[2];
	String question[] = new String[2];
	
	
	
	//Typing Area:
	private JTextField txtEnter = new JTextField();
	
	//Chat Area:
	private JTextArea txtChat = new JTextArea();
	

	//private JLabel background;
	//private ImageIcon image;
	 
	
	public BotGui() {
		//Frame Attributes:
		 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Would you like to date a man or woman?");
		
	 
	/*	
		//Setting an image background
		
		image = new ImageIcon(getClass().getResource("C:/Users/ratho/Desktop/img.jpg"));
		
		background = new JLabel("",image,JLabel.CENTER);
		background.setBounds(0,0,600,600);
		add(background);
	*/
	
		//txtEnter Attributes:
		txtEnter.setLocation(2, 540);
		txtEnter.setSize(590, 30);

		//txtEnter Action Event:
		txtEnter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String userinput = txtEnter.getText();
				//String userinput2 = inputHandler.processInput(userinput);
				
			txtChat.append("You: " + userinput + "\n");
			
				data = inputHandler.parseInput(userinput);
				if(userinput.endsWith("?")) {
					botoutput = outputDeterminer.respond(inputHandler.keywordConvert(qdata), p);
					//System.out.println(chatbotname + ": " + botoutput);
					txtChat.append(chatbotname + ": " + botoutput + "\n");
					
				}
				
				
				// Determine desired gender from user
				if (genderchosen == false) {
					String gender = inputHandler.checkGender(userinput);
					p = new Personality(gender);
					chatbotname = p.getName();
					//System.out.println("You are now on a date with a " + gender + " named " + chatbotname + ".");
					txtChat.append("You are now on a date with a " + gender + " named " + chatbotname + ".");
					genderchosen = true;
				}
			
				
				
				
								
				
				
				txtEnter.setText("");

				
	/*			
				if (genderchosen == true && nameknown == true) {
					data = inputHandler.parseInput(userinput);
					if (data.equals("nothing")&&(!data.equals("bye"))) {
						qresponse[0] = "what";
						while (qresponse[0].equals("what")) {
							question = questionAsker.ask();
							//System.out.println(chatbotname + ": " + question[0]);
							txtChat.append(chatbotname + ": " + question[0]);
							qdata = question[1];
							userinput = inputHandler.getUserInput();
							qresponse = inputHandler.parseQResponse(userinput, qdata, p);
						}
						if (userinput.endsWith("?")) {
							botoutput = outputDeterminer.respond(inputHandler.keywordConvert(qdata), p);
							//System.out.println(chatbotname + ": " + botoutput);
							txtChat.append(chatbotname + ": " + botoutput);
						}
						botoutput = questionAsker.afterAsk(qresponse, qdata);
					} else {
						botoutput = outputDeterminer.respond(data, p);

					}
					//System.out.print(chatbotname + ": " + botoutput);
					txtChat.append(chatbotname + ": " + botoutput);
				}
				
				// Determine desired gender from user
				if (genderchosen == false) {
					String gender = inputHandler.checkGender(userinput);
					p = new Personality(gender);
					chatbotname = p.getName();
					//System.out.println("You are now on a date with a " + gender + " named " + chatbotname + ".");
					txtChat.append("You are now on a date with a " + gender + " named " + chatbotname + ".");
					genderchosen = true;
				}
				
				
				// Determining users name, occupation from user
				if (nameknown == false) {
					//System.out.println(chatbotname + ": Hi! What's your name?");
					txtChat.append(chatbotname + ": Hi! What's your name?");
					//System.out.println(username + ":");
					txtChat.append(username + ":");
					userinput = inputHandler.getUserInput();
					username = inputHandler.parseName(userinput).substring(0, 1).toUpperCase()
							+ inputHandler.parseName(userinput).substring(1);
					nameknown = true;
					int random = (int) (Math.random() * 2 + 1);
					if (random == 1)
						//System.out.println(
								//chatbotname + ": It's nice to meet you, " + username + ". What do you do for a living?");
						txtChat.append(chatbotname + ": It's nice to meet you, " + username + ". What do you do for a living?");
					else
						//System.out.println(
								//chatbotname + ": That's a lovely name, " + username + ". So, what do you do for a living?");
						txtChat.append(chatbotname + ": That's a lovely name, " + username + ". So, what do you do for a living?");
					//System.out.println(username + ":");
						txtChat.append(username + ":");
					userinput = inputHandler.getUserInput();
					//System.out.println(
							//chatbotname + ": " + outputDeterminer.occupation(inputHandler.checkOccupation(userinput)));
						txtChat.append(chatbotname + ": " + outputDeterminer.occupation(inputHandler.checkOccupation(userinput)));
				}
				
			/*	
				if(uText.contains("hi")){
					String response = "You are on a blind date. Would you like to date a man or a woman?";
					botSay(response);
				}
				else{
					int decider = (int) (Math.random()*3+1);
					if(decider == 1){
						botSay("I didn't get that");
					}
					else if(decider == 2){
						botSay("Please rephrase that");
					}
					else if(decider == 3){
						botSay("???");
					}
				}
			*/	
		
				
				
			}
		});
		
		//txtChat Attributes:
		txtChat.setLocation(15, 5);
		txtChat.setSize(560, 510);
		txtChat.setEditable(false);
 		
		//Add Items To Frame:
		this.add(txtEnter);
		this.add(txtChat);
	}

	public void botSay(String s){
		txtChat.append("ChatBot: " + s + "\n");
	}

	public static void main(String[] args){
		BotGui bot = new BotGui();
	}
	
}