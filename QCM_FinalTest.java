public class QCM_FinalTest{
/**
 * méthode qui permet de lire uniquement si une personne mets Y, 
 * toute autre réponses entrainera une repose de la question
*/
  public static void Début (){
    char rep;
    do{
      System.out.println("Etes vous pret?(Y/N)");
      rep=Terminal.lireChar();
    }while (rep!='Y');
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
 * Méthode d'affectation des questions, des choix des réponses et de la/les  bonne(s) réponse(s) à la question 
*/
  public static void QuestionReponse (String Questions [], String ReponseChoix[][], int ReponseBonne[][]){
    int nbreReponse=0;
    for (int i=0; i<Questions.length; i++){
      System.out.println("Ecrivez la question ci dessous (Appuyez sur entrée si plus de questions)");
      Questions[i]=Terminal.lireString();
      if (Questions[i].isEmpty()){
        break;
      }
      for (int j=1; j<ReponseChoix[i].length; j++) {
        System.out.println("Ecrivez la réponse " + j + "(appuyez sur Entrée si plus de réponses )");
        ReponseChoix[i][j]=Terminal.lireString();
        nbreReponse=nbreReponse+1;
        if (ReponseChoix[i][j].isEmpty()){
          break;
        }
      }
      for (int j=1; j<ReponseChoix[i].length; j++){
        System.out.println("Quelle est la ou les bonnes réponses à la question ?");
        ReponseBonne[i][j]=lireEntier("Ecrivez le chiffre de la bonne réponse ou d'une des bonnes réponses (Appuyez sur 0 si plus de bonne réponses)",0,nbreReponse-1);
        if(ReponseBonne[i][j]==0){
          break;
        }
      }
    }
  }
/** 
 * Méthode qui permet de poser les questions et d'obtenir les différentes réponses.
*/
  public static void Test (String Questions[], String ReponseChoix[][], int ReponseTest[][]){
    int NbreReponse=0;
    for(int i=0; i<Questions.length; i++){
      if(Questions[i].isEmpty()){
        break;
      }
      System.out.println(Questions[i]);
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
/**
 * Méthode de comparaison entre deux valeurs si elles sont indentiques celle du test se réinitialise et ajoute 1 pts
 * si elles sont différentes elles ne se réinitialise pas.
*/
  public static int Comparaison( String Questions[], int ReponseBonne[][], int ReponseTest[][]){
  int Comparaison=0;
  int ExtractionRB,ExtractionRT;
  for (int i=0
         ; i<Questions.length; i++){
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
 * Méthode calcul de points, qui permet d'ajouter des points par rapport à la comparaison.
 * S'il reste des valeurs supérieur à 0 on retire des points.
*/
  public static double Points (String Questions[], int ReponseBonne[][], int ReponseTest[][]){
  double points=0; 
  points=Comparaison(Questions, ReponseBonne, ReponseTest);
  for (int x=0; x<Questions.length; x++){
    for (int y=1; y<ReponseBonne[x].length; y++){
      if (ReponseTest[x][y]>0){
        points=points-1;
      }
    }
  } return points;
  }
/** 
 * Méthode qui propose un menu à l'utilisateur si les questions ne sont pas rempli par l'utilisateur alors 
 * l'utilisateur retourne au menu et est invité à remplir le questionnaire.
*/
  public static int Menu(String Questions[]){
    int ChoixMenu;
    do{
      System.out.println("------------------MENU---------------");
      System.out.println("1- Remplir questionnaire");
      System.out.println("2- Passer le test.");
      System.out.println("3- Quitter");
      ChoixMenu=lireEntier("Votre choix: ",1,3);
      if(ChoixMenu==2&&Questions[1]==null){
        System.out.println("Vous n'avez pas rempli le questionnaire.");
        System.out.println("Vous devez remplir le questionnaire avant de pouvoir passer le test.");
      }
    }while(ChoixMenu==2&&Questions[1]==null);
    return ChoixMenu;
  }
/**
 * Méthode d'affichage du succès ou de l'échec par rapport au points et au nombre total de réponses.
*/
  public static void SuccesEchec( String Questions[], int ReponseBonne[][], int ReponseTest[][]){
  int TotalReponse=0;
  double Points;
  for(int i=0; i<ReponseBonne.length; i++){
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
/**
 * Méthode principale avec les différents tableaux et les appelles de méthodes. 
*/
  public static void main (String [] args) {
    String []Questions = new String [150];
    String [][]ReponseChoix = new String [150][150];
    int [][] ReponseBonne = new int [150][150], ReponseTest = new int [150][150];
    int ChoixMenu;
    do{
      ChoixMenu=Menu(Questions);
      if(ChoixMenu==1){
        QuestionReponse(Questions, ReponseChoix, ReponseBonne);
      }
      else if(ChoixMenu==2){
        Début();
        Test(Questions,ReponseChoix, ReponseTest);
        SuccesEchec(Questions, ReponseBonne, ReponseTest);
      }
      else{
        System.exit(3);
      }
    }while(ChoixMenu!=3);
  }
}
