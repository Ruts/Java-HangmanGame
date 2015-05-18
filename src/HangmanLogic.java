import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

// this class contains the logic behing the hangman game
public class HangmanLogic implements ActionListener {
	JButton Restart;
	JButton[] Letters;
	ImageIcon Hangmanimage;
	JLabel Hangman;
	JLabel[] Guess;
	JLabel Misses;
	int lives = 8, hangmangif = 1, win = 0, perform = 0;
	char[] CharArray;
	int[] Array;
	
	public HangmanLogic(JButton[] letters, JLabel[] guess, JLabel hangman, JLabel misses, char[] charArray) {
		Letters = letters;
		Guess = guess;
		Misses = misses;
		Hangman = hangman;
		CharArray = charArray;
	}

	// this listener which sup which button is presses and either adds the letter to the blank spaces or reduces the number of lives
	public void actionPerformed(ActionEvent event) {
		for (int i = 0; i < Letters.length; i++) {
			if (event.getSource() == Letters[i]) {
				Letters[i].setEnabled(false);
				perform = 0;
				
				for(int j = 0; j < CharArray.length;j++){
					if(((char)(i+65)) == CharArray[j]){
						Guess[j].setText(""+CharArray[j]);
						win++;
						perform++;
					}
					if(perform == 0 && j == CharArray.length - 1){
						Hangmanimage = new ImageIcon("hang"+hangmangif+".gif");
						Hangman.setIcon(Hangmanimage);
						hangmangif++;
						lives--;
						Misses.setText(lives + " lives remaining");
						if(lives == 0){
							Misses.setText("No lives remaining. GAME OVER");
							
							for(int k = 0; k < Letters.length; k++){
								Letters[k].setEnabled(false);
							}
						}
					}

					if(win == CharArray.length){
						Misses.setText("You Have Won The Game");
						
						for(int k = 0; k < Letters.length; k++){
							Letters[k].setEnabled(false);
						}
					}
				}
			}
		} 
	}
}