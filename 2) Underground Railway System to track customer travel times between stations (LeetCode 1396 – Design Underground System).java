import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {
    
    private final Map<Integer, Object[]> checkInMap = new HashMap<>();
    private final Map<String, double[]> routeMap = new HashMap<>();

    public UndergroundSystem() {}
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Object[]{stationName, t});
    }
    
    public void checkOut(int id, String stationName, int t) {
        Object[] checkIn = checkInMap.remove(id);
        String startStation = (String) checkIn[0];
        int checkInTime = (int) checkIn[1];
        
        String routeKey = startStation + "->" + stationName;
        int travelTime = t - checkInTime;
        
        double[] data = routeMap.computeIfAbsent(routeKey, k -> new double[2]);
        data[0] += travelTime;
        data[1] += 1;
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        double[] data = routeMap.get(routeKey);
        return data[0] / data[1];
    }
}
