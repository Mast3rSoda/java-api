package pl.wi.xd.api.db.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "privilege_name", nullable = false)
    private String privilegeName;

    @ManyToMany(mappedBy = "privileges", cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<Role> roles = new LinkedHashSet<>();

    protected Privilege() {}

    public Privilege(@NotBlank String privilegeName, @NotBlank Role role) {
        this.privilegeName = privilegeName;
        this.roles.add(role);
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

}