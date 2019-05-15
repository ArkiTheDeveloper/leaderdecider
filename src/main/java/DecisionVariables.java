import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Methods and variables used
 * for deciding the actions of
 * producer and consumer.
 */
public class DecisionVariables {

    private static volatile AtomicBoolean isLeader = new AtomicBoolean(true);

    static final String CURRENT_INSTANCE = "<CURRENT_INSTANCE>"; //CONFIGURATION

    static final Long POLLING_INTERVAL = 5L; //CONFIGURATION

    static final TimeUnit POLLING_UNITS = TimeUnit.SECONDS; //CONFIGURATION

    /**
     * Method that returns if the current
     * instance is leader or not.
     *
     * Synchronizing because producer and consumer
     * are two different threads
     *
     * @return true if leader
     */
    synchronized static boolean isLeader() {
        return isLeader.get();
    }


    /**
     * Method to make the current
     * instance is a leader
     *
     * Synchronizing because producer and consumer
     * are two different threads
     */
    synchronized static void makeInstanceLeader() {
        isLeader.set(true);
    }


    /**
     * Method to make the current
     * instance is a slave
     *
     * Synchronizing because producer and consumer
     * are two different threads
     */
    synchronized static void makeInstanceSlave() {
        isLeader.set(false);
    }

}
