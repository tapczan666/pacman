package pacman;

import java.io.File;
import java.util.List;
import java.util.Scanner;


/**
 * Klasa s³u¿¹ca do wczytywania, przechowywania i przeszukiwania mapy.
 * @author Maciek Wójcik & Kuba Wilkowski
 *
 */
public class Map{
	/**
	 * Zmienna klasy Scanner s³u¿¹ca do wczytywania zawartoœci pliku.
	 */
	private Scanner m;
	/**
	 * Dwuwymiarowa tablica obiektów klasy String. Reprezentuje zawartoœæ poszczególnych komórek mapy.
	 */
	private String Map[][] = new String[15][15];
	/**
	 * Zmienna u¿ywana do przechowywania liczby kropek na mapie;
	 */
	private int pellets;
	
	/**
	 * Konstruktor klasy Map. 
	 * @param levelNum - numer poziomu, który chcemy wczytaæ
	 */
	public Map(int levelNum){
		openFile(levelNum);
		readFile();
		closeFile();
		pellets = 0;
	}
	
	/**
	 * Zwraca zawartoœæ wskazanej komórki mapy.
	 * @param row - wiersz 
	 * @param col - kolumna 
	 * @return index - zawartoœæ wskazanej komórki mapy
	 */
	public String getMap(int row, int col){
		String index = Map[row][col];
		return index;
	}
	
	/**
	 * Klasa reprezentuj¹ca pozycjê na mapie.
	 * Wykorzystywana dla ³atwiejszego przekazywania zmiennych.
	 * @author Maciek
	 *
	 */
	public class Position{
		public int tileX;
		public int tileY;
	}
	
	/**
	 * Zwraca pozycjê pocz¹tkow¹ Pacmana na wczytanej mapie.
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
	 * Znajduje pozycje pocz¹tkowe duszków na wczytanej mapie. 
	 * @param gh - lista obiektów klasy Ghost
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
	 * Zwraca liczbê kropek znajduj¹cych siê na mapie.
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
	 * Wczytuje otwarty plik linia po linii i wype³nia tablicê Map[][].
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
