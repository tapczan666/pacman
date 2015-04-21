package pacman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.*;

import pacman.Pacman;
import pacman.TopTen;

/**
 * Klasa tworz¹ca menu..
 * @author Maciek Wójcik & Kuba Wilkowski
 *
 */

public class Menu extends JPanel{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Zmienne u¿ywane do przechowywania u¿ywanych obiektów klas Game, Pacman, TopTen
	 */
		Game myGame;
		Pacman pacman;
		TopTen topTen;
		
		/**
		 * Zmienna u¿ywana do przechowywania t³a.
		 */
		private Image background;
		
		/**
		 * Zmienna u¿ywana do przechowywania danych z formularzy
		 */
		String Name = "";
		String map = "1";
		
		/**
		 * Zmienna u¿ywana do przechowywania rozmiaru guzików
		 */
		private Dimension dimButton = new Dimension(120,40);
		
		/**
		 * Obiekty klasy JButton - guziki.
		 */
		private JButton play;
		private JButton highscores;
		private JButton options;
		private JButton exit;
		private JButton back;
		private JButton start;
		private JButton delete;
		private JButton save;
		private JButton back_high;
		private JButton proceed;
		private JTextField nameField;
		private JTextField mapField;
		
		/**
		 * Obiekty odpowiedzilne za rozmieszczenie elementów w Menu
		 */
		private GridBagLayout layout;
		private GridBagConstraints c;
		private GridBagConstraints c_h;
		
