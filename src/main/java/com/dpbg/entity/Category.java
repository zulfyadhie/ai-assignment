package com.dpbg.entity;

import javax.persistence.*;

/**
 * Created by zulfy on 9/12/16.
 */
@Entity
@Table(name = "category")
public class Category{

    private Long id;
    private String name;
    private Category parent;
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "category_seq_gen")
    @SequenceGenerator(name = "category_seq_gen", sequenceName = "category_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}