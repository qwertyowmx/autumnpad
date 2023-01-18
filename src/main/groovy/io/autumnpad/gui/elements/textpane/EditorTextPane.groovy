package io.autumnpad.gui.elements.textpane

import io.autumnpad.gui.elements.doc.HighlightStyleDocument
import io.autumnpad.gui.global.Fonts
import io.autumnpad.reactive.ReactiveUISource

import javax.swing.*
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants
import javax.swing.text.TabSet
import javax.swing.text.TabStop
import java.awt.*
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

class EditorTextPane extends JTextPane {

    private volatile File openedFile

    EditorTextPane() {
        setTabs(this, 8)
        setFonts()
        addFileSelectionListener()
        addFileSaveListener()
    }

    static def setTabs(final JTextPane textPane, int charactersPerTab) {
        FontMetrics metrics = textPane.getFontMetrics(textPane.getFont());
        int charWidth = metrics.charWidth(' ' as char);
        int tabWidth = charWidth * charactersPerTab;

        TabStop[] tabs = new TabStop[5];
        for (int j = 0; j < tabs.length; j++) {
            int tab = j + 1;
            tabs[j] = new TabStop(tab * tabWidth);
        }
        TabSet tabSet = new TabSet(tabs);
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setTabSet(attributes, tabSet);
        int length = textPane.getDocument().getLength();
        textPane.getStyledDocument().setParagraphAttributes(0, length, attributes, false);
    }

    def setFonts() {
        setFont(Fonts.GLOBAL_FONT)
    }

    def addFileSelectionListener() {
        ReactiveUISource.getFileOpeningSubject().subscribe((file) -> {

            synchronized (EditorTextPane.class) {
                openedFile = file
                setDocument(new HighlightStyleDocument(openedFile))
            }

            java.util.List<String> list = Files
                    .readAllLines(Paths.get(file.getPath()), StandardCharsets.UTF_8)

            this.setText(String.join("\n", list))
            ReactiveUISource.getLinesNumberStateSubject().onNext(list.size())
        })
    }

    def addFileSaveListener() {
        ReactiveUISource.getFileSaveSubject().subscribe((o) -> {
            Files.writeString(Paths.get(openedFile.getPath()), getText())
            ReactiveUISource.getStatusBarSubject().onNext("File saved: '${openedFile.getPath()}'")
        })
    }
}
