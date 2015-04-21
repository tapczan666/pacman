package pacman;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
/**
 *Klasa obs³uguj¹ca najlepsze wyniki. Zapisuje, wczytuje, kasuje
 *@author Maciek Wójcik & Kuba Wilkowski
 */
public class TopTen {
	/**
	 *Obiekt Scanner do odczytu z plików
	 */
	private Scanner m;
	/**
	 *tablica Stringów do przechowywania wyników 
	 */
	String[] string = new String[22];

	/**
	 *metoda, która otwiera strumieñ, wczytuje œciœle okreœlon¹ zawartoœæ do tablicy stringów i zamyka strumieñ
	 *@return string - tablica stringów, w której s¹ nazwy i wyniki najlepszych graczy
	 */
	public String[] fillTop() {
		openFile();
		readFile();
		closeFile();
		return string;
	}
	
	/**
	 *Zamyka strumieñ
	 */
	private void closeFile() {
		m.close();
	}

	/**
	 *Wczytuje zawartoœæ pliku do tablicy stringów
	 */
	private void readFile() {
			for(int i=0; i!=22; i++){
						string[i] = m.next();
			}
	}

	/**
	 *Otwiera strumieñ i sprawdza czy plik istnieje 
	 */
	private void openFile() {
		try{
			m = new Scanner(new File("src\\top\\highscores.txt"));
		}catch(Exception e){
			System.out.println("File does not exist!");
			System.exit(0);
		}
	}
	/**
	 *Usuwa wyniki, czyli wype³nia plik wyjœciowy kreskami i zerami
	 */
	public void delete() throws IOException {
		FileWriter fw = new FileWriter("src\\top\\highscores.txt");
		for(int i = 0 ; i != 11 ; ++i){
			string[2*i] = "...";
			string[2*i+1] = "0";
		}
		for (int i = 0; i != 22; i++) {
			fw.write(string[i] + "\n");
		}
		fw.close();
	}
	
	/**
	 *Je¿eli wynik uzyskany przez gracza jest dostatecznie dobry, zapisuje do tablicy najlepszych wyników.
	 *Je¿eli aktualny wynik jest lepszy ni¿ 11, to zostaje zapisany do tablicy, niezale¿nie od koñcowej pozycji(mo¿e byæ nawet 11,czyli poza
	 *top10). Nastêpnie tablica jest posortowana i do wgl¹du graczy jest tylko 10 najlepszych wyników.
	 *@param name - nazwa gracza
	 *@param score - rezultat gracza
	 */
	public void send(String name, int score) throws IOException{
		openFile();
		readFile();
		closeFile();

		FileWriter fw = new FileWriter("src\\top\\highscores.txt");
		String[][] tempString = new String[11][2];
		

		for(int i = 0; i != 11; ++i)
		{
			tempString[i][0]= string[2*i+1];
			tempString[i][1]= string[2*i];
		}
		tempString[10][0] = Integer.toString(score);
		tempString[10][1] = name;

		/**
		 *metoda sortuj¹ca tablice dwuwymiarow¹ 
		 */
		Arrays.sort(tempString, new Comparator<String[]>() {
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
		for (int i = 0; i != 11; i++) {
			fw.write(tempString[i][1] + "\t" +tempString[i][0] + "\n" );
		}
		fw.close();
	}
}