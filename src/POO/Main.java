package POO;


public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        initCartes();
    }
    
    //  La fonction retourne une valeur comprise entre min et max
    private int auHasardEntre(int min, int max){
        double t=Math.random();
        int resultat = (int) ((max-min)*t + min);
        return resultat;
    }
    
    // Mélanger les cartes
    private void melanger(){
        int alea;
        Carte tmp;
        // Parcourir la liste des cartes
        for(int i=0; i<listeCartes.length; i++){
          // Tirer un nombre au hasard entre 0 et le nombre de cartes
          alea = auHasardEntre(0, listeCartes.length);
          // Echanger la carte d'indice i avec celle placée à l'indice correspondant à la valeur tirée au hasard
          tmp = listeCartes[i];
          listeCartes[i] = listeCartes[alea];
          listeCartes[alea] = tmp;
        }
    }
    
    //Initialiser le jeu de carte
    private void initCartes()
    {
            // Créer un tableau d'objets de type Carte
           listeCartes = new Carte [nbCartes];
           int cptr = 0;
           // Répéter autant de fois qu'il y a de carte
           for(int i = 0; i < nbCartes; i++)
           {
               // Créer une carte avec la photo de face, de dos et son id
               listeCartes[i] = new Carte("./Photos/carte"+cptr+".png", "./Photos/dos.png", cptr);
               // compter les photos créées
               cptr++;
               // Si on arrive à la moitié des photos créées, réinitialisée le compteur pour créer un second jeu de 24 cartes
               if(cptr == nbCartes/2)
               {
                   cptr = 0;
               }
           }
           melanger();
           afficherLesCartesAvecEvt();
    }
    
    // Afficher les cartes en y ajoutant un écouteur d'événement
    private void afficherLesCartesAvecEvt(){
         for (int i=0; i  < nbCartes; i++) {
             // Placer les cartes dans le Panel boitePhotos
              boitePhotos.add(listeCartes[i]);
              // Ajouter un écouteur d'événements sur chaque carte de la liste
              listeCartes[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    // Lorsque la carte reçoit l'événement mousReleased
                     public void mouseReleased(java.awt.event.MouseEvent evt) {
                        // exécuter la méthode :
                        carteMouseReleased(evt);
                     }
               });
         }
    }


    
    
    // Actions à mener à l'écoute de l'événement mousReleased
    private void carteMouseReleased(java.awt.event.MouseEvent evt) {
         // Mémoriser la carte cliquée
         carteCliquee = (Carte) evt.getSource();
         // Si le drapeau aRetourner est vrai, c'est le 3ieme clic
         // les cartes doivent être retournées, si elles sont différentes
         if(aRetourner){
               premiere.tournerVersDos();
               seconde.tournerVersDos();
               aRetourner = false;
         }
         // Si c'est le premier clic
         if(nbClick ==0){
             //le clic vaut maintenant 1
             nbClick = 1;
             // Mémoriser la premiere carte et la tourner
             premiere = carteCliquee;
             premiere.tournerVersFace();
             // Supprimer l'écouteur d'evt pour qu'elle ne puisse plus être retournée
             premiere.removeMouseListener(premiere.getMouseListeners()[0]);
         } else {
             // Si c'est le second clic
             if (nbClick == 1){
                 nbClick = 0;
                 // Mémoriser la seconde carte et la tourner
                 seconde = carteCliquee;
                 seconde.tournerVersFace();
                 // Si les cartes sont différentes
                 if( seconde.getId() != premiere.getId()){
                      // Mettre le drapeau aRetourner à vrai pour retourner les cartes au prochain clic
                     aRetourner = true;
                     // Remettre un écouteur sur la premier carte vue que la second n'est pas identique, pour que l'on puisse cliquer à nouveau dessus
                     premiere.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseReleased(java.awt.event.MouseEvent evt) {
                            carteMouseReleased(evt);
                        }
                     });
                  }
                  else { // Si les cartes sont identiques
                       // Supprimer l'écouteur d'evt de la seconde carte  pour qu'elle ne puisse plus être retournée
                       seconde.removeMouseListener(seconde.getMouseListeners()[0]);
                       // Compter le nombre de paires trouvées
                       nbPaires+=2;
                       // Si le nombre de paires trouvées vaut le nombre de cartes, la partie est finie
                       if (nbPaires == nbCartes){
                          // Afficher la boite de dialogue pour proposer une nouvelle partie
                          new Message(this, true);
                          // Retourner toutes les cartes
                          retournerToutesLesCartes();
                          // Les effacer
                          boitePhotos.removeAll();
                          // Et les réafficher
                          initCartes();
                          nbPaires = 0;                
                       }
                  }
             }
         }


    }
    // Retourner toutes les cartes
    private void retournerToutesLesCartes(){
       // Parcourir la liste des cartes et les tourner vers le dos
       for (int i=0; i  < nbCartes; i++) {
              listeCartes[i].tournerVersDos();
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boitePhotos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(824, 450));
        setResizable(false);

        boitePhotos.setBorder(javax.swing.BorderFactory.createTitledBorder("Une carte se retourne"));
        boitePhotos.setMinimumSize(new java.awt.Dimension(470, 364));
        boitePhotos.setPreferredSize(new java.awt.Dimension(470, 364));
        boitePhotos.setLayout(new java.awt.GridLayout(4, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boitePhotos, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boitePhotos, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        boitePhotos.getAccessibleContext().setAccessibleName("  Trouver les paires de cartes   ");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private Carte [] listeCartes;
    private static int nbCartes = 48;
    Carte carteCliquee, premiere, seconde;
    boolean aRetourner = false;
    int nbClick=0;
    int nbPaires = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boitePhotos;
    // End of variables declaration//GEN-END:variables
}
