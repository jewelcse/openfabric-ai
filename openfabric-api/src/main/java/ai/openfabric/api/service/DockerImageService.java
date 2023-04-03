package ai.openfabric.api.service;

import ai.openfabric.api.model.request.DockerImageRequest;
import ai.openfabric.api.model.response.DockerImageResponse;

public interface DockerImageService {

    DockerImageResponse saveImage(DockerImageRequest imageRequest);
}
