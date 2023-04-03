package ai.openfabric.api.controller;

import ai.openfabric.api.config.MyDockerClientConfig;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.transport.DockerHttpClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${node.api.path}/worker")
@AllArgsConstructor
public class WorkerController {

    private final DockerClient dockerClient;


    @PostMapping(path = "/hello")
    public @ResponseBody String hello(@RequestBody String name) {
        //System.out.println(dockerClient.listImagesCmd().exec());
        return "Hello!" + name;
    }


    @GetMapping(path = "/images")
    public ResponseEntity<?> getImages() {
        List<Image> images = dockerClient.listImagesCmd().exec();
        return ResponseEntity.ok(images);
    }

    @GetMapping(path = "/containers")
    public ResponseEntity<?> getContainers(){
        List<Container> containers = dockerClient.listContainersCmd().exec();
        return ResponseEntity.ok(containers);
    }

    @PostMapping(path = "/container/start")
    public ResponseEntity<?> startContainer(@RequestBody String id){
        dockerClient.startContainerCmd(id).exec();
        return ResponseEntity.ok("started!");
    }

    @PostMapping(path = "/container/stop")
    public ResponseEntity<?> stopContainer(@RequestBody String id){
        dockerClient.stopContainerCmd(id).exec();
        return ResponseEntity.ok("stopped!");
    }








}
