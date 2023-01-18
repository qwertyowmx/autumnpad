package io.autumnpad.gui.elements.tree.model

class FileModel {

    String name;
    File source;

    FileModel(File source) {
        this.source = source
        this.name = source.name
    }

    FileModel(String name, File source) {
        this.name = name;
        this.source = source;
    }

    @Override
    String toString() {
        return name;
    }
}
