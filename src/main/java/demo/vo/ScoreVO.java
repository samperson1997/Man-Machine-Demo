package demo.vo;

public class ScoreVO {

    private double BC;
    private double MC;
    private double total;

    public ScoreVO() {
    }

    public ScoreVO(double BC, double MC, double total) {
        this.BC = BC;
        this.MC = MC;
        this.total = total;
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
