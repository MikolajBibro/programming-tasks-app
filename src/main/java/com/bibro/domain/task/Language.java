package com.bibro.domain.task;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Language {
    C("extension", "dockerfile", "directoryPath"),
    JAVA("extension", "dockerfile", "directoryPath"),
    PYTHON("extension", "dockerfile", "directoryPath");

    private final String extension;
    private final String dockerfile;
    private final String directoryPath;

    Language(String extension, String dockerfile, String directoryPath) {
        this.extension = extension;
        this.dockerfile = dockerfile;
        this.directoryPath = directoryPath;
    }

    public static List<String> getLanguages() {
        return Arrays.stream(Language.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
