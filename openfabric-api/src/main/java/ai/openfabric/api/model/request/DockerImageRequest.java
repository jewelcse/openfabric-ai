package ai.openfabric.api.model.request;


import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DockerImageRequest {
    public String name;
    public String imageId;
    public String repoDigest;
    public String repoTag;
    public String createdAt;
    public long size;
}
