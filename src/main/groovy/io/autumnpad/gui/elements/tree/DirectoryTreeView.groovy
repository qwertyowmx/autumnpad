package io.autumnpad.gui.elements.tree

import io.autumnpad.gui.elements.tree.model.FileModel
import io.autumnpad.reactive.ReactiveUISource

import javax.swing.*
import javax.swing.tree.DefaultMutableTreeNode
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

class DirectoryTreeView extends JTree {

    DirectoryTreeView() {
        setModel(new DirectoryViewModel(null))
        subscribeDirectoryOpened()
        addSelectionListener()
    }

    def addSelectionListener() {
        getSelectionModel().addTreeSelectionListener((e) -> {
            def node = (DefaultMutableTreeNode) this.getLastSelectedPathComponent()
            FileModel model = node.getUserObject() as FileModel

            if (model.source.isFile()) {
                ReactiveUISource.getFileOpeningSubject().onNext(model.source)
                ReactiveUISource.getStatusBarSubject().onNext("Opened file: '${model.source.getAbsolutePath()}'")
            }
        });
    }

    def subscribeDirectoryOpened() {
        ReactiveUISource.getDirOpeningSubject().subscribe((file) -> {
            def tree = collectFoldersRecursive(new FileModel(file))
            ReactiveUISource.getStatusBarSubject().onNext("Opened directory '${file.getAbsolutePath()}'")
            setModel(new DirectoryViewModel(tree))
        })
    }

    DefaultMutableTreeNode collectFoldersRecursive(FileModel model) {

        def node = new DefaultMutableTreeNode(model)
        def children = Files
                .walk(Paths.get(model.source.getAbsolutePath()), 1)
                .map((path) -> {
//                    ReactiveUISource.getStatusBarSubject().onNext("Scanning: " + path.toString())
                    path.toFile()
                })
                .map(mappedFile ->
                        new FileModel(mappedFile))
                .collect(Collectors.toList())

        for (def child in children) {
            if (child.name != model.name) {
                node.add(collectFoldersRecursive(child))
            }
        }

        return node
    }
}
