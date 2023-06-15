package com.oopcourse.careernote.entity;


import jakarta.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="idgenerator")
    @SequenceGenerator(name = "idgenerator",sequenceName = "idgenerator",initialValue=1)
    private Long id;


    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity that)) {
            return false; // null or not an AbstractEntity class
        }
        if (getId() != null) {
            return getId().equals(that.getId());
        }
        return super.equals(that);
    }
}
