package com.bibro.domain.task;

import com.bibro.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Task task;
    @ManyToOne
    private User user;
    private LocalDateTime start;
    private LocalDateTime end;

    public Challenge(Task task, User user) {
        this.task = task;
        this.user = user;
        this.start = LocalDateTime.now();
    }
}
