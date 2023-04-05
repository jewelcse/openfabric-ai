package ai.openfabric.api.model.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerCreateRequest {

    private String id;
    private String imageName;
}
