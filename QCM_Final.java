public class QCM_Final{
/**
 * Programme permettant de remplir, de modifier et de passer un QCM avec une ou plusieurs réponses bonnes.
 * lors du passage du test une note final est donnée et si le test est réussi.
 * Obligation de déclarée les tableaux dans la méthode main, pas d'arraylist et pas de POO.
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
         modificationQuestions(Questions,ReponseChoix,ReponseBonne);
       }
       else if(ChoixMenu==3){
         char rep;
         do{
           rep=OuiNon("Etes vous prêt?");
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
       System.out.println("Ecrivez la question " + i + " ci dessous (Appuyez sur entrée si plus de questions)");
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
       System.out.println("Ecrivez la réponse " + j + "(appuyez sur Entrée si plus de réponses )");
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
       System.out.println("Quelle est la ou les bonnes réponses à la question ?");
       ReponseBonne[k]=lireEntier("Ecrivez le chiffre de la bonne réponse ou d'une des bonnes réponses (Appuyez sur 0 si plus de bonne réponses)",0,nbreReponse-1);
       if(ReponseBonne[k]==0){
         break;
       }
     }
   }
   
   public static void modificationQuestions(String Questions[], String ReponseChoix[][], int ReponseBonne[][]){
     int numQuestion, nbreQuestions=0;
     char repModif;
     for (int i=1; i<Questions.length; i++){
       if (Questions[i].isEmpty()){
         break;
       }
       nbreQuestions+=1;
       System.out.println(i + "- " + Questions[i]);
     }
     ajoutQuestion(Questions, ReponseChoix, ReponseBonne, nbreQuestions);
     numQuestion=lireEntier("Quelle question souhaitez vous modifier?",0,nbreQuestions);
     System.out.println("Ecrivez la nouvelle question");
     Questions[numQuestion]=Terminal.lireString();
     repModif=OuiNon("Souhaitez vous modifier les réponses?");
     if(repModif=='O'){
       modificationReponse(ReponseChoix[numQuestion], ReponseBonne[numQuestion]);
     }
   }
   public static void ajoutQuestion(String Questions[],String ReponseChoix[][], int ReponseBonne[][], int nbreQuestions){
     char repAjout;
     do{
       repAjout=OuiNon("Souhaitez vous ajouter une question?");
       nbreQuestions+=1;
       System.out.println("Ecrivez votre questions");
       Questions[nbreQuestions]=Terminal.lireString();
       creationReponses(ReponseChoix[nbreQuestions], ReponseBonne[nbreQuestions]);
       for (int i=1; i<Questions.length; i++){
         if (Questions[i].isEmpty()){
           break;
         }
         nbreQuestions+=1;
         System.out.println(i + "- " + Questions[i]);
       }
     }while (repAjout=='O');
   }
   public static void modificationReponse(String ReponseChoix[], int ReponseBonne[]){
     int numReponse, nbreReponse=0;
     char repModif, repAjout;
     for (int i=1; i<ReponseChoix.length; i++){
       if(ReponseChoix[i].isEmpty()){
         break;
       }
       nbreReponse+=1;
       System.out.println(i + "- " + ReponseChoix[i]);
     }
     ajoutReponses(ReponseChoix, ReponseBonne, nbreReponse);
     for (int i=0; i<=nbreReponse; i++){
       numReponse=lireEntier("Quelle réponse souhaitez vous modifier? (appuyer sur 0 si fini)",0,nbreReponse);
       if(numReponse==0){
         break;
       }
       System.out.println("Ecrivez la nouvelle réponse");
       ReponseChoix[numReponse]=Terminal.lireString();
     }
     repModif=OuiNon("Souhaitez vous modifier la/les réponses bonnes?");
     if(repModif=='O'){
       modificationReponseBonne(ReponseBonne);
     }
   }
   public static void ajoutReponses(String ReponseChoix [],int ReponseBonne [], int nbreReponse){
     char repAjout;
     do{
       repAjout=OuiNon("Souhaitez vous ajouter une réponse?");
       nbreReponse+=1;
       System.out.println("Ecrivez la réponse");
       ReponseChoix[nbreReponse]=Terminal.lireString();
       for (int i=1; i<ReponseChoix.length; i++){
         if(ReponseChoix[i].isEmpty()){
           break;
         }
         nbreReponse+=1;
         System.out.println(i + "- " + ReponseChoix[i]);
       }
     }while(repAjout=='O');
   }
     
   
   public static void modificationReponseBonne(int ReponseBonne[]){
     int numReponseBonne, nbreReponseBonne=0;
     for(int i=1; i<=ReponseBonne.length; i++){
       if(ReponseBonne[i]==0){
         break;
       }
       nbreReponseBonne+=1;
       System.out.println(i + "- " + ReponseBonne[i]);
     }
     for (int i=1; i<=nbreReponseBonne; i++){
       numReponseBonne=lireEntier("Quelle réponse bonne souhaitez vous modifier?(Appuyez sur 0 si fini)",0,nbreReponseBonne);
       if (numReponseBonne==0){
         break;
       }
       System.out.println("Ecrivez la nouvelle reponse bonne?");
       ReponseBonne[numReponseBonne]=Terminal.lireInt();
     }
   }
   
   public static void Test (String Questions[], String ReponseChoix[][], int ReponseTest[][]){
     int NbreReponse=0;
     for(int i=1; i<Questions.length; i++){
       if(Questions[i].isEmpty()){
         break;
       }
       System.out.println("__ " + Questions[i]);
       for(int j=1; j<ReponseChoix[i].length; j++){
         if (ReponseChoix[i][j].isEmpty()){
           break;
           }
         System.out.println(j + "- " + ReponseChoix[i][j]);
         NbreReponse=NbreReponse+1;
       }
       for (int j=1; j<ReponseTest[i].length; j++){
         ReponseTest[i][j]=lireEntier("Entrez le chiffre de la ou des bonnes réponses (Pressez sur 0 si plus de réponses)",0,NbreReponse);
         if (ReponseTest[i][j]==0){
           break;
         }
       }
     } 
   }
   
   public static void SuccesEchec( String Questions[], int ReponseBonne[][], int ReponseTest[][]){
     int TotalReponse=0;
     double Points;
     for(int i=1; i<ReponseBonne.length; i++){
       if(Questions[i].isEmpty()){
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
       System.out.println("Vous avez réussi le test, vous avez " + Points + " points");
     }else{
       System.out.println("Vous avez échoué le test, vous avez " + Points + " points");
     }
   }
   
   public static double Points (String Questions[], int ReponseBonne[][], int ReponseTest[][]){
     double points=0; 
     points=Comparaison(Questions, ReponseBonne, ReponseTest);
     for (int x=1; x<Questions.length; x++){
       for (int y=1; y<ReponseBonne[x].length; y++){
         if (ReponseTest[x][y]>0){
           points=points-1;
         }
       }
     } return points;
   }
   
   public static int Comparaison( String Questions[], int ReponseBonne[][], int ReponseTest[][]){
     int Comparaison=0;
     int ExtractionRB,ExtractionRT;
     for (int i=1; i<Questions.length; i++){
       if (Questions[i].isEmpty()){
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
  * Méthode qui permet de lire un entier comppris entre un minimum et un maximum.
  * Si la réponse n'est pas un entier ou compris entre min et max alors on repose la question.
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
           System.out.println("Vous avez entré autre chose qu'un entier. ");
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
 * méthode qui permet de lire uniquement si une personne mets Y, 
 * toute autre réponses entrainera une repose de la question
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