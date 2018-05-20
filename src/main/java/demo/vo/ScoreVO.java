package demo.vo;

public class ScoreVO {

    private String name;
    private double BC;
    private double MC;
    private double total;

    public ScoreVO() {
    }

    public ScoreVO(String name, double BC, double MC, double total) {
        this.name = name;
        this.BC = BC;
        this.MC = MC;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBC() {
        return BC;
    }

    public void setBC(double bC) {
        BC = bC;
    }

    public double getMC() {
        return MC;
    }

    public void setMC(double mC) {
        MC = mC;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}
