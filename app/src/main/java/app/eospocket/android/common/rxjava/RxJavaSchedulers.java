package app.eospocket.android.common.rxjava;

import io.reactivex.Scheduler;

public interface RxJavaSchedulers {

    Scheduler getNewThread();

    Scheduler getComputation();

    Scheduler getIo();

    Scheduler getMainThread();
}
