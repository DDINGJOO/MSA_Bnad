package dding.info.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name ="band_detail")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BandDetail {

    @Id
    String bandId;
    String leaderId;

    @Lob
    String contents;



    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

}
