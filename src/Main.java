import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	JButton Restart;
	JButton[] Letters;
	ImageIcon Hangmanimage;
	JLabel Hangman;
	JLabel Misses;
	JLabel[] Guess;
	
	//Any word as long as it is in capital letters can be added here and become part of the game, chosen at random
	String[] words = {"HANGMAN","AMERICA","KENYA","KINGKONG","HALO","FOOTBALL","DRAGON","TELEVISION","BLACKHOLE"}; 
	
	// this class displays the GUI
	public Main(int word){
		super("Hang Man Game");
		
		Restart = new JButton("Restart");
		Hangmanimage = new ImageIcon("hang0.gif");
		Hangman = new JLabel(Hangmanimage);
		Misses = new JLabel("8 lives remaining");
		Letters = new JButton[26];
		String answer = words[word]; 
		char[] charArray = answer.toCharArray();
		Guess = new JLabel[charArray.length];
		
		HangmanLogic logic = new HangmanLogic(Letters, Guess, Hangman, Misses, charArray);
		
		Box topbox = Box.createHorizontalBox();
		topbox.add(Restart, BorderLayout.CENTER);
		
		
		// this button restarts the program while choosing one of the words in the String array at random
		Restart.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						Main.this.dispose();
						
						Random random = new Random();
						int high = words.length, low = 0;
						int word = random.nextInt(high) + low;
						
						Main main = new Main(word);
						main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						main.setSize(500, 400);
						main.setVisible(true);
					}
				});
		
		Box midbox = Box.createVerticalBox();
		Box midbox1 = Box.createVerticalBox();
		Box midbox2 = Box.createHorizontalBox();
		
		midbox1.add(Hangman, BorderLayout.CENTER);
		
		for (int i = 0; i < charArray.length; i++) {
			Guess[i] = new JLabel("_ ");
			midbox2.add(Guess[i]);
		}
		
		midbox.add(midbox1, BorderLayout.NORTH);
		midbox.add(midbox2, BorderLayout.SOUTH);
		midbox.add(Misses, BorderLayout.SOUTH);
		
		Box bottombox = Box.createVerticalBox();
		Box bottombox1 = Box.createHorizontalBox();				
		Box bottombox2 = Box.createHorizontalBox();
		Box bottombox3 = Box.createHorizontalBox();
		
		for (int i = 0; i < 26; i++) {
			char ch = (char) ('A' + i);
			Letters[i] = new JButton(Character.toString(ch));
			Letters[i].addActionListener(logic);
			if(i < 9){
				bottombox1.add(Letters[i]);
			}else if(i < 17){
				bottombox2.add(Letters[i]);
			}else{
				bottombox3.add(Letters[i]);
			}
		}
		
		bottombox.add(bottombox1);
		bottombox.add(bottombox2);
		bottombox.add(bottombox3);
		
		add(topbox, BorderLayout.NORTH);
		add(midbox, BorderLayout.CENTER);
		add(bottombox, BorderLayout.SOUTH);
	}
	
	public static void main(String args[]){
		Main main = new Main(0);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(500, 400);
		main.setVisible(true);
	}
}

