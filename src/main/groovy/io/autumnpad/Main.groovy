package io.autumnpad

import io.autumnpad.gui.EditorWindow
import io.autumnpad.gui.plaf.EditorPlaf

import javax.swing.*

class Main {
    static void main(String[] args) {
        EditorPlaf.makeUIStyleDarkNimbus()
        SwingUtilities.invokeLater(() -> {
            def editor = new EditorWindow()
            editor.showWindow();
        })
    }
}
