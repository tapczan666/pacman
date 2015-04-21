package pacman;

import java.io.File;
import java.util.List;
import java.util.Scanner;


/**
 * Klasa s�u��ca do wczytywania, przechowywania i przeszukiwania mapy.
 * @author Maciek W�jcik & Kuba Wilkowski
 *
 */
public class Map{
	/**
	 * Zmienna klasy Scanner s�u��ca do wczytywania zawarto�ci pliku.
	 */
	private Scanner m;
	/**
	 * Dwuwymiarowa tablica obiekt�w klasy String. Reprezentuje zawarto�� poszczeg�lnych kom�rek mapy.
	 */
	private String Map[][] = new String[15][15];
	/**
	 * Zmienna u�ywana do przechowywania liczby kropek na mapie;
	 */
	private int pellets;
	
	/**
	 * Konstruktor klasy Map. 
	 * @param levelNum - numer poziomu, kt�ry chcemy wczyta�
	 */
	public Map(int levelNum){
		openFile(levelNum);
		readFile();
		closeFile();
		pellets = 0;
	}
	
	/**
	 * Zwraca zawarto�� wskazanej kom�rki mapy.
	 * @param row - wiersz 
	 * @param col - kolumna 
	 * @return index - zawarto�� wskazanej kom�rki mapy
	 */
	public String getMap(int row, int col){
		String index = Map[row][col];
		return index;
	}
	
	/**
	 * Klasa reprezentuj�ca pozycj� na mapie.
	 * Wykorzystywana dla �atwiejszego przekazywania zmiennych.
	 * @author Maciek
	 *
	 */
	public class Position{
		public int tileX;
		public int tileY;
	}
	
	/**
	 * Zwraca pozycj� pocz�tkow� Pacmana na wczytanej mapie.
	 * @return pos - pozycja Pacmana na mapie
	 */
	public Position findPlayer(){
		Position pos = new Position();
		for(int y=0; y<15; y++){
			for(int x=0; x<15; x++){
				if(Map[y][x].equals("P")){
					pos.tileX = x;
					pos.tileY = y;
				}
			}
		}
		return pos; 
	}
	
	/**
	 * Znajduje pozycje pocz�tkowe duszk�w na wczytanej mapie. 
	 * @param gh - lista obiekt�w klasy Ghost
	 */
	public void findGhosts(List<Ghost> gh){
		Position pos = new Position();
		for(int y=0; y<15; y++){
			for(int x=0; x<15; x++){
				if(Map[y][x].equals("G")){
					pos.tileX = x;
					pos.tileY = y;
					gh.add(new Ghost(pos));
				}
			}
		}
	}
	
	/**
	 * Zwraca liczb� kropek znajduj�cych si� na mapie.
	 * @return pellets - liczba kropek na mapie
	 */
	public int pelletNum(){
		for(int y=0; y<15; y++){
			for(int x=0; x<15; x++){
				if(Map[y][x].equals("O") || Map[y][x].equals("S")){
					pellets++;
				}
			}
		}
		return pellets;
	}
	
	/**
	 * Zmienia wskazany element mapy na 0.
	 * @param row - wiersz
	 * @param col - kolumna
	 */
	public void replaceChar(int row, int col) {
		
			Map[row][col] = "0";
		}
	
	/**
	 * Otwiera wskazany w konstruktorze plik.
	 * @param levelNum - numer poziomu;
	 */
	public void openFile(int levelNum){
		try{
			//m = new Scanner(new File("E:\\Java\\Pacman\\src\\pacman\\map1.txt"));
			m = new Scanner(new File("src\\maps\\map"+levelNum+".txt"));
		}catch(Exception e){
			System.out.println("Error loading map");
			System.exit(0);
		}
	}
	
	
	
	/**
	 * Wczytuje otwarty plik linia po linii i wype�nia tablic� Map[][].
	 */
	public void readFile(){
		String temp;
		while(m.hasNext()){
			for(int y=0; y<15; y++){
				temp = m.next();
				for(int x=0; x<15; x++){
					Map[y][x] = temp.substring(x, x+1);
				}
			}
		}
	}
	
	/**
	 * Zamyka otwarty plik.
	 */
	public void closeFile(){
		m.close();
	}
	
}
