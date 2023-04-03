package ai.openfabric.api.controller;

import ai.openfabric.api.model.request.DockerImageRequest;
import ai.openfabric.api.model.response.DockerImageResponse;
import ai.openfabric.api.service.DockerImageService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.InspectImageResponse;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.PullResponseItem;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("${node.api.path}/images")
@AllArgsConstructor
public class ImageController {
    private final DockerClient dockerClient;
    private final DockerImageService dockerImageService;

    @PostMapping("/pull")
    public ResponseEntity<?> pullImage(@RequestBody String imageName) {
        try {

            // todo: apply the checking mechanising
            dockerClient.pullImageCmd(imageName).start().awaitCompletion();
            InspectImageResponse imageResponse = dockerClient.inspectImageCmd(imageName).exec();
            DockerImageRequest imageRequest = DockerImageRequest.builder()
                    .imageId(imageResponse.getId())
                    .repoDigest(imageResponse.getRepoDigests().stream().findFirst().get())
                    .repoTag(imageResponse.getRepoTags().stream().findFirst().get())
                    .size(imageResponse.getSize())
                    .createdAt(imageResponse.getCreated())
                    .build();
            DockerImageResponse response = dockerImageService.saveImage(imageRequest);
            return ResponseEntity.ok(response);
            //return ResponseEntity.ok("Image already pulled.");
        } catch (InterruptedException | DockerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error pulling image.");
        }
    }

    @GetMapping(path = "/images")
    public ResponseEntity<?> getImages() {
        List<Image> images = dockerClient.listImagesCmd().exec();
        return ResponseEntity.ok(images);
    }
}
