package io.autumnpad.gui.elements.doc

import io.autumnpad.gui.elements.doc.highlight.HighlightFactory
import io.autumnpad.reactive.ReactiveUISource

import javax.swing.text.AttributeSet
import javax.swing.text.BadLocationException
import javax.swing.text.DefaultStyledDocument

class HighlightStyleDocument extends DefaultStyledDocument {

    private File openedFile

    HighlightStyleDocument(File openedFile) {
        this.openedFile = openedFile
    }

    @Override
    void insertString(int offset, String str, AttributeSet attributeSet) throws BadLocationException {
        super.insertString(offset, str, attributeSet)
        def targetText = getText(0, getLength())

        if (targetText != null) {
            def lineBreaksCount = countLineBreaks(targetText)
            ReactiveUISource.getLinesNumberStateSubject().onNext(lineBreaksCount)
        }

        highlightText()
    }

    def highlightText() {
        def extension = getFileExtension(openedFile)
        def strategy = HighlightFactory.createHighlighter(extension, this)
        strategy?.highlight()
    }

    static def getFileExtension(File file) {
        String name = file?.getName()
        int lastIndexOf = name?.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""
        }
        return name?.substring(lastIndexOf);
    }

    static def countLineBreaks(text) {
        return text.findAll { it -> it == '\n' }.size()
    }
}
