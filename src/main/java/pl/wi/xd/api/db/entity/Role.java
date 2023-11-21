package pl.wi.xd.api.db.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    public enum Roles {
        ROLE_USER,
        ROLE_MOD,
        ROLE_ADMIN,
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "role_name", nullable = false)
    private Roles roleName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "role_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges = new LinkedHashSet<>();

    protected Role() {}

    public Role(@NotBlank String roleName, @NotBlank User user) {
        this.roleName = Roles.valueOf(roleName);
        this.users.add(user);
    }

    public String getRoleName() {
        return String.valueOf(roleName);
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void addPrivilege(Privilege privilege) {
        this.privileges.add(privilege);
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }


}