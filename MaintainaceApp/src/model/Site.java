package model;

public class Site {

    private int length;
    private int width;
    private String siteType; 

    public Site(int length, int width, String siteType) {
        this.length = length;
        this.width = width;
        this.siteType = siteType;
    }

    public int calculateArea() {
        return length * width;
    }

    public double calculateMaintenance() {
        if ("OPEN".equalsIgnoreCase(siteType)) {
            return calculateArea() * 6;
        } else {
            return calculateArea() * 9;
        }
    }
}
