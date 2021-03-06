package ru.phoenigm.stuffer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "service_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private boolean active;
    private Date lastPasswordResetDate;

    private String avatarUrl;

    private LocalDateTime registrationDate;
    private LocalDateTime lastVisit;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Trip> publishedTrips;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @JsonBackReference
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviewsOnMe;

    @JsonBackReference
    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    @JsonIgnore
    @ManyToMany(mappedBy = "stuffers", cascade = CascadeType.ALL)
    private Set<Trip> stufferTrips;

    @JsonIgnore
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private Set<TripRequest> receivedRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "stuffer", cascade = CascadeType.ALL)
    private Set<TripRequest> sentRequests;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
