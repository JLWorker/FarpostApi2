package org.farpost.farpostapi2.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.enitities.ViewDir;
import org.farpost.farpostapi2.repositories.CityRepository;
import org.farpost.farpostapi2.repositories.ViewDirsRepository;
import org.farpost.farpostapi2.services.utils.ParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreeNode;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Slf4j
public class ViewDirParser {

    private final ObjectMapper objectMapper;
    private final ParserUtils parserUtils;
    private final ViewDirsRepository viewDirsRepository;

    @SneakyThrows
    public void startParser(String path){
        JsonNode treeNode = objectMapper.readTree(Files.readAllBytes(Path.of(path)));
        parseChildren(treeNode);
        log.info("ViewDir parsing simplified successfully");
    }

    @SneakyThrows
    private void parseChildren(JsonNode jsonNode){
        JsonNode children = jsonNode.path("children");
        if (parserUtils.checkPath(children)) {
            if (children.isArray() && !children.isEmpty()) {
                children.forEach(this::parseChildren);
            } else {
                if (!jsonNode.path("title").isMissingNode() && !jsonNode.path("id").isMissingNode()) {
                    String name = jsonNode.get("title").asText();
                    Integer farpostId = jsonNode.get("id").asInt();
                    viewDirsRepository.save(new ViewDir(name, farpostId));
                }
            }
        }
    }

}
