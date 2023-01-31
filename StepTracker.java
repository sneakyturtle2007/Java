public class StepTracker
{
   // copy the instance variable declarations here
    private double Average_steps;
    private int Daily_steps;
    private int min_steps_for_active_day;
    private int Active_days;
    private int total_steps;
    private int total_days;
   // copy the constructor with a parameter here
    public StepTracker(int initmin_steps_for_active_day){
        min_steps_for_active_day = initmin_steps_for_active_day;
        Average_steps = 0;
        Daily_steps = 0;
        Active_days = 0;
        total_steps = 0;
    }

   // copy the accessor method activeDays() here
    public int activeDays(){
        
        return Active_days;
    }

   // copy the mutator method addDailySteps here.
   // @param number of steps taken that day
    public void addDailySteps(int initsteps){
        Daily_steps = 0;
        Daily_steps = Daily_steps + initsteps;
        total_steps = total_steps + Daily_steps;
        total_days ++;
        if(Daily_steps >= min_steps_for_active_day){
            Active_days ++;
        }
    }

   //Write the accessor method averageSteps() here
   // @return average steps calculated by dividing the total number of steps taken by the number of days tracked (which should be instance variables). Make sure you use type casting to double!
    public double averageSteps(){
        if(total_days >= 1){
            Average_steps = (double)total_steps / total_days; 
            return Average_steps;
        }else{
            Average_steps = 0;
            return Average_steps;
        }
    }


   public static void main(String[] args)
   {
      StepTracker tr = new StepTracker(10000);
      System.out.println(tr.activeDays()); // returns 0. No data have been recorded yet.
      System.out.println(tr.averageSteps()); // returns 0.0. When no step data have been recorded, the averageSteps method returns 0.0.
      tr.addDailySteps(9000); // This is too few steps for the day to be considered active.
      tr.addDailySteps(5000); // This is too few steps for the day to be considered active.
      System.out.println(tr.activeDays()); // returns 0.  No day had at least 10,000 steps.
      System.out.println(tr.averageSteps()); // returns 7000.0 The average number of steps per day is (14000 / 2).
      tr.addDailySteps(13000); // This represents an active day.
      System.out.println(tr.activeDays());  // returns 1. Of the three days for which step data were entered, one day had at least 10,000 steps.
      System.out.println(tr.averageSteps()); // returns 9000.0. The average number of steps per day is (27000 / 3).
      tr.addDailySteps(23000); // This represents an active day.
      tr.addDailySteps(1111); // This is too few steps for the day to be considered active.
      System.out.println(tr.activeDays()); // returns 2. Of the five days for which step data were entered, two days had at least 10,000 steps.
      System.out.println(tr.averageSteps()); // returns 10222.2. The average number of steps per day is (51111 / 5).
   }
}
