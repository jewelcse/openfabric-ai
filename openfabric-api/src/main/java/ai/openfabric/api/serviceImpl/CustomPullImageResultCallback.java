package ai.openfabric.api.serviceImpl;

import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.PullResponseItem;

import java.util.concurrent.CountDownLatch;

public class CustomPullImageResultCallback extends PullImageResultCallback {
    private CountDownLatch countDownLatch;

    public CustomPullImageResultCallback(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void onComplete() {
        super.onComplete();
        countDownLatch.countDown();
    }

    public void onError(Throwable throwable) {
        super.onError(throwable);
        countDownLatch.countDown();
    }

    public void onNext(PullResponseItem item) {
        super.onNext(item);
        System.out.println(item.getStatus());
    }
}
