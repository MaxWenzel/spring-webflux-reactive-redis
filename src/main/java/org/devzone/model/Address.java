package org.devzone.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

@Data
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Address  {

    private String postalCode;
    private String locality;
    @EqualsAndHashCode.Exclude
    private String state;

}
