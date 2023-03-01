
package services;

import Entités.Boutique;
import javafx.collections.ObservableList;


public interface IboutiqueCRUD {
    public void ajouterBoutique(Boutique b);
     public void modifierBoutique(Boutique b);
     public void supprimerBoutique(Boutique b);
     public ObservableList<Boutique> afficherBoutiques();
     
    
}
