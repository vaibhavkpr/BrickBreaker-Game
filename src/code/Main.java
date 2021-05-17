package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Main {
	
	//Create JFrame
	static JFrame window = new JFrame("Brick Breaker");
	static JPanel menuPanel = new JPanel();
	static JLabel background = new JLabel();
	static JButton playButton = new JButton();
	static JButton instructionsButton = new JButton();
	static JButton exitButton = new JButton();
		
	//create images as icons
	static ImageIcon dimage = new ImageIcon("Resources/background.jpg");	
	static ImageIcon playimage = new ImageIcon("Resources/Play.png");
	static ImageIcon helpimage = new ImageIcon("Resources/Help.png");
	static ImageIcon exitimage = new ImageIcon("Resources/Exit.png");
	
	//Menu Screen
	public static void Menu(){		
						
		//Create Menu Frame
		window.setBounds(10, 10, 700, 600);
		window.setResizable(false);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("Resources/icon.png");
		window.setIconImage(icon.getImage());
		window.add(menuPanel);
						
		//Play Button	
		ImageIcon playimage = new ImageIcon("Resources/Play.png");
		Image img = playimage.getImage() ;  
		Image playimg = img.getScaledInstance( 80, 80,  java.awt.Image.SCALE_SMOOTH ) ;  
		playimage.setImage(playimg);
		
		playButton.setIcon(playimage);
		playButton.setBounds(200,50,300,100);		 
		playButton.setVisible(true);
		playButton.setBackground(Color.black);
		menuPanel.add(playButton);		
		playButton.addActionListener (new Action1());
		 		 
		//Instructions Button
		ImageIcon helpimage = new ImageIcon("Resources/Help.png");
		Image img2 = helpimage.getImage() ;  
		Image helpimg = img2.getScaledInstance( 100, 90,  java.awt.Image.SCALE_SMOOTH ) ;  
		helpimage.setImage(helpimg);
		
		instructionsButton.setIcon(helpimage);
		instructionsButton.setBounds(200,200,300,100);
		instructionsButton.setBackground(Color.black);
		menuPanel.add(instructionsButton);		
		instructionsButton.addActionListener (new Action2());
		instructionsButton.setVisible(true);
		
		//Exit Button
		ImageIcon exitimage = new ImageIcon("Resources/Exit.png");
		Image img3 = exitimage.getImage() ;  
		Image exitimg = img3.getScaledInstance( 80, 80,  java.awt.Image.SCALE_SMOOTH ) ;  
		exitimage.setImage(exitimg);
		
		exitButton.setIcon(exitimage);
		exitButton.setBounds(200,350,300,100);
		exitButton.setBackground(Color.black);
		menuPanel.add(exitButton);		 
		exitButton.addActionListener (new Action3());
		exitButton.setVisible(true);
		
		//label for background
		background.setBounds(0,0, 700, 600);
		background.setIcon(dimage);
		menuPanel.add(background);
		
		//panel
		menuPanel.setBounds(0,0, 700, 600);
		menuPanel.setBackground(Color.darkGray);	
		menuPanel.setVisible(true);
		
	}
		
	
	public static class Action1 implements ActionListener{
		
		//When clicked
		public void actionPerformed (ActionEvent e){
			
			//clear buttons			
			window.remove(playButton);
			window.remove(instructionsButton);
			window.remove(exitButton);
			
			//clear window and go to game
			window.remove(menuPanel);
			window.remove(background);
			window.dispose();
			
			//Game
			createGame();
			
		}
	}
	
	public static class Action2 implements ActionListener{
		
		//When Clicked
		public void actionPerformed (ActionEvent e){			
			help();			
		}
	}
	
	public static class Action3 implements ActionListener{
		
		//When CLicked
		public void actionPerformed (ActionEvent e){
			
			//clear buttons
			window.remove(playButton);
			window.remove(instructionsButton);
			window.remove(exitButton);
			window.remove(background);
			window.remove(menuPanel);
			window.remove(window);
			
			//clear window
			window.dispose();
			
		}
	}

	//Play
	public static void createGame(){
		
		Game game = new Game();
		
		//Set properties for JFrame
		window.setBounds(10, 10, 700, 600);
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(game);
		ImageIcon icon = new ImageIcon("icon.png");
		window.setIconImage(icon.getImage());
		window.setLocationRelativeTo(null);
		
	}
	
	//Instructions
	public static void help(){
		
		//display instructions as a popup
		JOptionPane.showMessageDialog(null, "Break all the bricks without losing the ball! Use arrow keys to control the paddle.","Help", JOptionPane.PLAIN_MESSAGE);
	}
	
	//Main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Menu();		
		
	}

}
