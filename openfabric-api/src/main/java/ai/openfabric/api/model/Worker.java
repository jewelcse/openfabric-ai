package ai.openfabric.api.model;


import com.yahoo.elide.annotation.Include;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Include(rootLevel = true, type = "worker")
@Entity()
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Worker extends Datable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    @Getter
    @Setter
    public String id;
    public String name;
    public String containerId;
    public String imageName;
    public String status;
    public String state;

}
