/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layout;

/**
 *
 * @author rafie
 */
public class UserDivisi {
    private String id;
    private String employeeId;
    private String name;
    private String division;
    private String idLevel;
    private String bossManId;
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(String idLevel) {
        this.idLevel = idLevel;
    }

    public String getBossManId() {
        return bossManId;
    }

    public void setBossManId(String bossManId) {
        this.bossManId = bossManId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserDivisi{" + "id=" + id + ", employeeId=" + employeeId + ", name=" + name + ", division=" + division + ", idLevel=" + idLevel + ", bossManId=" + bossManId + ", createdAt=" + createdAt + '}';
    }
    
    
}
