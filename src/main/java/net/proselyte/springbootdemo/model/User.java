package net.proselyte.springbootdemo.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
}
