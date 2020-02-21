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
    static int redEndPoints = 0;
    static int blueEndPoints = 0;
    int redRP=0;
    int blueRP=0;
    static int redAutooints = 0;
    static int blueAutoPoints = 0;

    static int ballsOnField = 10;
    static int ballsInRedLoad = 5;
    static int ballsInBlueLoad = 5;
    static int redCharge=0;
    static int blueCharge=0;
    static double matchTime=0;
    static Boolean redRotate = false;
    static Boolean blueRotate = false;

    static Boolean redColor = false;
    static Boolean blueColor = false;

    // stage 0 init -> stage 1 9 charge and tele stage 2 20 charge and 
    //rotate stage 3 20 charge and control

    static int redStage=0; 
    static int blueStage=0;
    static Boolean blueSwitchBalanced= false;
    static Boolean redSwitchBalanced= false;
    static int redPark=0; 
    static int bluePark=0;
    static int redHang=0; 
    static int blueHang=0;

    Robot[] myRobots;
    // constructor 
    public FRC2020Match(Robot[] robots) {
        myRobots = robots;
        runMatch();
    }

    public static void hangRobot(String color, Boolean balanced){
        if (color.equals("red")) {
            redHang++;
            if(balanced)
                redSwitchBalanced=true;
            else
                redSwitchBalanced=false;
            System.out.println("Red hang and balanced = " + redSwitchBalanced.toString());
        }
        if (color.equals("blue")) {
            blueHang++;
            if(balanced)
                blueSwitchBalanced=true;
            else
                blueSwitchBalanced=false;

            System.out.println("Blue hang and balanced = " + blueSwitchBalanced.toString());
        }

    }

    public static void parkRobot(String color) {
        if (color.equals("red")) {
            redPark++;
        }
        if (color.equals("blue")) {
            bluePark++;
            System.out.println(color + " parked ");
        }
    }

    //  method for robots to call to get field info
    public static int getCharge(String color){
        if (color.equals("red"))
            return redCharge;
        return blueCharge;
     }

     public static int getStage(String color){
        if (color.equals("red"))
            return redStage;
        return blueStage;
     }

     public static Boolean getRotate(String color){
        if (color.equals("red"))
            return redRotate;
        return blueRotate;
     }

     public static void setRotate(String color){
        if (color.equals("red"))
            redRotate=true;
        blueRotate=true;
     }

     public static void nextStage(String color){
        if (color.equals("red")) {
            if (redStage==0 && redCharge ==9 && matchTime>15) {
                redStage = 1;
                redCharge = 0;
                System.out.println("Red stage 1 " + matchTime);
            }
            else if (redStage ==1 && redCharge == 20 && redRotate) {
                redStage = 2;
                redCharge = 0;
                System.out.println("Red stage 2: rotated " + matchTime);
            }
            else if (redStage ==2 && redCharge == 20 && redColor) {
                redStage = 3;
                redCharge = 0;
                System.out.println("Red stage 3: colored " + matchTime);
            }
        }
        else {
            if (blueStage==0 && blueCharge ==9 && matchTime>15) {
                blueStage = 1;
                blueCharge = 0;
                System.out.println("Blue stage 1 " + matchTime);
             }
             else if (blueStage ==1 && blueCharge == 20 && blueRotate) {
                 blueStage = 2;
                 blueCharge = 0;
                 System.out.println("Blue stage 2: rotated " + matchTime);
             }
             else if (blueStage ==2 && blueCharge == 20 && blueColor) {
                 blueStage = 3;
                 blueCharge = 0;
                 System.out.println("Blue stage 3: colored " + matchTime);
             }
        }  
        
     }

     public static void setColor(String color){
        if (color.equals("red"))
            redColor=true;
        blueColor=true;
     }

     public static Boolean getColor(String color){
        if (color.equals("red"))
            return redColor;
        return blueColor;
     }


     public static void addCharge(String color){
        if (color.equals("red")){
            // check to see if we add to capacity
            if ((redStage==0 && redCharge<9) || (redStage==1 && redCharge<20) || (redStage==2 && redCharge<20))
                redCharge++;
            // check to see if we need to go to next stage
            
            
            //return;
        }
        if (color.equals("blue")) { // blue charge
            if ((blueStage==0 && blueCharge<9) || (blueStage==1 && blueCharge<20) || (blueStage==2 && blueCharge<20))
            blueCharge++;
        // check to see if we need to go to next stage
            
        
        
        }
        nextStage(color);
        
     } // method end

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
            else
                ballsInBlueLoad++;
        }
        else {
            if (ballsInRedLoad>14) 
                ballsOnField++;
            else
                ballsInRedLoad++;
            
        }
     }

     public static int getBallsInLoader(String color){
        if (color.equals("blue"))        
            return ballsInBlueLoad;
        return ballsInRedLoad;

     }
     

     public String getScores() {
         // determine if ranking points
         // stage 3 complete (gets 1 RP)
         // shield generator operational (>= 65 end game points)
         // end game points: 25 per robot, 15 level, 5 park
        String scoreText = "";
        if (redSwitchBalanced) {
            redEndPoints+=15;
        }
        redScore+=redEndPoints;
        if (blueSwitchBalanced) {
            blueEndPoints+=15;
        }
        blueScore+=blueEndPoints;


        scoreText = "Red = " + redScore+ " to Blue = " + blueScore + '\n' + "redEndGame " + redEndPoints + "--Blue End Game " + blueEndPoints;
        scoreText += "AutoPoints R1 =" + myRobots[0].getAutoPoints();
        return scoreText;
     }
     
    // simulations
    public void runMatch() { 
        for (double i = 0; i < 150; i+=.1) { // run match at .1 second resolution
        //robot selection loop
        matchTime = i;
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