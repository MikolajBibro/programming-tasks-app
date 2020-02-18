package com.bibro.domain;

import lombok.Getter;

@Getter
public enum Language {
    C(".c", "C:\\Users\\Mikolaj\\Desktop\\new\\Dockerfile", "C:\\Users\\Mikolaj\\Desktop\\new\\"),
    JAVA(".java", "C:\\Users\\Mikolaj\\Desktop\\new\\Dockerfile", "C:\\Users\\Mikolaj\\Desktop\\new\\"),
    PYTHON(".py", "C:\\Users\\Mikolaj\\Desktop\\new\\Dockerfile", "C:\\Users\\Mikolaj\\Desktop\\new\\");

    private final String extension;
    private final String dockerfile;
    private final String directoryPath;

    Language(String extension, String dockerfile, String directoryPath) {
        this.extension = extension;
        this.dockerfile = dockerfile;
        this.directoryPath = directoryPath;
    }
}
