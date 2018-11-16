package hu.elte.szakdolgozat.spms.model.ui;

import java.util.LinkedHashMap;
import java.util.Map;

public class StatisticChartModel {

    private LinkedHashMap<String, Integer> chartDataMap = new LinkedHashMap<>(4);

    public void addToExistingOrPutNew(String status, Integer quantity){
        if(chartDataMap.containsKey(status)){
            quantity = quantity + chartDataMap.get(status);
        }
        chartDataMap.put(status, quantity);
    }

    public String toArrayString() {
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String,Integer> e : chartDataMap.entrySet()) {
            sb.append("['").append(e.getKey()).append("',").append(e.getValue()).append("],");
        }
        return sb.toString();
    }
}
