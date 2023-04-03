package ai.openfabric.api.serviceImpl;

import ai.openfabric.api.mapper.DockerImageMapper;
import ai.openfabric.api.model.DockerImage;
import ai.openfabric.api.model.request.DockerImageRequest;
import ai.openfabric.api.model.response.DockerImageResponse;
import ai.openfabric.api.repository.DockerImageRepository;
import ai.openfabric.api.service.DockerImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DockerImageServiceImpl implements DockerImageService {
    private final DockerImageRepository dockerImageRepository;
    private final DockerImageMapper dockerImageMapper;

    @Override
    public DockerImageResponse saveImage(DockerImageRequest imageRequest) {
       DockerImage image = dockerImageRepository.save(dockerImageMapper.toDockerImage(imageRequest));
        return dockerImageMapper.toDockerImageResponse(image);
    }
}
