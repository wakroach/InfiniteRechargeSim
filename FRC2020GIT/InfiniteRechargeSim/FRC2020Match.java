package FRC2020GIT.InfiniteRechargeSim;


public class FRC2020Match  {

    // match variables
    int score4low = 1;
    int score4outer = 2;
    int score4inner = 3;
    int scoreMultiplierAuto = 2;
    int score4init = 5;
    int redScore = 0;
    int blueScore = 0;
    static int ballsOnField = 10;
    static int ballsInRedLoad = 5;
    static int ballsInBlueLoad = 5;
    static int redCharge=0;
    static int blueCharge=0;
    Robot[] myRobots;
    // constructor 
    public FRC2020Match(Robot[] robots) {
        myRobots = robots;
        runMatch();
    }

    //  method for robots to call to get field info
    public static int getCharge(String color){
        if (color.equals("red"))
            return redCharge;
        return blueCharge;
     }
     public static void addCharge(String color){
        if (color.equals("red")){
            redCharge++;
            return;
        }
        blueCharge++;
     }

     public static void addBall2Field(){
        ballsOnField++;
     }

     public static int getBallsOnField(){
        return ballsOnField;
     }

     public static void takeBallOffField(){
        if (ballsOnField>0)
            ballsOnField--;

     }

     public static void takeBallOutLoader(String color){
        if (color.equals("blue")) {
            ballsInBlueLoad--;
        }
        else
            ballsInRedLoad--;
     }

     public static void addBall2Loader(String color){
        if (color.equals("blue")) {
            if (ballsInBlueLoad>14)
                ballsOnField++;
            ballsInBlueLoad++;
        }
        else {
            if (ballsInRedLoad>14) 
                ballsOnField++;
            ballsInRedLoad++;
            
        }
     }

     public static int getBallsInLoader(String color){
        if (color.equals("blue"))        
            return ballsInBlueLoad;
        return ballsInRedLoad;

     }
     

     public String getScores() {
        return "Red = " + redScore+ " to Blue = " + blueScore;
     }
     
    // simulations
    public void runMatch() { 
        for (double i = 0; i < 150; i+=.1) { // run match at .1 second resolution
        //robot selection loop
            for (Robot j: myRobots) {
                if (j.robotReady()<=i){
                //choose an action
                    if (j.getColor().equals("red")) {
                        redScore = redScore + j.action(i);
                        //System.out.println("Action" + j.toString()+ "  : "+ redScore);
                    }
                    else {
                        blueScore = blueScore + j.action(i);
                       // System.out.println("Action" + j.toString()+ "  : "+ blueScore);
                    }
                
                
                }

                //update points for action

                // repeat
            }

        }
        // reset robots
        for (Robot j: myRobots) {
            j.robotReset();
        }
    }

     
    

    



}