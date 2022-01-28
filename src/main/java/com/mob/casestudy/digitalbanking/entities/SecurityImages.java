package com.mob.casestudy.digitalbanking.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SecurityImages {
    @Id
    @Column(length = 36)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String securityImageName;
    private String securityImageUrl;
    @OneToMany(mappedBy = "securityImages")
    private List<CustomerSecurityImages>  customerSecurityImages;
}
