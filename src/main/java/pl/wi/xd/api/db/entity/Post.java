package pl.wi.xd.api.db.entity;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank
    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    private Category category;

    @Column(name = "date", nullable = false)
    @CreationTimestamp
    private Date date;

    protected Post() {}

    public Post(@NotBlank String title, @NotBlank String description, @NotBlank Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

}