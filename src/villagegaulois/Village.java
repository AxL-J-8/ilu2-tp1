package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

    private static class Marche{
    	private Etal[] etals;
    	private int  nbEtalMax=0;

    	public Marche(int nbEtal) {
    		this.etals=new Etal[nbEtal];
    		this.nbEtalMax=nbEtal;
    	}
    	 private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
    		 if (indiceEtal>-1) {
    		 etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);}
    	 }
    	 int trouverEtalLibre() {
    		 int result=-1;
    		 int i =0;
             if (etals[i]!=null) {
    		 while(result==-1&& i<nbEtalMax) {
    			if (!(etals[i].isEtalOccupe())){
    				result=i;
    			}
    			i++;
    		}}
    		 return result;
    		 
    	 }
    	 private Etal[] trouverEtals(String produit) {
    		 Etal[] liste_etal=new Etal[nbEtalMax];
    		 int i_liste=0;
    		 for (int i=0;i<nbEtalMax;i++) {
    			 if (etals[i].contientProduit(produit)) {
    				 liste_etal[i_liste++]=etals[i];
    			 }
    		 }
    		 return liste_etal;
    	 }
    	 
    	 private Etal trouverVendeur(Gaulois gaulois) {
    		Etal result=null;
    		boolean trouve=false;
    		int i =0;
    		while (i<nbEtalMax&&!trouve) {
    			if (gaulois.equals(etals[i].getVendeur())) {
    				result=etals[i];
    				trouve=true;
    			}
    			i++;
    		}
    		return result;
    		}
    	 
    	private String afficherMarche() {
    		 StringBuilder chaine = new StringBuilder();
    		 int nbEtalVide=0;
    		 for (int i=0;i<nbEtalMax;i++) {
    			 if(etals[i].isEtalOccupe()) {
    			 chaine.append(etals[i].afficherEtal());
    			 }
    			 else {
    				 nbEtalVide++;
    			 }
    		 }
    		 chaine.append("Il reste"+ nbEtalVide +" étals non utilisés dans le marché.\n");
    		 return chaine.toString();
    	 }
    	 
    }
    
    
	public Village(String nom, int nbVillageoisMaximum,int nbEtalMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche=new Marche(nbEtalMarche);
	}
	public String installerVendeur(Gaulois vendeur,String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int noEtal= marche.trouverEtalLibre();
		String nomVendeur=vendeur.getNom();
		chaine.append(nomVendeur+" cherche un endroit pour vendre "+nbProduit +" "+produit +"\n");
		marche.utiliserEtal(noEtal, vendeur, produit, nbProduit);
		chaine.append("Le vendeur "+ nomVendeur+ "vends des " +produit+ "a letal n°"+noEtal+"\n");
		return chaine.toString();
	}
	 public String rechercherVendeursProduit(String produit) {
		 StringBuilder chaine = new StringBuilder();
		 Etal[] l_etal = marche.trouverEtals(produit);
		 int i=0;
		 chaine.append("les vendeurs qui proposent des "+produit+"sont\n");
		while(l_etal[i].isEtalOccupe()) {
			chaine.append("- "+l_etal[i].getVendeur().getNom()+"\n");
			i++;
		}
		return chaine.toString();
	 }
	 
	 public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	 }
	 public String partirVendeur(Gaulois vendeur) {
	    return marche.trouverVendeur(vendeur).libererEtal();
	 }
	 
	 public String afficherMarche() {
		 StringBuilder chaine = new StringBuilder();
		 chaine.append("Le marché du village "+"'"+nom+"' possede plusieurs étals : \n"+marche.afficherMarche());
		return chaine.toString();
	 }

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}