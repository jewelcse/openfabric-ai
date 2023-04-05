package ai.openfabric.api.service;

import ai.openfabric.api.model.request.WorkerCreateRequest;

public interface WorkerService {

    void create(WorkerCreateRequest workerRequest);

    void start(String id);
    void stop(String id);

}
