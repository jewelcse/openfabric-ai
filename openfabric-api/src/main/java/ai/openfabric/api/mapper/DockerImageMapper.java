package ai.openfabric.api.mapper;

import ai.openfabric.api.model.DockerImage;
import ai.openfabric.api.model.request.DockerImageRequest;
import ai.openfabric.api.model.response.DockerImageResponse;
import org.springframework.stereotype.Component;

@Component
public class DockerImageMapper {


    public DockerImageResponse toDockerImageResponse(DockerImage image){
        if(image == null) return null;
        return DockerImageResponse.builder()
                .imageId(image.getId())
                .createdAt(image.getCreatedAt())
                .name(image.getName())
                .repoDigest(image.getRepoDigest())
                .repoTag(image.getRepoTag())
                .id(image.getId())
                .build();
    }

    public DockerImage toDockerImage(DockerImageRequest imageRequest){
        return DockerImage.builder()
                .imageId(imageRequest.getImageId())
                .createdAt(imageRequest.getCreatedAt())
                .name(imageRequest.getName())
                .repoDigest(imageRequest.getRepoDigest())
                .repoTag(imageRequest.getRepoTag())
                .size(imageRequest.getSize())
                .build();
    }


}
