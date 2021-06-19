package entities;

public class Account {
    private String kind;
    private String id;
    private String name;
    private String status;
    private String days_left;
    private String plan;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDays_left() {
        return days_left;
    }

    public void setDays_left(String days_left) {
        this.days_left = days_left;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
