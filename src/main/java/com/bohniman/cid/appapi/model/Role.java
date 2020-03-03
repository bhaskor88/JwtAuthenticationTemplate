package com.bohniman.cid.appapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "master_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Long roleid;

    @NaturalId
    private String rolename;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String roledescription;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String isactive;

}