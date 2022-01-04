public class QCM_Final{
/**
 * Programme permettant de remplir, de modifier et de passer un QCM avec une ou plusieurs r�ponses bonnes.
 * lors du passage du test une note final est donn�e et si le test est r�ussi.
 * Obligation de d�clar�e les tableaux dans la m�thode main, pas d'arraylist et pas de POO.
*/
   public static void main (String [] args) {
     String []Questions = new String [150];
     String [][]ReponseChoix = new String [150][150];
     int [][] ReponseBonne = new int [150][150], ReponseTest = new int [150][150];
     int ChoixMenu;
     do{
       ChoixMenu=Menu(Questions);
       if(ChoixMenu==1){
         creationQuestions(Questions, ReponseChoix, ReponseBonne);
       }
       else if(ChoixMenu==2){
         menuModifAjout(Questions,ReponseChoix,ReponseBonne);
       }
       else if(ChoixMenu==3){
         char rep;
         do{
           rep=OuiNon("Etes vous pr�t?");
         }while(rep!='O');
         Test(Questions,ReponseChoix, ReponseTest);
         SuccesEchec(Questions, ReponseBonne, ReponseTest);
       }
       else{
         System.exit(4);
       }
     }while(ChoixMenu!=4);
   }
   
   public static int Menu(String Questions[]){
     int ChoixMenu;
     do{
       System.out.println("------------------MENU---------------");
       System.out.println("1- Remplir questionnaire");
       System.out.println("2- Modifier le questionnaire");
       System.out.println("3- Passer le test.");
       System.out.println("4- Quitter");
       ChoixMenu=lireEntier("Votre choix: ",1,4);
       if(ChoixMenu==3&&Questions[1]==null){
         System.out.println("Vous n'avez pas rempli le questionnaire.");
         System.out.println("Vous devez remplir le questionnaire avant de pouvoir passer le test.");
       }
       else if(ChoixMenu==2&&Questions[1]==null){
         System.out.println("Vous n'avez pas rempli le questionnaire.");
         System.out.println("Vous devez remplir le questionnaire avant de pouvoir le modifier.");
       }
     }while((ChoixMenu==2&&Questions[1]==null)||(ChoixMenu==3&&Questions[1]==null));
     return ChoixMenu;
   }

   public static void creationQuestions (String Questions [], String ReponseChoix[][], int ReponseBonne[][]){
     for (int i=1; i<Questions.length; i++){
       System.out.println("Ecrivez la question " + i + " ci dessous (Appuyez sur entr�e si plus de questions)");
       Questions[i]=Terminal.lireString();
        if (Questions[i].isEmpty()){
         break;
       }
       creationReponses(ReponseChoix[i], ReponseBonne[i]);
     }
   }

   public static void creationReponses(String ReponseChoix[], int ReponseBonne[]){
     int nbreReponse=0;
     for (int j=1; j<ReponseChoix.length; j++) {
       System.out.println("Ecrivez la r�ponse " + j + "(appuyez sur Entr�e si plus de r�ponses )");
       ReponseChoix[j]=Terminal.lireString();
       nbreReponse=nbreReponse+1;
       if (ReponseChoix[j].isEmpty()){
         break;
       }
     }
     creationReponsesBonnes(ReponseBonne, nbreReponse);
   }

   public static void creationReponsesBonnes(int ReponseBonne [], int nbreReponse){
     for (int k=1; k<=ReponseBonne.length; k++){
       System.out.println("Quelle est la ou les bonnes r�ponses � la question ?");
       ReponseBonne[k]=lireEntier("Ecrivez le chiffre de la bonne r�ponse ou d'une des bonnes r�ponses (Appuyez sur 0 si plus de bonne r�ponses)",0,nbreReponse-1);
       if(ReponseBonne[k]==0){
         break;
       }
     }
   }

   public static void menuModifAjout(String Questions[], String ReponseChoix[][], int ReponseBonne[][]){ 
     int choix;
     do{
       System.out.println("------------------MENU---------------");
       System.out.println("1- Modifier");
       System.out.println("2- Ajouter");
       System.out.println("3- Retour");
       choix=lireEntier("Votre choix?",1,3);
       if (choix==1){
         sousmenuModifications(Questions, ReponseChoix, ReponseBonne);
       }
       else if (choix==2){
         sousMenuAjout(Questions, ReponseChoix, ReponseBonne);
       }
     }while (choix!=3);
   }
   
   public static void sousmenuModifications(String Questions[], String ReponseChoix[][], int ReponseBonne[][]){
     int choix;
     do{
       System.out.println("------------------MENU MODIFICATION---------------");
       System.out.println("1-Modifier les questions");
       System.out.println("2-Modifier les r�ponses possibles");
       System.out.println("3- Modifier les r�ponses bonnes");
       System.out.println("4- Retour");
       choix=lireEntier("Votre choix?",1,4);
       if (choix==1){
         modificationQuestions(Questions, ReponseChoix, ReponseBonne);
       }
       else if (choix==2){
         int numQuestion=affichageEtChoixQuestions(Questions);
         modificationReponse(ReponseChoix[numQuestion], ReponseBonne[numQuestion]);
       }
       else if (choix==3){
         int numQuestion=affichageEtChoixQuestions(Questions);
         modificationReponseBonne(ReponseChoix[numQuestion],ReponseBonne[numQuestion]); 
       }
     }while (choix!=4);
   }

   public static void sousMenuAjout(String Questions[], String ReponseChoix[][], int ReponseBonne[][]){
     int choix;
     do{
       System.out.println("------------------MENU AJOUT---------------");
       System.out.println("1- Ajouter des questions");
       System.out.println("2- Ajouter des r�ponses possibles");
       System.out.println("3- Ajouter des r�ponses bonnes");
       System.out.println("4- Retour");
       choix=lireEntier("Votre choix?",1,4);
       if(choix==1){
         ajoutQuestion(Questions, ReponseChoix, ReponseBonne);
       }
       else if (choix==2){
         int numQuestion=affichageEtChoixQuestions(Questions);
         ajoutReponses(ReponseChoix[numQuestion],ReponseBonne[numQuestion]);
       }
       else if (choix==3){
         int numQuestion=affichageEtChoixQuestions(Questions);
         ajoutReponseBonne(ReponseChoix[numQuestion], ReponseBonne[numQuestion]);
       }
     }while (choix!=4);
   }

   public static void modificationQuestions(String Questions[], String ReponseChoix[][], int ReponseBonne[][]){
     int numQuestion, nbreQuestions;
     char repModifQuest='O', repModifRep;
     do{
       numQuestion=affichageEtChoixQuestions(Questions);
       System.out.println("Ecrivez la nouvelle question");
       Questions[numQuestion]=Terminal.lireString();
       repModifRep=OuiNon("Souhaitez vous modifier les r�ponses?");
       if(repModifRep=='O'){
         modificationReponse(ReponseChoix[numQuestion],ReponseBonne[numQuestion]);
       }
       repModifQuest=OuiNon("Souhaitez vous modifier une autre question?");
     }while(repModifQuest!='N');
   }

   public static void modificationReponse(String ReponseChoix[], int ReponseBonne[]){
     int numReponse, nbreReponse=0;
     char repModif='O', repModifRepBonne;
     do{
       for (int i=1; i<ReponseChoix.length; i++){
         if(ReponseChoix[i]==null||ReponseChoix[i].isEmpty()){
           break;
         }
         nbreReponse=i;
         System.out.println(i + "- " + ReponseChoix[i]);
       }
       numReponse=lireEntier("Quelle r�ponse souhaitez vous modifier?",1,nbreReponse);
       System.out.println("Ecrivez la nouvelle r�ponse");
       ReponseChoix[numReponse]=Terminal.lireString();
       repModifRepBonne=OuiNon("Souhaitez vous modifier la/les r�ponses bonnes?");
       if(repModifRepBonne=='O'){
         modificationReponseBonne(ReponseChoix, ReponseBonne);
       }
       repModif=OuiNon("Souhaitre vous modifier une autre r�ponse?");
     } while (repModif!='N');
   }

   public static void modificationReponseBonne(String ReponseChoix[], int ReponseBonne[]){
     char repModif='O';
     int numReponseBonne, nbreReponseBonne=0,nbreReponse=0;
     do{
       for (int i=1; i<ReponseChoix.length; i++){
         if(ReponseChoix[i]==null||ReponseChoix[i].isEmpty()){
           break;
         }
         nbreReponse=i;
         System.out.println(i + "- " + ReponseChoix[i]);
       }
       for(int i=1; i<ReponseBonne.length; i++){
         if(ReponseBonne[i]==0){
           break;
         }
         nbreReponseBonne=i;
         System.out.println(i + "- " + ReponseBonne[i]);
       }
       numReponseBonne=lireEntier("Quelle r�ponse bonne souhaitez vous modifier?",1,nbreReponseBonne);
       ReponseBonne[numReponseBonne]=lireEntier("Entrez la nouvelle r�ponse bonne",1,nbreReponse);
       repModif=OuiNon("Souhaitez vous modifier une autre bonne r�ponse?");
     }while(repModif!='N');
   }

   public static void ajoutQuestion(String Questions[],String ReponseChoix[][], int ReponseBonne[][]){
     int nbreQuestions=0; 
     char repAjout='O';
     do{
        for (int i=1; i<Questions.length; i++){
         if(Questions[i]==null||Questions[i].isEmpty()){
           break;
         }
         nbreQuestions=i;
         System.out.println(i + "- " + Questions[i]);
       }
       nbreQuestions+=1;
       System.out.println("Ecrivez votre questions");
       Questions[nbreQuestions]=Terminal.lireString();
       creationReponses(ReponseChoix[nbreQuestions], ReponseBonne[nbreQuestions]);
       repAjout=OuiNon("Souhaitez vous ajouter une autre question?");
     }while (repAjout!='N');
   }
 
   public static void ajoutReponses(String ReponseChoix [],int ReponseBonne []){
     char repAjout='O', repBonneRep;
     int nbreReponseBonne=0, nbreReponse=0;
     do{
       for (int i=1; i<ReponseChoix.length; i++){
         if(ReponseChoix[i]==null||ReponseChoix[i].isEmpty()){
           break;
         }
         nbreReponse=i;
         System.out.println(i + "- " + ReponseChoix[i]);
       }
       nbreReponse+=1;
       System.out.println("Ecrivez la r�ponse");
       ReponseChoix[nbreReponse]=Terminal.lireString();
       repBonneRep=OuiNon("Souhaitez vous ajouter cette r�ponse dans les bonnes r�ponses?");
       if(repBonneRep=='O'){
         for (int i=1; ReponseBonne[i]==0; i++){
           nbreReponseBonne=i;
         }
         nbreReponseBonne+=1;
         ReponseBonne[nbreReponseBonne]=nbreReponse;
       }
       repAjout=OuiNon("Souhaitez vous ajouter une r�ponse?");
     }while(repAjout!='N');
   }
 
   public static void ajoutReponseBonne(String ReponseChoix[],int ReponseBonne[]){
     int nbreReponse=0, nbreReponseBonne=0;
     char repAjout='O';
       do{
         for (int i=1; i<ReponseChoix.length; i++){
           if(ReponseChoix[i]==null||ReponseChoix[i].isEmpty()){
             break;
           }
           nbreReponse=i;
           System.out.println(i + "- " + ReponseChoix[i]);
         }
         System.out.println("La/les r�ponses bonnes:");
         for (int i=1; i<ReponseBonne.length; i++){
           if(ReponseBonne[i]==0){
             break;
           }
           nbreReponseBonne=i;
           System.out.println("- " + ReponseBonne[i]);
         }
         nbreReponseBonne+=1;
         System.out.println("Ecrivez la r�ponse");
         ReponseBonne[nbreReponseBonne]=lireEntier("Ecrivez la bonne r�ponse",1,nbreReponse);
         repAjout=OuiNon("Souhaitez vous ajouter une r�ponse bonne?");
       }while(repAjout!='N');
     }

   public static void Test (String Questions[], String ReponseChoix[][], int ReponseTest[][]){
     int NbreReponse=0;
     for(int i=1; i<Questions.length; i++){
       if(Questions[i]==null||Questions[i].isEmpty()){
         break;
       }
       System.out.println("__ " + Questions[i]);
       for(int j=1; j<ReponseChoix[i].length; j++){
         if (ReponseChoix[i]==null||ReponseChoix[i][j].isEmpty()){
           break;
           }
         System.out.println(j + "- " + ReponseChoix[i][j]);
         NbreReponse=NbreReponse+1;
       }
       for (int j=1; j<ReponseTest[i].length; j++){
         ReponseTest[i][j]=lireEntier("Entrez le chiffre de la ou des bonnes r�ponses (Pressez sur 0 si plus de r�ponses)",0,NbreReponse);
         if (ReponseTest[i][j]==0){
           break;
         }
       }
     } 
   }
   /**
    * M�thode qui permet d'obtenir le total de r�ponse et qui r�cup�re les points obtenues.
    * la m�thode affiche le nbre de point et si l'�valu� a r�ussi ou �chou�.
    */
   public static void SuccesEchec( String Questions[], int ReponseBonne[][], int ReponseTest[][]){
     int TotalReponse=0;
     double Points;
     for(int i=1; i<ReponseBonne.length; i++){
       if(Questions[i]==null||Questions[i].isEmpty()){
         break;
       }
       for(int j=1; j<ReponseBonne[i].length; j++){
         if(ReponseBonne[i][j]==0){
           break;
         }
         TotalReponse=TotalReponse+1;
       }
     }
     Points=Points(Questions, ReponseBonne, ReponseTest);
     if(Points>=TotalReponse/2){
       System.out.println("Vous avez r�ussi le test, vous avez " + Points + " points");
     }else{
       System.out.println("Vous avez �chou� le test, vous avez " + Points + " points");
     }
   }
   /**
    * M�thode qui r�cup�re les points de comparaison
    * elle scan le tableau r�ponse test et pour toute valeur sup�rieur � z�ro on retire 0,5 points.
    */
   public static double Points (String Questions[], int ReponseBonne[][], int ReponseTest[][]){
     double points=0; 
     points=Comparaison(Questions, ReponseBonne, ReponseTest);
     for (int x=1; x<Questions.length; x++){
       for (int y=1; y<ReponseBonne[x].length; y++){
         if (ReponseTest[x][y]>0){
           points-=0.5;
         }
       }
     } return points;
   }
   /**
    * M�thode qui permet de comparer les r�ponses du test avec les r�ponses bonnes.
    * Si la r�ponse test est �gale � la r�ponse bonne alors on ajoute un point de comparaison
    * et on r�initialise la r�ponse test � z�ro.
    * on renvoie les points de comparaison.
    */
   public static int Comparaison( String Questions[], int ReponseBonne[][], int ReponseTest[][]){
     int Comparaison=0;
     int ExtractionRB,ExtractionRT;
     for (int i=1; i<Questions.length; i++){
       if (Questions[i]==null||Questions[i].isEmpty()){
         break;
       }
       for (int j=1; j<ReponseBonne[i].length; j++){
         if (ReponseBonne[i][j]==0){
           break;
         }
         ExtractionRT=ReponseTest[i][j];
         for(int k=1; k<ReponseBonne[i].length; k++){
           ExtractionRB=ReponseBonne[i][k];
           if(ExtractionRB==ExtractionRT&&ReponseBonne[i][k]>0){
             ReponseTest[i][j]=0;
             Comparaison=Comparaison+1;
           }
           else {
             Comparaison=Comparaison+0;
           }
         }
       } 
     }return Comparaison;
   }
   /**
    * m�thode d'affichage des questions et retour de la question que l'on souhaite modifier
    */
   public static int affichageEtChoixQuestions(String Questions[]){
     int numQuestion, nbreQuestions=0;
     for (int i=1; i<Questions.length; i++){
         if(Questions[i]==null||Questions[i].isEmpty()){
           break;
         }
         nbreQuestions=i;
         System.out.println(i + "- " + Questions[i]);
       }
     numQuestion=lireEntier("Quelle question souhaitez vous modifier?",0,nbreQuestions);
     return numQuestion;
   }
   
   /**
    * M�thode qui permet de lire un entier comppris entre un minimum et un maximum.
    * Si la r�ponse n'est pas un entier ou compris entre min et max alors on repose la question.
    */
   public static int lireEntier(String msg, int Min, int MaxReponse) {
     int entier=0, NbreErreur=0;
     boolean correct;
     do{
       try{
         System.out.println(msg);
         entier=Terminal.lireInt();
         correct=true;
         if (entier<Min || entier>MaxReponse){
           Terminal.ecrireString("vous avez que " + MaxReponse + " choix. ");
           correct=false;
         }
       }
       catch(TerminalException e){
         correct = false;
         NbreErreur+=1;
         if (NbreErreur<3){
           System.out.println("Vous avez entr� autre chose qu'un entier. ");
         }
         if (NbreErreur>3){
           System.out.println("Toujours pas. un chiffre sans virgule s'il vous plait?");
         }
       }
     }
     while (!correct);
     return entier;
   }
/**
 * m�thode qui permet de lire uniquement si une personne mets O ou N, 
 */
   public static char OuiNon (String msg){
     char rep;
     boolean OuiouNon=false;
     do{
       System.out.println(msg + "(O/N)");
       rep=Terminal.lireChar();
       if (rep=='O'){
         OuiouNon=true;
       }
       else if(rep=='N'){
         OuiouNon=true;
       }
     }while (!OuiouNon);
     return rep;
   } 
}