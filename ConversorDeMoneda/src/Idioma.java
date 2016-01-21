import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Idioma extends Observable {
	
	Properties idioma;
	
	public Idioma(){
		idioma = new Properties();
	}

	public void clear() {
		idioma.clear();
		setChanged();
		notifyObservers();
	}

	public Object clone() {
		return idioma.clone();
	}

	public Object compute(Object arg0, BiFunction<? super Object, ? super Object, ? extends Object> arg1) {
		return idioma.compute(arg0, arg1);
	}

	public Object computeIfAbsent(Object arg0, Function<? super Object, ? extends Object> arg1) {
		return idioma.computeIfAbsent(arg0, arg1);
	}

	public Object computeIfPresent(Object arg0, BiFunction<? super Object, ? super Object, ? extends Object> arg1) {
		return idioma.computeIfPresent(arg0, arg1);
	}

	public boolean contains(Object arg0) {
		return idioma.contains(arg0);
	}

	public boolean containsKey(Object arg0) {
		return idioma.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return idioma.containsValue(arg0);
	}

	public Enumeration<Object> elements() {
		return idioma.elements();
	}

	public Set<Entry<Object, Object>> entrySet() {
		return idioma.entrySet();
	}

	public boolean equals(Object arg0) {
		return idioma.equals(arg0);
	}

	public void forEach(BiConsumer<? super Object, ? super Object> arg0) {
		idioma.forEach(arg0);
	}

	public Object get(Object arg0) {
		return idioma.get(arg0);
	}

	public Object getOrDefault(Object arg0, Object arg1) {
		return idioma.getOrDefault(arg0, arg1);
	}

	public String getProperty(String key, String defaultValue) {
		return idioma.getProperty(key, defaultValue);
	}

	public String getProperty(String key) {
		return idioma.getProperty(key);
	}

	public int hashCode() {
		return idioma.hashCode();
	}

	public boolean isEmpty() {
		return idioma.isEmpty();
	}

	public Set<Object> keySet() {
		return idioma.keySet();
	}

	public Enumeration<Object> keys() {
		return idioma.keys();
	}

	public void list(PrintStream out) {
		idioma.list(out);
	}

	public void list(PrintWriter out) {
		idioma.list(out);
	}

	public void load(InputStream inStream) throws IOException {
		idioma.load(inStream);
		setChanged();
		notifyObservers();
	}

	public void load(Reader reader) throws IOException {
		idioma.load(reader);
		setChanged();
		notifyObservers();
	}

	public void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
		idioma.loadFromXML(in);
	}

	public Object merge(Object arg0, Object arg1, BiFunction<? super Object, ? super Object, ? extends Object> arg2) {
		return idioma.merge(arg0, arg1, arg2);
	}

	public Enumeration<?> propertyNames() {
		return idioma.propertyNames();
	}

	public Object put(Object arg0, Object arg1) {
		return idioma.put(arg0, arg1);
	}

	public void putAll(Map<? extends Object, ? extends Object> arg0) {
		idioma.putAll(arg0);
		setChanged();
		notifyObservers();
	}

	public Object putIfAbsent(Object arg0, Object arg1) {
		return idioma.putIfAbsent(arg0, arg1);
	}

	public boolean remove(Object arg0, Object arg1) {
		Boolean o = idioma.remove(arg0, arg1);
		setChanged();
		notifyObservers();
		return o;
	}

	public Object remove(Object arg0) {
		Object o = idioma.remove(arg0);
		setChanged();
		notifyObservers();
		return o;
	}

	public boolean replace(Object arg0, Object arg1, Object arg2) {
		return idioma.replace(arg0, arg1, arg2);
	}

	public Object replace(Object arg0, Object arg1) {
		return idioma.replace(arg0, arg1);
	}

	public void replaceAll(BiFunction<? super Object, ? super Object, ? extends Object> arg0) {
		idioma.replaceAll(arg0);
	}

	public void save(OutputStream out, String comments) {
		idioma.save(out, comments);
	}

	public Object setProperty(String key, String value) {
		Object o = idioma.setProperty(key, value);
		setChanged();
		notifyObservers();
		return o;
	}

	public int size() {
		return idioma.size();
	}

	public void store(OutputStream out, String comments) throws IOException {
		idioma.store(out, comments);
	}

	public void store(Writer writer, String comments) throws IOException {
		idioma.store(writer, comments);
	}

	public void storeToXML(OutputStream os, String comment, String encoding) throws IOException {
		idioma.storeToXML(os, comment, encoding);
	}

	public void storeToXML(OutputStream os, String comment) throws IOException {
		idioma.storeToXML(os, comment);
	}

	public Set<String> stringPropertyNames() {
		return idioma.stringPropertyNames();
	}

	public String toString() {
		return idioma.toString();
	}

	public Collection<Object> values() {
		return idioma.values();
	}
	
	
	
	
	
	
	

}
