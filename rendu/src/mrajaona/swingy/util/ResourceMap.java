package mrajaona.swingy.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class ResourceMap {

    final Map<String, String> map;

    @SuppressWarnings("unused")
	private ResourceMap() {
    	map = null;
    }

    public ResourceMap(String[][] data) {
        map = new HashMap<String, String>(data.length);
        for (String[] mapping : data) {
            map.put(mapping[0], mapping[1]);
        }
    }

    public String getKeyByValue(String value) {
    	if (!map.containsValue(value))
    		return null;

	    for ( Entry<String, String> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

}
