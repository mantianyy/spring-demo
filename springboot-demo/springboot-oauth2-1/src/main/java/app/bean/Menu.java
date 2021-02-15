package app.bean;

import java.util.List;

public class Menu implements java.io.Serializable{
    private Integer id;
    private String pattern;
    private String desc;
    private List<Role> roles;

    public Menu() {
    }

    public Menu(Integer id, String pattern, String desc) {
        this.id = id;
        this.pattern = pattern;
        this.desc = desc;
    }

    public Menu(Integer id, String pattern, String desc, List<Role> roles) {
        this.id = id;
        this.pattern = pattern;
        this.desc = desc;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
