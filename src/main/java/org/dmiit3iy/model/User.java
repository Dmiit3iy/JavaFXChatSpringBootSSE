package org.dmiit3iy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    private long id;

    @NonNull
    private String login;
    @NonNull
    private String password;

    @ToString.Exclude
    @JsonIgnore
    private List<Message> messageList;
}
