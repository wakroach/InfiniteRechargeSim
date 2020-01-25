public class FRC2020 {

     

    public static void main(String[] args) {
        Robot[] Robots= new Robot[]{new Robot("R1","red"),new Robot("R2","red"),new Robot("R3","red"),new Robot("B1","blue"),new Robot("B2","blue"),new Robot("B3","blue")};
        int score4low = 1;
        int score4outer = 2;
        int score4inner = 3;
        int scoreMultiplierAuto = 2;
        int score4init = 5;
        int redScore = 0;
        int blueScore = 0;
        int ballsOnField = 10;
        int ballsInRedLoad = 7;
        int ballsInBlueLoad = 7;
        


        System.out.println("Simulation");
        for (int i = 0; i < 150; ++i) {
            if (i == 15)
                System.out.println("Auto over");
            if (i == 130)
                System.out.println("End game start");
            //robot selection loop
            for (Robot j: Robots) {
                if (j.robotReady()<=i){
                    //choose an action
                    if (j.getColor().equals("red")) {
                        redScore = redScore + j.action(i);
                        System.out.println("Score!" + j.toString()+ "  : "+ redScore);
                    }
                    else {
                        blueScore = blueScore + j.action(i);
                        System.out.println("Score!" + j.toString()+ "  : "+ blueScore);
                    }
                    
                    
                }

                    //update points for action

                    // repeat
                }

            }
        

        System.out.println("Game Over");
        System.out.println("Red = " + redScore+ " to Blue = " + blueScore);
        //System.out.println(Robots[0].toString());



    }
}



