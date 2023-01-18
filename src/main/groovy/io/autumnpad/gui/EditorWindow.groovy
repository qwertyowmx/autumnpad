package io.autumnpad.gui

import io.autumnpad.gui.menu.EditorMenuBar
import io.autumnpad.gui.panels.EditorPanel
import io.autumnpad.gui.status.StatusBar
import io.autumnpad.reactive.ReactiveUISource

import javax.swing.*
import java.awt.*

class EditorWindow extends JFrame {

    final Dimension DEFAULT_SIZE = new Dimension(1000, 1000)

    EditorWindow() {
        initWindow()
        initPanel()
    }

    private def initWindow() {
        setTitle("AutumnPad")
        resolveAndSetImageIcon()
        setDefaultCloseOperation(DISPOSE_ON_CLOSE)
        setSize(DEFAULT_SIZE)
        setJMenuBar(new EditorMenuBar())
    }

    private def initPanel() {
        setContentPane(new EditorPanel())
        getContentPane().add(new StatusBar(), BorderLayout.SOUTH)
    }

    def showWindow() {
        ReactiveUISource.getStatusBarSubject().onNext("Ready")
        setVisible(true)
    }

    def resolveAndSetImageIcon() {
        def resource = getClass().getClassLoader().getResource("leaf.png" as String)
        def image = Toolkit.getDefaultToolkit().getImage(resource)
        this.setIconImage(image)
    }
}
