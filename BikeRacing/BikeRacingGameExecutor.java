import java.util.*;
import java.util.concurrent.*;


class RaceResult {
    String name;
    long startTime;
    long endTime;

    RaceResult(String name, long startTime, long endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    long getTimeTaken() {
        return endTime - startTime;
    }
}


class BikerTask implements Callable<RaceResult> {

    private String name;
    private int totalDistance;

    BikerTask(String name, int totalDistance) {
        this.name = name;
        this.totalDistance = totalDistance;
    }

    @Override
    public RaceResult call() throws Exception {

        int covered = 0;
        long start = System.currentTimeMillis();

        while (covered < totalDistance) {

            
            int sleepTime = 200 + (int) (Math.random() * 500);
            Thread.sleep(sleepTime);

            covered += 100;
            if (covered > totalDistance) {
                covered = totalDistance;
            }

            System.out.println(name + " covered " + covered + " meters");
        }

        long end = System.currentTimeMillis();
        return new RaceResult(name, start, end);
    }
}


public class BikeRacingGameExecutor {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("BIKE RACING GAME STARTED \n");

      
        String[] bikers = new String[10];
        for (int i = 0; i < 10; i++) {
            System.out.print("Enter Biker " + (i + 1) + " Name: ");
            bikers[i] = sc.nextLine();
        }

       
        System.out.print("\nEnter Race Distance (in KM): ");
        int distance = sc.nextInt() * 1000;

        System.out.println("\nRace Distance: " + distance + " meters");

        
        System.out.println("\nRace Starting In...");
        for (int i = 10; i >= 1; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("🚦 LET'S PLAYYYYY 🚦\n");

       
        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<Future<RaceResult>> futureResults = new ArrayList<>();


        for (String name : bikers) {
            futureResults.add(executor.submit(new BikerTask(name, distance)));
        }

        executor.shutdown();

        
        List<RaceResult> results = new ArrayList<>();
        for (Future<RaceResult> f : futureResults) {
            results.add(f.get()); 
        }

        
        results.sort(Comparator.comparingLong(RaceResult::getTimeTaken));

       
        System.out.println("\n================ FINAL RACE DASHBOARD ================");
        System.out.println("Rank\tName\tStart Time\tEnd Time\tTime Taken(ms)");

        int rank = 1;
        for (RaceResult r : results) {
            System.out.println(
                    rank++ + "\t" +
                    r.name + "\t" +
                    r.startTime + "\t" +
                    r.endTime + "\t" +
                    r.getTimeTaken()
            );
        }

        sc.close();
    }
}
