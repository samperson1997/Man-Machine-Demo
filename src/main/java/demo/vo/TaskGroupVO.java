package demo.vo;

public class TaskGroupVO {

    private String[][] taskGroup;

    public TaskGroupVO() {
    }

    public TaskGroupVO(String[][] taskGroup) {
        this.taskGroup = taskGroup;
    }

    public String[][] getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String[][] taskGroup) {
        this.taskGroup = taskGroup;
    }
}
