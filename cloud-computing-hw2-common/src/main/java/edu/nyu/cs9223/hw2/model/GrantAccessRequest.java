package edu.nyu.cs9223.hw2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
