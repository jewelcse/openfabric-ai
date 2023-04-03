package ai.openfabric.api.model;

import com.yahoo.elide.annotation.Include;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Include(rootLevel = true, type = "image")
@Entity()
public class DockerImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    @Getter
    @Setter
    public String id;
    public String name;
    public String imageId;
    public String repoDigest;
    public String repoTag;
    public long size;
    public String createdAt;
}
