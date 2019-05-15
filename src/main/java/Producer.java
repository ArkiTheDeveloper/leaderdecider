import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Producer class that produces (notifies)
 * a message every 10 seconds (configurable)
 * if it is a leader.
 */
public class Producer {

    /*
     Instantiation of a new Single Threaded Scheduled Executor to produce messages
     */
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /*
     Whenever tomcat boots completely this method is triggered
     */
    public void notifyLeadershipStatus() {
        //Establish a connection
        if (DecisionVariables.isLeader()) {
            System.out.println("Currently Leader");
        } else {
            DecisionVariables.makeInstanceSlave();
            executorService.shutdown();
            System.out.println("NOT_LEADER");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        executorService.scheduleAtFixedRate(producer::notifyLeadershipStatus,
                0,
                DecisionVariables.POLLING_INTERVAL,
                DecisionVariables.POLLING_UNITS);
        Thread.sleep(10000);
        DecisionVariables.makeInstanceSlave();
    }
}
