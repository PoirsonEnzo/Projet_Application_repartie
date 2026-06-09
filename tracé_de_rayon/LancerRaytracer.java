import java.time.Instant;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;

import raytracer.Disp;
import raytracer.Scene;

public class LancerRaytracer {

    public static String aide = "Raytracer : synthèse d'image par lancé de rayons (https://en.wikipedia.org/wiki/Ray_tracing_(graphics))\n\nUsage : java LancerRaytracer [fichier-scène] [largeur] [hauteur]\n\tfichier-scène : la description de la scène (par défaut simple.txt)\n\tlargeur : largeur de l'image calculée (par défaut 512)\n\thauteur : hauteur de l'image calculée (par défaut 512)\n";
     
    public static void main(String args[]){

        // Le fichier de description de la scène si pas fournie
        String fichier_description="simple.txt";

        // largeur et hauteur par défaut de l'image à reconstruire
        int largeur = 512, hauteur = 512;

        
        if(args.length > 0){
            fichier_description = args[0];
            if(args.length > 1){
                largeur = Integer.parseInt(args[1]);
                if(args.length > 2)
                    hauteur = Integer.parseInt(args[2]);
            }
        }else{
            System.out.println(aide);
        }
        
   
        // création d'une fenêtre 
        Disp disp = new Disp("Raytracer", largeur, hauteur);
        
        // Initialisation d'une scène depuis le modèle 
        Scene scene = new Scene(fichier_description, largeur, hauteur);
        
        // Calcul de l'image de la scène les paramètres : 
        // - x0 et y0 : correspondant au coin haut à gauche
        // - l et h : hauteur et largeur de l'image calculée
        // Ici on calcule toute l'image (0,0) -> (largeur, hauteur)
        
        int x0 = 0, y0 = 0;
        int l = largeur, h = hauteur;
                
        // Chronométrage du temps de calcul
        Instant debut = Instant.now();
        System.out.println("Calcul de l'image :\n - Coordonnées : "+x0+","+y0
                           +"\n - Taille "+ largeur + "x" + hauteur);
        /* 
        Image image = scene.compute(x0, y0, l/2, h/2);
        Image image2 = scene.compute(x0+l/2, y0+h/2,l-l/2,h-h/2);
        Image image3 = scene.compute(x0+l/2, y0,l-l/2,h);
        Image image4 = scene.compute(x0, y0+h/2,l,h-h/2);
        
        */
       try {
            Registry annuaire = LocateRegistry.getRegistry(args[0],1099);
            PointCentral central = (PointCentral) annuaire.lookup("distributeur");
    // Définition de la grille de découpage (3x3 = 9 blocs)
            int nbColonnes = 3; 
            int nbLignes = 3;
            
            int largeurBloc = l / nbColonnes;
            int hauteurBloc = h / nbLignes;

            // Double boucle pour parcourir la grille en X et en Y
            for(int ligne = 0; ligne < nbLignes; ligne++) {
                for(int colonne = 0; colonne < nbColonnes; colonne++) {
                    
                    // Calcul des coordonnées de départ pour le bloc courant
                    int departX = x0 + (colonne * largeurBloc);
                    int departY = y0 + (ligne * hauteurBloc);
                    
                    // Ajustement pour le dernier bloc de la ligne/colonne au cas où 
                    // la largeur/hauteur ne serait pas un multiple parfait de 3
                    int wCourant = (colonne == nbColonnes - 1) ? (l - departX) : largeurBloc;
                    int hCourant = (ligne == nbLignes - 1) ? (h - departY) : hauteurBloc;
                    
                    ServiceNoeud noeud = central.getNoeud();
                    
                    // On passe les bonnes coordonnées et tailles au thread
                    ThreadDessin t = new ThreadDessin(central, disp, noeud, scene, departX, departY, wCourant, hCourant);
                    t.start();
                }
            }
            Instant fin = Instant.now();
            long duree = Duration.between(debut, fin).toMillis();
            
            System.out.println("Image calculée en :"+duree+" ms");
        } catch (Exception e) {
            e.printStackTrace();
        }	
    }
}
