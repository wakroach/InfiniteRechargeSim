package FRC2020GIT.InfiniteRechargeSim;

public class FRC2020 {
    // class variables to represent the status of field 
    

    public static void main(String[] args) {
        Robot[] robots= new Robot[]{new Robot("R1","red"),new Robot("R2","red"),new Robot("R3","red"),new Robot("B1","blue"),new Robot("B2","blue"),new Robot("B3","blue")};
       // FRC2020Match match = new FRC2020Match(robots);

       // System.out.println(match.getScores());
        
        FRC2020Match match1 = new FRC2020Match(robots);

        System.out.println(match1.getScores());
        System.out.println("Red charge =" + FRC2020Match.getCharge("red"));
        System.out.println("Blue charge = " +FRC2020Match.getCharge("blue"));

    }
}
