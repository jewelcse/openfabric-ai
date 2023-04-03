package ai.openfabric.api.repository;

import ai.openfabric.api.model.DockerImage;
import ai.openfabric.api.model.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DockerImageRepository extends CrudRepository<DockerImage, String> {
}
