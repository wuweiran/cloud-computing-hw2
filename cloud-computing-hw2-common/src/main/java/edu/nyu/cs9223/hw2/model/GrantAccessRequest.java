package edu.nyu.cs9223.hw2.model;

import lombok.*;

/**
 * @author wwrus
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GrantAccessRequest {
    String faceId;
    String name;
    String phoneNumber;
}
