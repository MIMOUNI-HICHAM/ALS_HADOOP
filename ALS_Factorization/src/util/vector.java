package util;

public class Vector {
    private double[] values;
    
    public Vector(int size) { values = new double[size]; }
    public void setValue(int i, double v) { values[i] = v; }
    public double getValue(int i) { return values[i]; }
    public int size() { return values.length; }
    
    public static Vector fromString(String str) {
        String[] parts = str.split(",");
        Vector v = new Vector(parts.length);
        for (int i = 0; i < parts.length; i++) {
            v.setValue(i, Double.parseDouble(parts[i]));
        }
        return v;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(values[i]);
        }
        return sb.toString();
    }
}
