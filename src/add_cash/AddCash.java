package add_cash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public class AddCash{
	static void start() {
		LRU a = new LRU(2);
		
		a.set("apple", "3");
		a.set("orange", "7");
		var c1 = a.get("orange");
		var c2 = a.get("orange");
		a.set("pine", "pine");
		var c3 = a.get("apple");

	}	
}

class LRU {
	private HashMap<String, Integer> data;
	private List<ppair> vec2;
	private Integer capacity;
	private Integer poz_to_add;

	public LRU(Integer cap) {
		poz_to_add = 0;
		capacity = cap;
		data = new HashMap<String, Integer>();
		vec2 = new Vector<>();
	}

	public void set(String k, String v) {
		if (data.containsKey(k)) {
			var idx = data.get(k);
			vec2.set(idx, new ppair(k, v));

		} else if (vec2.size() >= capacity) {
			var elem = vec2.get(poz_to_add);
			data.remove(elem.getKey());
			vec2.set(poz_to_add, new ppair(k, v));
			data.put(k, poz_to_add);
			poz_to_add = (poz_to_add + 1) % vec2.size();
		} else {
			data.put(k, vec2.size());
			vec2.add(new ppair(k, v));
		}
	}

	public String get(String k) {
		if (data.containsKey(k)) {
			refresh(k);
			return vec2.get(data.get(k)).getValue();
		}
		return null;
	}

	private void refresh(String k) {
		var poz = data.get(k);
		if (poz == 0)
			return;
		for (Integer iter = poz; iter > 0; iter--) {

			var vec_A = vec2.get(iter);
			var vec_B = vec2.get(iter - 1);
			vec2.set(iter, vec_B);
			vec2.set(iter - 1, vec_A);

			var dat_hA = vec2.get(iter).getKey();
			var dat_hB = vec2.get(iter - 1).getKey();
			data.put(dat_hA, iter);
			data.put(dat_hB, iter - 1);
		}

		poz_to_add = (poz_to_add + 1) % vec2.size();
	}
}

class ppair {
	private String value;
	private String key;

	public ppair(String k, String v) {
		value = v;
		key = k;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
