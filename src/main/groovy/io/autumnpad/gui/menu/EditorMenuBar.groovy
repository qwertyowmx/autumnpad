package io.autumnpad.gui.menu

import io.autumnpad.gui.menu.dialog.DirectoryOpenDialog
import io.autumnpad.reactive.ReactiveUISource

import javax.swing.*
import java.awt.*
import java.awt.event.KeyEvent

class EditorMenuBar extends JMenuBar {

    EditorMenuBar() {
        add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(createFileMenu())
    }

    private static def createFileMenu() {
        def menu = new JMenu("File")

        def openFile = new JMenuItem("Open File")
        menu.add(openFile)

        def openFolder = new JMenuItem("Open Folder");
        menu.add(openFolder)

        openFolder.addActionListener((event) -> {
            def openDirDialog = new DirectoryOpenDialog()
            openDirDialog.showOpenDialog(event.getSource() as Component)
            def selectedFile = openDirDialog.getSelectedFile();
            ReactiveUISource.getDirOpeningSubject().onNext(selectedFile)
        })

        menu.addSeparator()

        def saveFile = new JMenuItem("Save")

        saveFile.addActionListener((e) -> {
            println "Saving file"
            ReactiveUISource.getFileSaveSubject().onNext(new Object())
        })

        KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        saveFile.setAccelerator(keyStrokeToOpen)
        menu.add(saveFile)

        def saveAsFile = new JMenuItem("Save As")
        menu.add(saveAsFile)

        menu.addSeparator()

        def autoSave = new JCheckBoxMenuItem("AutoSave")
        menu.add(autoSave)

        return menu
    }
}
