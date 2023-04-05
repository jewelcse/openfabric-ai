package ai.openfabric.api.serviceImpl;

import ai.openfabric.api.model.Worker;
import ai.openfabric.api.model.WorkerStatus;
import ai.openfabric.api.model.request.WorkerCreateRequest;
import ai.openfabric.api.repository.WorkerRepository;
import ai.openfabric.api.service.WorkerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;



    @Override
    public void create(WorkerCreateRequest workerRequest) {
        Worker newWorker = Worker.builder()
                .containerId(workerRequest.getId())
                .imageName(workerRequest.getImageName())
                .state(WorkerStatus.CLOSED.name())
                .status(WorkerStatus.DOWN.name())
                .build();
        workerRepository.save(newWorker);

    }

    @Override
    public Page<Worker> getWorkers(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return workerRepository.findAll(pageRequest);
    }

    @Override
    public void start(String id) {



    }

    @Override
    public void stop(String id) {

    }
}
