package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois gaulois= new Gaulois("solo",5);
		try {
		etal.acheterProduit(2,gaulois );
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		} catch(IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
		}
}
