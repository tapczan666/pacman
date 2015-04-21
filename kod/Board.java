package pacman;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import pacman.Map.Position;

/**
 * Klasa odpowiedzialna za rysowanie planszy oraz logikê gry.
 * @author Maciek Wójcik & Kuba Wilkowski
 *
 */
public class Board extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4344914725421971143L;
	/**
	 * Zmienna przechowuj¹ca aktualnie wczytan¹ mapê.
	 */
	private Map m;
	/**
	 * Zmienna opisuj¹ca Pacmana.
	 */
	private Player p;
	/**
	 * Zmienna u¿ywana do przechowywania liczby kropek na planszy.
	 */
	private int pellets;
	/**
	 * Zmienna u¿ywana do przechowywania liczby ¿yæ.
	 */
	private int lives;
	/**
	 * Zmienna u¿ywana do przechowywania wyniku.
	 */
	private int score;
	/**
	 * Zmienna okreœlaj¹ca numer poziomu.
	 */
	private int levelNum;
	/**
	 * Licznik klatek animacji Pacmana.
	 */
	private int frame;
	/**
	 * Licznik pomocniczy do animacji Pacmana.
	 */
	private int counter;
	/**
	 * Zmienne opisuj¹ce ruch Pacmana.
	 */
	private double dx, dy;
	/**
	 * Zmienna okreœlaj¹ca d³ugoœæ kroku Pacmana.
	 */
	private double step = 0.125;
	/**
	 * Zmienna okreœlaj¹ca d³ugoœæ kroku duszków.
	 */
	private double ghStep = 0.125;
	/**
	 * Zmienna okreœlaj¹ca aktualny stan (bonus) Pacmana.
	 */
	private String status;
	/**
	 * Zmienna okreœlaj¹ca rozmiar okna. U¿ywana do skalowania.
	 */
	private Dimension size;
	/**
	 * Zmienne okreœlaj¹ce stany zwi¹zane z bonusami.
	 */
	private boolean scared, speedUp, extraPoints, ghostsStop, bonusStatus;
	/**
	 * Rozszerzalna lista obiektów typu Ghost
	 */
	private List<Ghost> gh;
	/**
	 * Wykorzystywane obrazki.
	 */
	private Image r1, r2, r3, r4, l1, l2, l3, l4, u1, u2, u3, u4, d1, d2, d3, d4, wall, pellet, superPellet, ghostLeft, ghostRight, scaredGhostLeft, scaredGhostRight;
	/**
	 * Zmienne pomocnicze do skalowania obrazów.
	 */
	private Image r11, r22, r33, r44, l11, l22, l33, l44, u11, u22, u33, u44, d11, d22, d33, d44, wall1, pellet1, superPellet1, ghostLeft1, ghostRight1, scaredGhostLeft1, scaredGhostRight1;
	/**
	 * Zmienna
	 */
	private Bonus bonus;
	
	/**
	 * Zmienna do koñczenia gry
	 */	
	boolean gameOver = false;
	/**
	 * Konstruktor klasy Board. Inicjuje zmienne i wczytuje potrzebne pliki (pierwsz¹ mapê i obrazki).
	 */
	public Board(int startLvl){
		dx = 0;
		dy = 0;
		levelNum = startLvl;
		m = new Map(levelNum);
		scared = false;
		speedUp = false;
		extraPoints = false;
		ghostsStop = false;
		bonusStatus = false;
		status = "NORMAL";
		frame = 1;
		counter=0;
		pellets = m.pelletNum();
		score = 0;
		lives = 3;
		Position pos = m.findPlayer();
		p = new Player(pos);
		gh = new ArrayList<Ghost>();
		m.findGhosts(gh);
		setFocusable(true);
		this.addKeyListener(new AL());
		loadImages();
	}
	
	/**
	 * Wczytuje potrzebne obrazki.
	 */
	public void loadImages(){
		
		this.wall = new ImageIcon("src\\img\\wall.png").getImage();
		this.r1 = new ImageIcon("src\\img\\pac\\r1.png").getImage();
		this.r2 = new ImageIcon("src\\img\\pac\\r2.png").getImage();
		this.r3 = new ImageIcon("src\\img\\pac\\r3.png").getImage();
		this.r4 = new ImageIcon("src\\img\\pac\\r4.png").getImage();
		this.l1 = new ImageIcon("src\\img\\pac\\l1.png").getImage();
		this.l2 = new ImageIcon("src\\img\\pac\\l2.png").getImage();
		this.l3 = new ImageIcon("src\\img\\pac\\l3.png").getImage();
		this.l4 = new ImageIcon("src\\img\\pac\\l4.png").getImage();
		this.u1 = new ImageIcon("src\\img\\pac\\u1.png").getImage();
		this.u2 = new ImageIcon("src\\img\\pac\\u2.png").getImage();
		this.u3 = new ImageIcon("src\\img\\pac\\u3.png").getImage();
		this.u4 = new ImageIcon("src\\img\\pac\\u4.png").getImage();
		this.d1 = new ImageIcon("src\\img\\pac\\d1.png").getImage();
		this.d2 = new ImageIcon("src\\img\\pac\\d2.png").getImage();
		this.d3 = new ImageIcon("src\\img\\pac\\d3.png").getImage();
		this.d4 = new ImageIcon("src\\img\\pac\\d4.png").getImage();
		this.pellet = new ImageIcon("src\\img\\pellet.png").getImage();
		this.superPellet = new ImageIcon("src\\img\\superpellet.png").getImage();
		this.ghostLeft = new ImageIcon("src\\img\\ghostleft.png").getImage();
		this.ghostRight = new ImageIcon("src\\img\\ghostright.png").getImage();
		this.scaredGhostLeft = new ImageIcon("src\\img\\scaredghostleft.png").getImage();
		this.scaredGhostRight = new ImageIcon("src\\img\\scaredghostright.png").getImage();
		
	}
	
	/**
	 * Zmienia rozmiar wczytanych obrazków w zale¿noœci od aktualnego rozmiaru okna.
	 */
	private void scaleImages(){
		wall1 = resizeImage(wall, size.width/15, size.height/15);
		r11 = resizeImage(r1, size.width/15, size.height/15);
		r22 = resizeImage(r2, size.width/15, size.height/15);
		r33 = resizeImage(r3, size.width/15, size.height/15);
		r44 = resizeImage(r4, size.width/15, size.height/15);
		l11 = resizeImage(l1, size.width/15, size.height/15);
		l22 = resizeImage(l2, size.width/15, size.height/15);
		l33 = resizeImage(l3, size.width/15, size.height/15);
		l44 = resizeImage(l4, size.width/15, size.height/15);
		u11 = resizeImage(u1, size.width/15, size.height/15);
		u22 = resizeImage(u2, size.width/15, size.height/15);
		u33 = resizeImage(u3, size.width/15, size.height/15);
		u44 = resizeImage(u4, size.width/15, size.height/15);
		d11 = resizeImage(d1, size.width/15, size.height/15);
		d22 = resizeImage(d2, size.width/15, size.height/15);
		d33 = resizeImage(d3, size.width/15, size.height/15);
		d44 = resizeImage(d4, size.width/15, size.height/15);
		pellet1 = resizeImage(pellet, size.width/15, size.height/15);
		superPellet1 = resizeImage(superPellet, size.width/15, size.height/15);
		ghostLeft1 = resizeImage(ghostLeft, size.width/15, size.height/15);
		ghostRight1 = resizeImage(ghostRight, size.width/15, size.height/15);
		scaredGhostLeft1 = resizeImage(scaredGhostLeft, size.width/15, size.height/15);
		scaredGhostRight1 = resizeImage(scaredGhostRight, size.width/15, size.height/15);
	}
	
	/**
	 * Skaluje obraz do podanego rozmiaru.
	 * @param image - obraz do przeskalowania
	 * @param width - szerokoœæ obrazu wyjœciowego
	 * @param height - wysokoœæ obrazu wyjœciowego
	 * @return bufferedImage - przeskalowany obraz
	 */
	public static BufferedImage resizeImage(final Image image, int width, int height) {
			//System.out.println("BufferedImage resizeImage");
	        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        final Graphics2D graphics2D = bufferedImage.createGraphics();
	        graphics2D.setComposite(AlphaComposite.Src);
	        graphics2D.drawImage(image, 0, 0, width, height, null);
	        graphics2D.dispose();
	        return bufferedImage;
	}
	
	
	/**
	 * Wczytuje i przygotowuje nastêpny poziom.
	 */
	public void nextLevel(){
		dx = 0;
		dy = 0;
		levelNum++;
		scared = false;
		try{
			m = new Map(levelNum);
			pellets = m.pelletNum();
			Position pos = m.findPlayer();
			p.setPosition(pos);
			gh.clear();
			m.findGhosts(gh);
			
		}catch(Exception e){
			gameOver();
		}
	}
	
	public void gameOver(){
			gameOver=true;
	}
	
	public void setMap(int n){
		System.out.println(n);
		dx = 0;
		dy = 0;
		levelNum = n;
		scared = false;
		try{
		m = new Map(levelNum);
		pellets = m.pelletNum();
		Position pos = m.findPlayer();
		p.setPosition(pos);
		gh.clear();
		m.findGhosts(gh);

		}catch(Exception e){
		gameOver();
		}
		}
	
	/**
	 * Rysuje planszê gry na ekranie.
	 */
	public void paint(Graphics g){
		
		
		super.paint(g);
		size = this.getSize();
		size.height += -100;
		super.setBackground(Color.BLACK);
		scaleImages();
		if(!speedUp && !extraPoints && !ghostsStop){
			createBonus();
		}
		checkPlayerCollision();
		p.move(dx, dy);
		randGhosts();
		checkGhostCollision();
		moveGhosts();
		
		drawMap(g, wall1, pellet1,superPellet1);
		drawPlayer(g, r11, r22, r33, r44, l11, l22, l33, l44, u11, u22, u33, u44, d11, d22, d33, d44);
		drawGhosts(g, ghostRight1, ghostLeft1, scaredGhostRight1, scaredGhostLeft1);
		drawBonus(g);
		drawInfo(g);
		
		if(gameOver)
		{
			

	        g.setFont(new Font("Arial", Font.BOLD, 50));
	        g.setColor(Color.WHITE);
	        g.drawString("GAME OVER!", 50, 175);
	        g.setFont(new Font("Arial", Font.BOLD, 40));
	        g.setColor(Color.WHITE);
	        g.drawString("You got " + Integer.toString(score)+" points.", 50, 300);

		}
		
		
		if(counter%5 == 0){
			frame++;
			if(frame == 5){
				frame = 1;
			}
		}
	}
	
	/**
	 * Rysuje na ekranie mapê wraz z kropkami.
	 * @param g - kontekst graficzny
	 * @param wall - obrazek œciany
	 * @param pellet - obraz kropki
	 * @param superpellet - obraz Super Kropki
	 */
	public void drawMap(Graphics g, Image wall, Image pellet, Image superpellet){
		for(int y=0; y<15; y++){
			for(int x=0; x<15; x++){
				if(m.getMap(y, x).equals("X")){
					g.drawImage(wall, x*size.width/15, y*size.height/15, this);
				}else if(m.getMap(y, x).equals("O")){
					g.drawImage(pellet, x*size.width/15, y*size.height/15, this);
				}else if(m.getMap(y, x).equals("S")){
					g.drawImage(superpellet, x*size.width/15, y*size.height/15, this);
				}
			}
		}
	}
	
	/**
	 * Rysuje na ekranie Pacmana.
	 * @param g - kontekst graficzny
	 * @param r1 - obraz Pacmana w prawo 1
	 * @param r2 - obraz Pacmana w prawo 2
	 * @param r3 - obraz Pacmana w prawo 3
	 * @param r4 - obraz Pacmana w prawo 4
	 * @param l1 - obraz Pacmana w lewo 1
	 * @param l2 - obraz Pacmana w lewo 2
	 * @param l3 - obraz Pacmana w lewo 3
	 * @param l4 - obraz Pacmana w lewo 4
	 * @param u1 - obraz Pacmana w górê 1
	 * @param u2 - obraz Pacmana w górê 2
	 * @param u3 - obraz Pacmana w górê 3
	 * @param u4 - obraz Pacmana w górê 4
	 * @param d1 - obraz Pacmana w dó³ 1
	 * @param d2 - obraz Pacmana w dó³ 2
	 * @param d3 - obraz Pacmana w dó³ 3
	 * @param d4 - obraz Pacmana w dó³ 4
	 */
	public void drawPlayer(Graphics g, Image r1, Image r2, Image r3, Image r4, Image l1, Image l2, Image l3, Image l4, Image u1, Image u2, Image u3, Image u4, Image d1, Image d2, Image d3, Image d4){
	
		if(p.getDir().equals("right")){
			
			switch(frame){
			case(1): g.drawImage(r1, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(2): g.drawImage(r2, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(3): g.drawImage(r3, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(4): g.drawImage(r4, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			}
		}else if(p.getDir().equals("left")){
			
			switch(frame){
			case(1): g.drawImage(l1, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(2): g.drawImage(l2, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(3): g.drawImage(l3, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(4): g.drawImage(l4, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			}
		}else if(p.getDir().equals("up")){
			
			switch(frame){
			case(1): g.drawImage(u1, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(2): g.drawImage(u2, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(3): g.drawImage(u3, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(4): g.drawImage(u4, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			}
		}else if(p.getDir().equals("down")){
			
			switch(frame){
			case(1): g.drawImage(d1, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(2): g.drawImage(d2, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(3): g.drawImage(d3, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			case(4): g.drawImage(d4, (int)(p.getTileX()*size.width/15), (int)(p.getTileY()*size.height/15), this);
					break;
			}
		}
		counter++;
	}
	
	/**
	 * Rysuje na ekranie duszki.
	 * @param g - kontekst graficzny
	 * @param ghostright - obraz ducha w prawo
	 * @param ghostleft - obraz ducha w lewo
	 * @param scaredghostright - obraz przestraszonego ducha w prawo
	 * @param scaredghostleft - obraz przestraszonego ducha w lewo
	 */
	public void drawGhosts(Graphics g, Image ghostright, Image ghostleft, Image scaredghostright, Image scaredghostleft){
		for(int i = 0; i < gh.size(); i++){
			Ghost ghost = gh.get(i);
			if(!scared){
				if(ghost.getDir().equals("right")){
					g.drawImage(ghostright, (int)(ghost.getTileX()*size.width/15), (int)(ghost.getTileY()*size.height/15), this);
				} else{
					g.drawImage(ghostleft, (int)(ghost.getTileX()*size.width/15), (int)(ghost.getTileY()*size.height/15), this);
				}
			} else{
				if(ghost.getDir().equals("right")){
					g.drawImage(scaredghostright, (int)(ghost.getTileX()*size.width/15), (int)(ghost.getTileY()*size.height/15), this);
				} else{
					g.drawImage(scaredghostleft, (int)(ghost.getTileX()*size.width/15), (int)(ghost.getTileY()*size.height/15), this);
				}
			}
		}
	}
	
	/**
	 * Rysuje na ekranie panel informacyjny.
	 * @param g - kontekst graficzny
	 */
	public void drawInfo(Graphics g){
		g.setColor(Color.WHITE);
		g.drawImage(r33, size.width/15, size.height+20,this);
		g.drawString(Integer.toString(lives), 2*size.width/15, size.height+39);
		g.drawString("PELLETS: "+pellets, 5*size.width/15, size.height+40);
		g.drawString("SCORE: "+score, 10*size.width/15, size.height+40);
		g.drawString(status, 5*size.width/15, size.height+60);
			
	}
	
	/**
	 * Rysuje na ekranie aktualny bonus.
	 * @param g - kontekst graficzny
	 */
	public void drawBonus(Graphics g){
		if(speedUp || extraPoints || ghostsStop){
			g.drawImage(bonus.getIcon(), bonus.getX()*size.width/15, bonus.getY()*size.height/15, this);
		}
	}
	
	/**
	 * Losuje nowy bonus, je¿eli ¿aden nie jest na ekranie ani aktywny.
	 */
	public void createBonus(){
		if(!bonusStatus){
			Random r = new Random();
			int n = r.nextInt(1000);
			
			if(n == 1){
				int x, y;
				speedUp = true;
				while(true){
					x = r.nextInt(13)+1;
					y = r.nextInt(13)+1;
					if(m.getMap(y, x).equals("O")){
						System.out.println(x+" "+y);
						break;
					}
				}
				Image speedUpImage = new ImageIcon("src\\img\\bonus\\speedup.png").getImage();
				speedUpImage = resizeImage(speedUpImage, size.width/15, size.height/15);
				bonus = new Bonus(x, y, speedUpImage);
				Timer timer = new Timer();
				BonusTimer t = new BonusTimer();
				timer.schedule(t, 5000); 
			}
			if(n == 505){
				int x, y;
				extraPoints = true;
				while(true){
					x = r.nextInt(13)+1;
					y = r.nextInt(13)+1;
					if(m.getMap(y, x).equals("O")){
						break;
					}
				}
				Image extraPointsImage = new ImageIcon("src\\img\\bonus\\extrapoints.png").getImage();
				extraPointsImage = resizeImage(extraPointsImage, size.width/15, size.height/15);
				bonus = new Bonus(x, y, extraPointsImage);
				Timer timer = new Timer();
				BonusTimer t = new BonusTimer();
				timer.schedule(t, 5000); 
			}
			if(n == 666){
				int x, y;
				ghostsStop = true;
				while(true){
					x = r.nextInt(13)+1;
					y = r.nextInt(13)+1;
					if(m.getMap(y, x).equals("O")){
						System.out.println(x+" "+y);
						break;
					}
				}
				Image ghostsStopImage = new ImageIcon("src\\img\\bonus\\ghostsstop.png").getImage();
				ghostsStopImage = resizeImage(ghostsStopImage, size.width/15, size.height/15);
				bonus = new Bonus(x, y, ghostsStopImage);
				Timer timer = new Timer();
				BonusTimer t = new BonusTimer();
				timer.schedule(t, 5000); 
			}
		}
	}
	
	/**
	 * Zwraca liczbê kropek na ekranie.
	 * @return pellets - liczba kropek na ekranie
	 */
	public int getPellets(){
		return pellets;
	}
	
	/**
	 * Zwraca liczbê dostêpnych ¿yæ.
	 * @return lives - liczba dostêpnych ¿yæ
	 */
	public int getLives(){
		return lives;
	}
	
	
	/**
	 * Zwraca liczbê zdobytych punktów.
	 * @return score - liczba punktów
	 */
	public int getScore() {
		return score;
	}

	
	/**
	 * Losowo wybiera kierunek poruszania siê duszka.
	 */
	public void randGhosts(){
		for(int i = 0; i < gh.size(); i++){
			Ghost ghost = gh.get(i);
			if(ghost.getTileX()%1 == 0 && ghost.getTileY()%1 == 0){
				Random r = new Random();
				int n = r.nextInt(40);
				switch(n){
				case(0): ghost.setDx(ghStep);
						ghost.setDy(0);
						ghost.setDir("right");
					break;
				case(1): ghost.setDx(-ghStep);
						ghost.setDy(0);
						ghost.setDir("left");
						break;
				case(2): ghost.setDy(ghStep);
						ghost.setDx(0);
						break;
				case(3): ghost.setDy(-ghStep);
						ghost.setDx(0);
						break;
				default: break;
				}
			}
		}
	}
	
	/**
	 * Wywo³uje zmianê po³o¿enia wszystkich duszków.
	 */
	public void moveGhosts(){
		for(int i = 0; i < gh.size(); i++){
			Ghost ghost = gh.get(i);
			ghost.move();
		}
	}
	
	/**
	 * Detekcja kolizji Pacmana ze œcianami, duszkami,  kropkami oraz bonusami. Uruchamia bonusy.
	 */
	public void checkPlayerCollision(){
		if(p.getTileY()%1 == 0 && p.getTileX()%1 == 0){
			if(m.getMap((int)(p.getTileY()-1), (int)(p.getTileX())).equals("X") && dy < 0){
				dy = 0;
			}
			
			if(m.getMap((int)(p.getTileY()+1), (int)(p.getTileX())).equals("X") && dy > 0){
				dy = 0;
			}

			if(m.getMap((int)(p.getTileY()), (int)((p.getTileX()-1))).equals("X") && dx < 0){
				dx = 0;
			}
			
			if(m.getMap((int)(p.getTileY()), (int)(p.getTileX()+1)).equals("X") && dx > 0){
				dx = 0;
			}
		}
		
		if((m.getMap((int)(p.getTileY()), (int)(p.getTileX())).equals("O"))){
			m.replaceChar((int)(p.getTileY()), (int)(p.getTileX()));
			pellets--;
			score +=50;
		} else if((m.getMap((int)(p.getTileY()), (int)(p.getTileX())).equals("S"))){
			m.replaceChar((int)(p.getTileY()), (int)(p.getTileX()));
			pellets--;
			score +=100;
			scared = true;
			Timer timer = new Timer();
			SuperPelletTimer t = new SuperPelletTimer();
			timer.schedule(t, 5000);
		}
		
		if(speedUp || extraPoints || ghostsStop){
			if(p.getTileX()%1 == 0 && p.getTileY()%1 == 0){
				if((p.getTileX() == bonus.getX()) && (p.getTileY() == bonus.getY())){
					if(extraPoints){
						score += 500;
						extraPoints = false;
						
					}
					else if(speedUp){
						step = 0.25;
						status = "SPEEDUP";
						speedUp = false;
						bonusStatus = true;
						Timer timer = new Timer();
						SpeedUpTimer t = new SpeedUpTimer();
						timer.schedule(t, 5000);
					}
					else if(ghostsStop){
						ghStep = 0;
						status = "GHOSTSSTOP";
						ghostsStop = false;
						bonusStatus = true;
						Timer timer = new Timer();
						GhostsStopTimer t = new GhostsStopTimer();
						timer.schedule(t, 5000);
					}
					
				}
			}
		}
		
		for(int i = 0; i < gh.size(); i++){
			Ghost ghost = gh.get(i);
			if(((int)ghost.getTileX() == (int)p.getTileX()) && ((int)ghost.getTileY() == (int)p.getTileY())){
				if(!scared){
					lives--;
					p.setPosition(m.findPlayer());
					dx = 0;
					dy = 0;
				}else if(scared){
					score += 200;
					gh.remove(i);
				}
			}
		}
	}
	
	/**
	 * Detekcja kolizji duszków ze œcianami.
	 */
	public void checkGhostCollision(){
		for(int i = 0; i < gh.size(); i++){
			Ghost ghost = gh.get(i);
			if(ghost.getTileY()%1 == 0 && ghost.getTileX()%1 == 0){
				if(m.getMap((int)(ghost.getTileY()-1), (int)(ghost.getTileX())).equals("X") && ghost.getDy() < 0){
					ghost.setDy(0);
				}
				
				if(m.getMap((int)(ghost.getTileY()+1), (int)(ghost.getTileX())).equals("X") && ghost.getDy() > 0){
					ghost.setDy(0);
				}

				if(m.getMap((int)(ghost.getTileY()), (int)((ghost.getTileX()-1))).equals("X") && ghost.getDx() < 0){
					ghost.setDx(0);
				}
				
				if(m.getMap((int)(ghost.getTileY()), (int)(ghost.getTileX()+1)).equals("X") && ghost.getDx() > 0){
					ghost.setDx(0);
				}
			}
		}
	}
	
	/**
	 * Klasa pomocnicza do odliczania czasu trwania bonusa.
	 * @author Maciek Wójcik & Kuba Wilkowski
	 *
	 */
	public class SuperPelletTimer extends TimerTask{
		public void run(){
			scared = false;
		}
	}
	
	/**
	 * Klasa pomocnicza do odliczania czasu przebywania bonusa na ekranie.
	 * @author Maciek Wójcik & Kuba Wilkowski
	 *
	 */
	public class BonusTimer extends TimerTask{
		public void run(){
			if(extraPoints) extraPoints = false;
			else if(speedUp) speedUp = false;
			else if(ghostsStop) ghostsStop = false;
			
			bonus = null;
		}
	}
	
	/**
	 * Klasa pomocnicza do odliczania czasu trwania bonusa GhostsStop.
	 * @author Maciek Wójcik & Kuba Wilkowski
	 *
	 */
	public class GhostsStopTimer extends TimerTask{
		public void run(){
			ghStep = 0.125;
			status = "NORMAL";
			bonusStatus = false;
		}
	}
	
	/**
	 * Klasa pomocnicza do odliczania czasu trwania bonusa SpeedUp.
	 * @author Maciek Wójcik & Kuba Wilkowski
	 *
	 */
	public class SpeedUpTimer extends TimerTask{
		public void run(){
			step = 0.125;
			status = "NORMAL";
			bonusStatus = false;
		}
	}
	
	/**
	 * Obs³uga zdarzeñ klawiatury.
	 * @author Maciek Wójcik & Kuba Wilkowski
	 *
	 */
	public class AL implements KeyListener{
	
		public void keyPressed(KeyEvent e){
			System.out.println("key pressed");
			int keycode = e.getKeyCode();
			
			if(keycode == KeyEvent.VK_UP && dy >= 0){
				dy = -step;
				dx = 0;
				//p.changeDir("up");
				System.out.println("tileX "+p.getTileX());
				System.out.println("tileY "+p.getTileY());
				System.out.println(pellets);
			}
			if(keycode == KeyEvent.VK_DOWN && dy <= 0){
				dy = step;
				dx = 0;
				//p.changeDir("down");
				System.out.println("tileX "+p.getTileX());
				System.out.println("tileY "+p.getTileY());
				System.out.println(pellets);
			}
			if(keycode == KeyEvent.VK_LEFT && dx >= 0){
				dx = -step;
				dy = 0;
				//p.changeDir("left");
				System.out.println("tileX "+p.getTileX());
				System.out.println("tileY "+p.getTileY());
				System.out.println(pellets);
			}
			if(keycode == KeyEvent.VK_RIGHT && dx <= 0){		
				dx = step;
				dy = 0;
				//p.changeDir("right");	
			}
				System.out.println("tileX "+p.getTileX());
				System.out.println("tileY "+p.getTileY());
				System.out.println(pellets);
			}
		
		public void keyReleased(KeyEvent e){
			int keycode = e.getKeyCode();
			
			if(keycode == KeyEvent.VK_UP){
				//dy = 0;
			}
			
			if(keycode == KeyEvent.VK_DOWN){
				//moving = false;
			}
			
			if(keycode == KeyEvent.VK_LEFT){
				//moving = false;
			}
			
			if(keycode == KeyEvent.VK_RIGHT){
				//moving = false;
			}
			
		}
		public void keyTyped(KeyEvent e){
			
		}
	}
	
	
	

}
