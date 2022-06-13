import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Trace extends JPanel{

	
	
	double echelleX;
	double echelleY;
	
	int[][] t1;
	int t;
	
	Color Couleur[] = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN};
			
			
	public Trace(int[][] tab, int max) {
		this.t1 = tab;
		this.t = max;
		this.repaint();
	}

	//Trace les courbes en fonction des tableaux fournis
	public void paintComponent(Graphics g) {
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 18));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1100, 700);
		
		//Dessine les axes
		g.setColor(Color.BLACK);
		g.drawLine(60, 480, 860, 480);
		g.drawLine(60, 30, 60, 480);
		g.drawLine(860, 480, 855, 475);
		g.drawLine(860, 480, 855, 485);
		g.drawLine(60, 30, 55, 35);
		g.drawLine(60, 30, 65, 35);
		
		
		//Légende
		g.drawString("Crocodiles", 37, 20);
		g.drawString("années", 865, 485);
		g.drawRect(910, 30, 150, 147);
		g.drawString("Total", 960, 58);
		g.drawString("Femelles", 960, 108);
		g.drawString("Males", 960, 158);
		g.fillRect(920, 45, 30, 17);
		g.setColor(Color.RED);
		g.fillRect(920, 95, 30, 17);
		g.setColor(Color.BLUE);
		g.fillRect(920, 145, 30, 17);
		
		
		//Calcule l'échelle des axes horizontaux et verticaux
		double m = maxt(t1[0]);
		echelleY = 450 / m;
		echelleX = 800 / t;
		

		g.setColor(Color.BLACK);
		g.drawString("0", 45, 495);
		
		g.drawString(Integer.toString((int)(m / 2)), 5, 255);
		g.drawLine(55, 250, 65, 250);
		g.drawString(Integer.toString((int)(m)), 5, 40);
		g.drawLine(55, 35, 65, 35);
		g.drawString(Integer.toString((int)(3 * m / 4)), 5, 143);
		g.drawLine(55, 138, 65, 138);
		g.drawString(Integer.toString((int)(m / 4)), 5, 368);
		g.drawLine(55, 363, 65, 363);

		g.drawString(Integer.toString(t), (int) (60 + echelleX * t), 510);
		g.drawLine((int) (60 + echelleX * t)+10, 485, (int) (60 + echelleX * t)+10, 475);
		g.drawString(Integer.toString((int)(3 * t / 4)), (int) (60 + echelleX * ((int)(3 * t / 4))), 510);
		g.drawLine((int) (60 + echelleX * (int)(3 * t / 4))+10, 485, (int) (60 + echelleX * (int)(3 * t / 4))+10, 475);
		g.drawString(Integer.toString(t / 2), (int) (60 + echelleX * (t / 2)), 510);
		g.drawLine((int) (60 + echelleX * t/2)+10, 485, (int) (60 + echelleX * t/2)+10, 475);
		g.drawString(Integer.toString((int)(t / 4)), (int) (60 + echelleX * (t/4)), 510);
		g.drawLine((int) (60 + echelleX * t/4)+10, 485, (int) (60 + echelleX * t/4)+10, 475);
		
		
		//Dessine les courbes
		for(int j = 0 ; j < t1.length ; j++) {
			g.setColor(Couleur[j % 4]);
			for(int i = 0 ; i < t ; i++) {
				g.drawLine((int) (60 + echelleX * i), (int) (480 - echelleY * t1[j][i]), 
						(int) (60 + echelleX * (i + 1)), (int) (480 - echelleY * t1[j][i + 1]));
			}
		}
	}

	//Renvoie le maximum d'un tableau
	private double maxt(int[] T) {
		int m = 0;
		for(int i = 0 ; i < T.length ; i ++) {
			if(T[i] > m) {
				m = T[i];
			}
		}
		return (double) m;
	}
}
