package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MonControlleur {
	private int indiceCourant=0;
	@FXML
	private Button btAjouterHD;

	@FXML
	private Label lbindice;

	@FXML
	public Label lbinfo;

	@FXML
	private TextField tfDate;

	@FXML
	private TextField tfHeureArrivee;

	@FXML
	private TextField tfHeureDepart;

	@FXML
	private TextField tfNomPrenom;

	private Presence p;

	public static ArrayList<Presence> listePresence=new ArrayList<>();




	@FXML
	void initialize() {
		System.out.println("Démarage");
		//actionAjouter();
	}



	@FXML
	void actionAjouterHA(ActionEvent evt) throws Exception {
		System.out.println("actionAjouterHA");

		try {
			String nom_Prenom = tfNomPrenom.getText() ;


			String dateRecu = tfDate.getText();
			String heureArrivee = tfHeureArrivee.getText();

			String[] np = nom_Prenom.split(" ");
			String nom = np[0];
			String prenom = np[1];

			indiceCourant++;
			p = new Presence(nom, prenom, dateRecu, heureArrivee );

			//cpt=Presence.listePresence.size();

			//Presence.listePresence.add(p);

			int i= Presence.listePresence.indexOf(p);

			lbinfo.setText("Ajouter heure d'arrivée");

			//ibIndice.setText(String.format("%02d/%02d", cpt+1,cmp));
			lbindice.setText(String.format("%02d/%02d", indiceCourant,Presence.listePresence.size() ));
			System.out.println("OK");    
			btAjouterHD.setDisable(false);
			tfNomPrenom.setDisable(true);

		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			lbinfo.setText(e.getMessage());
		}



	}

	@FXML
	void actionAjouterHD(ActionEvent evt) throws Exception {
		System.out.println("Heure départ");
		try {
			String heureDepart = tfHeureDepart.getText();

			String[] hd = heureDepart.split(":");
			int hdi = Integer.parseInt(hd[0]);
			int mdi = Integer.parseInt(hd[1]);
			if(hdi< Presence.listePresence.get(indiceCourant-1).getHa()) {
				throw new Exception("Heure superieur");    
			}
			if(hdi>= Presence.listePresence.get(indiceCourant-1).getHa() ) {
				if(mdi < Presence.listePresence.get(indiceCourant-1).getMina() )
					throw new Exception("Minute superieur");
			}
			p.setHd(hdi);
			p.setMina(mdi);

			lbinfo.setText("Ajouter heure de départ");
			//ibIndice.setText(String.format("%02d/%02d", indiceCourant,Presence.listePresence.size() ));
			tfDate.setText(null);
			tfHeureArrivee.setText(null);
			tfHeureDepart.setText(null);
			btAjouterHD.setDisable(true);
		} catch (NumberFormatException e) {
			lbinfo.setText("ERREUR");
		}

	}

	@FXML
	public void actionAjouter() throws Exception  {
		try {
			// Ajouter une nouvelle présence à la liste
			String nomPrenom = tfNomPrenom.getText();
			String date = tfDate.getText();
			String heureArrivee = tfHeureArrivee.getText();
			String heureDepart = tfHeureDepart.getText();

			// Créer un nouvel objet Presence
			Presence nouvellePresence = new Presence(nomPrenom, date, heureArrivee, heureDepart);

			// Ajouter la nouvelle présence à la liste
			listePresence.add(nouvellePresence);

			// Mettre à jour le libellé lbIndice
			int tailleListe = listePresence.size();
			lbindice.setText(String.format("%02d/%02d", tailleListe, tailleListe));

			// Réinitialiser les champs après ajout
			tfNomPrenom.clear();
			tfDate.clear();
			tfHeureArrivee.clear();
			tfHeureDepart.clear();
			lbinfo.setText("Présence ajoutée avec succès.");
		} catch (Exception e) {
			// Afficher un message d'erreur en cas d'exception
			lbinfo.setText("Erreur lors de l'ajout de la présence: " + e.getMessage());
		}
	}


	@FXML
	void actionApres(ActionEvent evt) {
		System.out.println("actionApres");
		indiceCourant++; // Incrémenter l'indice si possible

		if(indiceCourant> Presence.listePresence.size())
			indiceCourant = 1;

		afficherPresence(listePresence.get(indiceCourant-1));

		// Mettre à jour le libellé lbIndice
		lbindice.setText(String.format("%02d/%02d", indiceCourant + 1, listePresence.size()));
		
	}


	@FXML
	void actionAvant(ActionEvent evt) {
		System.out.println("actionAvant");


		indiceCourant--; // Décrémenter l'indice si possible

		if(indiceCourant==0)
			indiceCourant = Presence.listePresence.size();
		afficherPresence(listePresence.get(indiceCourant-1));

		// Mettre à jour le libellé lbIndice
		lbindice.setText(String.format("%02d/%02d", indiceCourant , listePresence.size()));
	}


// Méthode pour afficher les détails d'une présence
private void afficherPresence(Presence presence) {
	tfNomPrenom.setText(presence.getNom());
	tfDate.setText(presence.getDate());
	tfHeureArrivee.setText(String.format("%02d:%02d", presence.getHa(), presence.getMina()));
	tfHeureDepart.setText(String.format("%02d:%02d", presence.getHd(), presence.getMind()));
}

}
