package application;

import java.util.ArrayList;

public class Presence implements Comparable<Presence> {
	public static ArrayList<Presence> listePresence=new ArrayList<>();
	private String nom;
	private String prenom;
	private String date;
	private int ha;
	private int mina;
	private int hd;
	private int mind;
	
	public Presence(String ligne) throws Exception {
		super();
		String[] elements=ligne.split(" ");
		for(String ele : elements) {
			String[] elementVal=ele.split("=");
			String element=elementVal[0];
			String val=elementVal[1];
			
			switch(element) {
			case "Date":
				this.date=val;
				break;
			case "Nom":
				this.nom=val;
				break;
			case "Prenom":
				this.prenom=val;
				break;
			case "Arrivee":
				String[] arriveeParties=val.split(":");
				this.ha=Integer.parseInt(arriveeParties[0]);
				this.mina=Integer.parseInt(arriveeParties[1]);
				if (ha < 0 || ha > 23 || mina < 0 || mina > 59) {
                    throw new Exception("L'heure d'arrivée n'est pas valide.");
                }
				break;
			case "Depart":
				String[] departParties=val.split(":");
				this.hd=Integer.parseInt(departParties[0]);
				this.mind=Integer.parseInt(departParties[1]);
				if (hd < 0 || hd > 23 || mind < 0 || mind > 59) {
                    throw new Exception("L'heure de départ n'est pas valide.");
                }
				
				break;
			default:
				System.out.println("erreur");
				break;
			}
			
		}
		
		listePresence.add(this);
	}
	
	
	 public Presence (String nom, String  prenom, String date, String  heureArrivee) throws Exception {
		 this.nom=nom;
		 this.prenom=prenom;
		 this.date=date;
		 String [] heureParties=heureArrivee.split(":");
		 this.ha=Integer.parseInt(heureParties[0]);
		 this.mina=Integer.parseInt(heureParties[1]);
		 if(!heureArrivee.isEmpty()) {
			 this.ha=Integer.parseInt(heureParties[0]);
			 this.mina=Integer.parseInt(heureParties[1]);
		 }
		 if (ha < 0 || ha > 23 || mina < 0 || mina > 59) {
             throw new Exception("L'heure d'arrivée n'est pas valide.");
         }
		 if (hd < 0 || hd > 23 || mind < 0 || mind > 59) {
             throw new Exception("L'heure de départ n'est pas valide.");
         }
			
		 // Initialisation de hd et mind à -1 pour indiquer qu'ils ne sont pas définis
	        this.hd = -1;
	        this.mind = -1;
		
		 listePresence.add(this);
	 }


	public static ArrayList<Presence> getListePresence() {
		return listePresence;
	}


	public static void setListePresence(ArrayList<Presence> listePresence) {
		Presence.listePresence = listePresence;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getHa() {
		return ha;
	}


	public void setHa(int ha) {
		this.ha = ha;
	}


	public int getMina() {
		return mina;
	}


	public void setMina(int mina) {
		this.mina = mina;
	}


	public int getHd() {
		return hd;
	}


	public void setHd(int hd) {
		this.hd = hd;
	}


	public int getMind() {
		return mind;
	}


	public void setMind(int mind) {
		this.mind = mind;
	}


	@Override
	public int compareTo(Presence o) {
		// TODO Auto-generated method stub
		return this.date.compareTo(o.date);
	}
	
	@Override
	public String toString() {
		return "Date ="+date+ "Nom="+nom+ "Prenom="+prenom+ "Arrivee="+ha+":"+mina+ "Depart="+hd+ ":"+mind;  
	}


	
	
}
