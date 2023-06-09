package ai.openfabric.api.controller;

import ai.openfabric.api.model.request.WorkerCreateRequest;
import ai.openfabric.api.service.WorkerService;
import ai.openfabric.api.util.ApplicationUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${node.api.path}/worker")
@AllArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    private final DockerClient dockerClient;

    @GetMapping(path = "/containers")
    public ResponseEntity<?> getContainers(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(workerService.getWorkers(page,pageSize));
    }

    @PostMapping(path = "/container/create")
    public ResponseEntity<?> createContainer(@RequestBody String name) {
        ExposedPort tcp01 = ExposedPort.tcp(8080);
        ExposedPort tcp02 = ExposedPort.tcp(8080);
        CreateContainerResponse containerResponse = dockerClient
                .createContainerCmd(name)
                .withHostName(ApplicationUtil.generateRandomName())
                .withExposedPorts(tcp01,tcp02)
                .exec();
        dockerClient.startContainerCmd(containerResponse.getId()).exec();
        workerService.create(WorkerCreateRequest.builder()
                .id(containerResponse.getId())
                .imageName(name)
                .build());
        return ResponseEntity.ok("started!");
    }

    @PostMapping(path = "/container/start")
    public ResponseEntity<?> startContainer(@RequestBody String id) {
        dockerClient.startContainerCmd(id).exec();
        workerService.start(id);
        return ResponseEntity.ok("started!");
    }

    @PostMapping(path = "/container/stop")
    public ResponseEntity<?> stopContainer(@RequestBody String id) {
        dockerClient.stopContainerCmd(id).exec();
        workerService.stop(id);
        return ResponseEntity.ok("stopped!");
    }

    @PostMapping(path = "/container/profile")
    public ResponseEntity<?> containerProfile(@RequestBody String id) {
        return ResponseEntity.ok(dockerClient.inspectContainerCmd(id).exec());
    }

}
