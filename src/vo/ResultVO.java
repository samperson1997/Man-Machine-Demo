package vo;

public class ResultVO {

    private int id;
    private int group_id;
    private String subject;
    private String tool;
    private int time_budget;
    private double BC;
    private double MC;
    private double total;
    private String time_start;
    private String time_end;

    public ResultVO() {
    }

    public ResultVO(int id, int group_id, String subject, String tool, int time_budget, double BC, double MC, double total, String time_start, String time_end) {
        this.id = id;
        this.group_id = group_id;
        this.subject = subject;
        this.tool = tool;
        this.time_budget = time_budget;
        this.BC = BC;
        this.MC = MC;
        this.total = total;
        this.time_start = time_start;
        this.time_end = time_end;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public int getTime_budget() {
        return time_budget;
    }

    public void setTime_budget(int time_budget) {
        this.time_budget = time_budget;
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
