package FRC2020GIT.InfiniteRechargeSim;

//import java.util.Random; 

public class Robot {

    private String myColor;
    private String myName;
    
    private Boolean isReady;
    private double timeStart;

    private double time4low;
    private double time4outer;
    private double prob4low;
    private double prob4outer;
    private double prob4inner;
    private double time4collectField;
    private double time4collectLoader;
    private double prob4collect;
    private int loadedBalls=0;
    private double prob4hang;
    private double time4hang;
    private double time2move2shoot;
    private double prob4rotate;
    private double time4rotate;
    private double prob4color;
    private double time4color;







    public Robot(String name, String color) {
        myColor = color;
        myName = name;
        isReady = true;
        timeStart = 0;
        time4low=2;
        time4outer=3;
        prob4low=.8;
        prob4outer=1.0;
        prob4inner=.1;
        loadedBalls = 3;
        prob4hang = 1.0;
        time4hang = 10;
        time4collectField = 5.0;
        time4collectLoader = 10.0;
        prob4collect = .25;
        time2move2shoot = 10;
        prob4color=1;
        time4color=10;
        prob4rotate=1;
        time4rotate=10;



    }

    public String toString() {
        return myColor + myName;
    }

    public String getColor() {
        return myColor;
    }
    public void robotReset() {
        timeStart=0;
    }

    public Boolean isReady() {
        return isReady;
    }
    public double robotReady() {
        return timeStart;
    }
    public int action(double gameTime) {
        // return the points generated by the action
        int points = 0;
        int currentCharge = FRC2020Match.getCharge(myColor);
        if (FRC2020Match.getStage(myColor)==1 && ! FRC2020Match.getRotate(myColor) && FRC2020Match.getCharge(myColor)==20) {
            // should go rotate if charge is getting close
            timeStart += time4rotate;
            if (timeStart>150)
                return 0;
            if (Math.random() <= prob4rotate){
                FRC2020Match.setRotate(myColor); 
                FRC2020Match.nextStage(myColor);
                System.out.println(" stage rotated "+ myColor + " " + timeStart);

            }
        }
        else if (FRC2020Match.getStage(myColor)==2 && ! FRC2020Match.getColor(myColor) && FRC2020Match.getCharge(myColor)==20) {
            // should go set color wheel
            timeStart += time4color;
            if (timeStart>150)
                return 0;
            if (Math.random() <= prob4color){
                FRC2020Match.setColor(myColor); 
                FRC2020Match.nextStage(myColor);
                System.out.println(" stage colored "+ myColor + " " + timeStart);
            }
        }   
        else if (loadedBalls >= 3) { // go shoot the balls
            timeStart = timeStart + time2move2shoot;
            if (timeStart>150)
                return 0;
            // shoot the balls
            while (loadedBalls > 0) {
                timeStart += time4outer;
                if (timeStart>150)
                    return points;
                loadedBalls-=1;
                if (Math.random() <= prob4outer) {
                    // have scored increase appropriate charge
                    FRC2020Match.addCharge(myColor);
                    FRC2020Match.addBall2Loader(myColor);
                    

                    if (Math.random() <= prob4inner) {
                        if (timeStart <=15) {
                            points = points + 6;
                            //System.out.print(" + 6 AutoInner ");
                            }
                    else {
                            points = points + 3;
                            //System.out.print(" + 3  TeleInner ");
                        }
                            
                    }
                    if (timeStart <=15) {
                        points = points + 4;
                        //System.out.print(" + 4 AutoOuter ");
                        }
                else {
                        points = points + 2;
                        //System.out.print(" + 2  TeleOuter ");
                    }
                
                } 
                else
                    FRC2020Match.addBall2Field(); // robot missed shot
            }
        }

            
        else {
            // pick up a ball
            // start with field balls
            // determine if loader or field is more efficient
            if (FRC2020Match.getBallsOnField() >= 5  &&  FRC2020Match.getBallsInLoader(myColor)<10){
                // get field balls
                if (FRC2020Match.getBallsOnField() > 0) {
                    timeStart += time4collectField;
                    if (timeStart>150)
                        return 0;
                    if (Math.random() < prob4collect){
                        loadedBalls += 1;
                        FRC2020Match.takeBallOffField();
                    }
                }
            
            }
            else {
                // get load balls
                if (FRC2020Match.getBallsInLoader(myColor)>0){
                    // load up to 5 or how many are left
                    timeStart += time4collectLoader;
                    if (timeStart>150)
                        return 0;
                    while (FRC2020Match.getBallsInLoader(myColor)>0 && loadedBalls<5){
                        // load a ball
                        FRC2020Match.takeBallOutLoader(myColor);
                        loadedBalls++;
    
    
                    }
            }


            
            


                
            }
                
                
           

        }
        //System.out.println();
        return points;
    }

}