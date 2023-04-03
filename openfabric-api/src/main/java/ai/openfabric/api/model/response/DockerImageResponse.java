package ai.openfabric.api.model.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DockerImageResponse {
    public String id;
    public String name;
    public String imageId;
    public String repoDigest;
    public String repoTag;
    public String createdAt;
    public long size;
}
