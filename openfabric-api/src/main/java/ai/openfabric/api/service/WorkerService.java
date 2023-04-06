package ai.openfabric.api.service;

import ai.openfabric.api.model.Worker;
import ai.openfabric.api.model.request.WorkerCreateRequest;
import org.springframework.data.domain.Page;

public interface WorkerService {

    void create(WorkerCreateRequest workerRequest);

    Page<Worker> getWorkers(int page, int pageSize);

    void start(String id);
    void stop(String id);

}
