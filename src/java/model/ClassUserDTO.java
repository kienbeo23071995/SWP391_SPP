package model;

public class ClassUserDTO {
    private int id;
    private User user;
    private Class cls;
    private Boolean isLeader;

    public ClassUserDTO() {
    }

    public ClassUserDTO(int id, User user, Class cls, Boolean isLeader) {
        this.id = id;
        this.user = user;
        this.cls = cls;
        this.isLeader = isLeader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public Boolean getLeader() {
        return isLeader;
    }

    public void setLeader(Boolean leader) {
        isLeader = leader;
    }
}
