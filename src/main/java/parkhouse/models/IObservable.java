package parkhouse.models;

import parkhouse.views.IObserver;

public interface IObservable {

    void registerObserver(IObserver o);
    void removeObserver(IObserver o);
    void notifyObservers();

}