		/**
		 * Konstruktor klasy Menu. Inicjuje zmienne i ustawia AcitonListenerów.
		 * @param game - obiekt klasy dziedzicz¹cej po JFrame, na którym rysowane jest menu
		 */
		public Menu(Game game){
			myGame=game;
			topTen = new TopTen();
			
			this.background = new ImageIcon("src\\img\\open.png").getImage();
			
		    this.layout = new GridBagLayout();
		    this.c = new GridBagConstraints();
		    this.c_h = new GridBagConstraints();
		    
		    this.play = new JButton("PLAY");
		    this.highscores = new JButton("HIGHSCORES");
		    this.options = new JButton("OPTIONS");
		    this.exit = new JButton("EXIT");
		    this.back = new JButton("BACK");
		    this.start = new JButton("I GET IT");
		    this.delete = new JButton("DELETE");
		    this.save = new JButton("SAVE");
		    this.back_high = new JButton("BACK");
		    this.proceed = new JButton("PROCEED");
		    this.nameField= new JTextField("Name");
		    this.mapField= new JTextField("Map number");

			/**
			 * ActionListener guzika PLAY
			 */
		    this.play.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        Menu.this.removeAll();
		        Menu.this.repaint();
		        Menu.this.revalidate();
		        Menu.this.instructions();
		      }
		    });
		    
			/**
			 * ActionListener guzika HIGHSCORES
			 */
		    this.highscores.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	Menu.this.removeAll();
		    	Menu.this.repaint();
		    	Menu.this.revalidate();
		    	Menu.this.highscores();
		      }
		    });
		    
			/**
			 * ActionListener guzika OPTIONS
			 */
		    this.options.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        Menu.this.removeAll();
		        Menu.this.repaint();
		        Menu.this.revalidate();
		        Menu.this.options();
		      }
		    });
		    
			/**
			 * ActionListener guzika EXIT
			 */
		    this.exit.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        System.exit(0);
		      }
		    });
		    
			/**
			 * ActionListener guzika BACK(W OPCJACH)
			 */
		    this.back.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        Menu.this.removeAll();
		        Menu.this.repaint();
		        Menu.this.revalidate();
		        Menu.this.setButtons();
		      }
		    });
		    
			/**
			 * ActionListener guzika BACK(W HIGSHSCORES)
			 */
		    this.back_high.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        Menu.this.removeAll();
		        Menu.this.repaint();
		        Menu.this.revalidate();
		        Menu.this.setButtons();
		      }
		    });
		    
			/**
			 * ActionListener guzika START
			 */
		    this.start.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        Menu.this.removeAll();
		        Menu.this.repaint();
		        Menu.this.revalidate();
		        Menu.this.Name();
		        
		      }
		    });
		    
			/**
			 * ActionListener guzika PROCEED
			 */
		    this.proceed.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        Menu.this.removeAll();
		        Menu.this.repaint();
		        Menu.this.revalidate();

		        Name = Menu.this.nameField.getText();

		        Menu.this.startGame();
		        
		      }
		    });
		    
			/**
			 * ActionListener guzika SAVE
			 */
		    this.save.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		        Menu.this.removeAll();
		        Menu.this.repaint();
		        Menu.this.revalidate();

		        map = Menu.this.mapField.getText();

		        Menu.this.removeAll();
		        Menu.this.repaint();
		        Menu.this.revalidate();
		        Menu.this.setButtons();

		      }
		    });
		    
			/**
			 * ActionListener guzika DELETE
			 */
		    this.delete.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e)
		      {

		        try {
					topTen.delete();
				} catch (IOException e1) {
					System.out.println("Plik nie istnieje");
					System.exit(0);
				}
		        Menu.this.removeAll();
		        Menu.this.repaint();
		        Menu.this.revalidate();
		        Menu.this.setButtons();
		        Menu.this.removeAll();
			    Menu.this.repaint();
			    Menu.this.revalidate();
		        Menu.this.highscores();
		        
		      }
		    });

		    setButtons();
		    setLayout(this.layout);
		}

		/**
		 * Ekran, który pobiera imiê gracza
		 */
		protected void Name() {
			String tempName = new String("Please enter your name");
			JLabel tempJL = new JLabel(tempName);
			this.layout.setConstraints(tempJL, this.c);
			tempJL.setFont(new Font("Arial", 1, 30));
			tempJL.setForeground(Color.black);
		    this.layout.setConstraints(tempJL, this.c);
		    add(tempJL);
			add(this.nameField);
			add(this.proceed);
		}

		/**
		 * Ekran opcji, gdzie mo¿na wybraæ dowoln¹ mapê
		 */
		protected void options() {
			String tempMap = new String("Please enter map number");
			JLabel tempJL = new JLabel(tempMap);
			this.layout.setConstraints(tempJL, this.c);
			tempJL.setFont(new Font("Arial", 1, 30));
			tempJL.setForeground(Color.black);
		    this.layout.setConstraints(tempJL, this.c);
		    add(tempJL);
		    add(this.mapField);
			add(this.save);
			add(this.back);
		}

		/**
		 * Ekran, gdzie s¹ wyœwietlane instrukcje
		 */
		protected void instructions() {
			int fontSize; 
			String[] str = new String[10];
			str[0] = "Hello World!";
			str[1] = "This is PacMan. The one and only game in the universe that is ";
			str[2] = "immortal.Rules are simple: You are the yellow guy and";
			str[3] = "your objective is to eat all the pellets(orange points)";
			str[4] = "Red ghosts are the bad guys. If you touch them, you loose life. ";
			str[5] = "But if you eat the big pellet the ghoststemporarily turn ";
			str[6] = "blue and you can eat them. Use the arrows the move. ";
			str[7] = "There are some powerups. Try to figure out what they do.";
			str[8] = "";
			str[9] = "Enjoy!";
			
			JLabel[] label= new JLabel[10];
			
			for(int i = 0; i != 10; ++i)
			{
				label[i]= new JLabel(str[i]);
				this.layout.setConstraints(label[i], this.c);
				if(i==0 || i == 9)		
					fontSize = 20;
				else 					
					fontSize = 14;
			    label[i].setFont(new Font("Arial", 1, fontSize));
			    label[i].setForeground(Color.black);
			    
			    add(label[i]);
			    this.c.gridwidth = 0;
			    this.layout.setConstraints(label[i], this.c);
			}
			add(this.start);
		}
		
		/**
		 * Ekran wyœwietlania najlepszych wyników
		 */
		protected void highscores() {

			String sTop = new String("TOP 10");
			JLabel jlTop = new JLabel(sTop);
			this.layout.setConstraints(jlTop, this.c_h);
		    jlTop.setFont(new Font("Arial", 1, 40));
		    jlTop.setForeground(Color.black);
		    
		    this.c_h.gridwidth = 2;
		    c_h.weightx = 0.5;
		    c_h.gridx = 0;
		    c_h.gridy = 0;
		    c_h.anchor = GridBagConstraints.CENTER;
		    this.layout.setConstraints(jlTop, this.c_h);
		    add(jlTop);
		    
			String[] topString = topTen.fillTop();
			JLabel[] name= new JLabel[11];
			JLabel[] score = new JLabel[11];
			
			String[][] sortedTop = new String[11][2];
			
			for(int i = 0; i != 11; ++i)
			{
				sortedTop[i][0]= topString[2*i+1];
				sortedTop[i][1]= topString[2*i];
			}
			
			/**
			 * Metoda do sortowania tablic dwuwymiarowych
			 */
			Arrays.sort(sortedTop, new Comparator<String[]>() {
				@Override
            public int compare(final String[] entry1, final String[] entry2) {
                int temp = 0;
				int intTime1 = Integer.parseInt(entry1[0]);
                int intTime2 = Integer.parseInt(entry2[0]);
                if(intTime2 == intTime1)	temp = 0;
                else if(intTime2 < intTime1) temp = -1;
                else temp = 1;
                return temp;
            }
        });
			
			for(int i = 0; i != 10; ++i)
			{
				name[i]= new JLabel(sortedTop[i][1]);
				score[i]= new JLabel(sortedTop[i][0]);
				
				this.layout.setConstraints(name[i], this.c_h);
				this.layout.setConstraints(score[i], this.c_h);

			    name[i].setFont(new Font("Arial", 1, 14));
			    name[i].setForeground(Color.black);
			    score[i].setFont(new Font("Arial", 1, 14));
			    score[i].setForeground(Color.black);
			    
			    this.c_h.gridwidth = 1;
			    
			    c_h.weightx = 0.5;
			    c_h.gridx = 0;
			    c_h.gridy = i+1;
			    c_h.anchor = GridBagConstraints.CENTER;
			    this.layout.setConstraints(name[i], this.c_h);
			    
			    c_h.weightx = 0.5;
			    c_h.gridx = 1;
			    c_h.gridy = i+1;
			    c_h.anchor = GridBagConstraints.CENTER;
			    this.layout.setConstraints(score[i], this.c_h);
			    
			    add(name[i]);
			    add(score[i]);			    
			}
			
			add(this.back_high);
			add(this.delete);	
		}

		/**
		 * Ustawia guziki w zamierzonym porz¹dku(wg c lub c_h)
		 */
		private void setButtons( )
		{
			this.play.setPreferredSize(dimButton);
			this.highscores.setPreferredSize(dimButton);
			this.options.setPreferredSize(dimButton);
			this.exit.setPreferredSize(dimButton);
			this.back.setPreferredSize(dimButton);
			this.start.setPreferredSize(dimButton);
			this.delete.setPreferredSize(dimButton);
			this.save.setPreferredSize(dimButton);
			this.back_high.setPreferredSize(dimButton);
			this.proceed.setPreferredSize(dimButton);
			this.nameField.setPreferredSize(dimButton);
			this.mapField.setPreferredSize(dimButton);
			
			this.c.gridwidth = 0;
			this.c.insets = new Insets(2, 0, 2, 0);

			this.layout.setConstraints(this.play, this.c);
			this.layout.setConstraints(this.highscores, this.c);
			this.layout.setConstraints(this.options, this.c);
			this.layout.setConstraints(this.exit, this.c);
			this.layout.setConstraints(this.start, this.c);
			this.layout.setConstraints(this.save, this.c);			
			this.layout.setConstraints(this.back, this.c);
			this.layout.setConstraints(this.proceed, this.c);
			this.layout.setConstraints(this.nameField, this.c);
			this.layout.setConstraints(this.mapField, this.c);

			c_h.gridwidth = 1;
		    c_h.gridx = 0;
		    c_h.gridy = 11;
			this.layout.setConstraints(this.back_high, this.c_h);
			
		    c_h.gridx = 1;
		    c_h.gridy = 11;
			this.layout.setConstraints(this.delete, this.c_h);

			add(this.play);
			add(this.highscores);
			add(this.options);
			add(this.exit);
			
		}
		
		/**
		 * Startuje Pacmana i zasadnicz¹ czêœæ gry
		 */
		public void startGame(){
			try {
				pacman = new Pacman(myGame.getLocationOnScreen(), myGame.getSize(), Integer.parseInt(map) );
				pacman.setName(Name);
				myGame.setVisible(false);
			}catch(NumberFormatException nFE) {
				System.out.println("Error. Wrong Map Name");
				System.exit(0);
			}
			}

		/**
		 * Rysuje komponent
		 * @param g - kontekst graficzny
		 */
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
	
			if (this.background != null)
				g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
			
			this.play.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
			this.highscores.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
			this.options.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
			this.exit.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
			this.back.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
			this.start.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
			this.delete.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
			this.save.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
			this.back_high.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
			this.proceed.setPreferredSize(new Dimension(dimButton.width * getWidth() / 600, dimButton.height * getHeight() / 600));
		}
}
