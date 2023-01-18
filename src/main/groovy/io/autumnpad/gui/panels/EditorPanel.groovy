package io.autumnpad.gui.panels

import io.autumnpad.gui.panels.split.EditorSplitPanel

import javax.swing.*
import java.awt.*

class EditorPanel extends JPanel {
    EditorPanel() {
        setDoubleBuffered(true)
        setLayout(new BorderLayout())
        add(new EditorSplitPanel(), BorderLayout.CENTER)
    }
}
