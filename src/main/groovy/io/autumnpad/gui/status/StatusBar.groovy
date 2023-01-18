package io.autumnpad.gui.status

import io.autumnpad.gui.global.Fonts
import io.autumnpad.reactive.ReactiveUISource

import javax.swing.*
import java.awt.*

class StatusBar extends JLabel {

    StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 16));
        setForeground(Color.BLACK)
        setFont(Fonts.GLOBAL_FONT)
        addSaveListener()
        setText("ready")
    }

    def addSaveListener() {
        ReactiveUISource.getStatusBarSubject().subscribe((line) -> {
            setText(line)
        })
    }
}
