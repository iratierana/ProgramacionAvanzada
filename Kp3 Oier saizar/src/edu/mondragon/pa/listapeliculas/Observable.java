package edu.mondragon.pa.listapeliculas;

public interface Observable {
	void addObserver(Observer o);
	void deleteObserver(Observer o);
	void notifyObservers();
}
