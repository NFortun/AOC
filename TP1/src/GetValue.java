import java.util.concurrent.Callable;

public class GetValue implements Callable<Integer> {

    @Override
    public Integer call() {
        return 10;
    }

}
