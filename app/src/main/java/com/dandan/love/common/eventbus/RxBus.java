package com.dandan.love.common.eventbus;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/19 下午3:29
 * Description:
 */
public class RxBus {
    private SerializedSubject<Event, Event> rxBus;

    public RxBus() {
        rxBus = new SerializedSubject(PublishSubject.create());
    }

    private static class SingletonHolder {

        private static RxBus instance = new RxBus();
    }

    public static RxBus getInstance() {
        return SingletonHolder.instance;
    }

    public void postEvent(Event event) {
        if (this.hasObservers()) rxBus.onNext(event);
    }

    private boolean hasObservers() {
        return rxBus.hasObservers();
    }

    public Observable<Event> toObservable(final String action) {
        return rxBus
                .asObservable()
                .filter(new Func1<Event, Boolean>() {
                    @Override
                    public Boolean call(Event event) {
                        return event.action().equals(action);
                    }
                })
                .onBackpressureBuffer();
    }

    public Observable<Event> toObservableOnMain(final String action) {
        return toObservable(action).observeOn(AndroidSchedulers.mainThread()) ;
    }
}
