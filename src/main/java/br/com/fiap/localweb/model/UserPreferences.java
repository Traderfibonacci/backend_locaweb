package br.com.fiap.localweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_user_preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    @JsonBackReference
    private UserAccount user;

    @Enumerated(EnumType.STRING)
    private Theme theme;
    private String color;
    @Enumerated(EnumType.STRING)
    private Category category;

}
