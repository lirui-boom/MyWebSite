package xyz.lirui123.mywebsite.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

public class AdminRole implements GrantedAuthority {

    private String roleName;

    public AdminRole() {
        this.roleName = "ROLE_ADMIN";
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
