import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {

    Map<Integer, LeakyBucket> bucket;
    public UserBucketCreator(int id) {
        bucket = new HashMap<>();
        bucket.put(id, new LeakyBucket(10));
    }

    void accessApplication(int id) {
        if (bucket.get(id).grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " Allowed");
            return;
        }
        System.out.println(Thread.currentThread().getName() + " 429 Rate Limited");
    }
}
