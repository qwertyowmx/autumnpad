package io.autumnpad.gui.panels.split

import io.autumnpad.gui.elements.textpane.EditorTextPane
import io.autumnpad.gui.elements.tree.DirectoryTreeView
import io.autumnpad.gui.global.Fonts
import io.autumnpad.reactive.ReactiveUISource

import javax.swing.*
import java.awt.*

class EditorSplitPanel extends JSplitPane {

    EditorSplitPanel() {
        def view = new DirectoryTreeView()
        def directoryTreeView = new JScrollPane(view)
        directoryTreeView.setPreferredSize(new Dimension(300, -1))
        setLeftComponent(directoryTreeView)

        def editorPane = new JScrollPane(new EditorTextPane())
        setRightComponent(editorPane)

        def lineNumbers = new JTextArea()
        lineNumbers.setFont(Fonts.GLOBAL_FONT)
        lineNumbers.setBackground(Color.DARK_GRAY)
        lineNumbers.setEditable(false)
        editorPane.setRowHeaderView(lineNumbers)

        ReactiveUISource.getLinesNumberStateSubject().subscribe { it ->
            lineNumbers.setText("")
            StringBuilder builder = new StringBuilder()
            for (int i = 0; i < it; i++) {
                builder.append(i).append('\n')
            }
            lineNumbers.setText(builder.toString())
        }

    }
}
