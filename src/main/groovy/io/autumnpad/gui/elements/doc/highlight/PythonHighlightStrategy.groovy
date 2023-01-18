package io.autumnpad.gui.elements.doc.highlight

import io.autumnpad.gui.elements.doc.highlight.resource.KeywordResource

import javax.swing.text.DefaultStyledDocument

class PythonHighlightStrategy extends HighlightStrategy {

    PythonHighlightStrategy(DefaultStyledDocument document, KeywordResource resource) {
        super(document, resource)
    }

    @Override
    def highlightMultilineComments() {
        String regex = "\"\"\"[^\"]+\"\"\""
        highlightInternal(regex, getText(), COMMENT_HL_ATTRIBUTE)
    }

    @Override
    def highlightKeywords() {
        String regexp = "(" + keywords.getKeywords().stream().reduce((a, b) -> a + "\\s+|\\s+" + b).get().toString() + ")"
        highlightInternal(regexp, getText(), KEYWORD_HL_ATTRIBUTE)
    }

    @Override
    def highlightOneLineComments() {
        return null
    }

    @Override
    def highlightObjectsInMethodCalls() {
        String regexp = "(?:[A-Za-z0-9_^.]+)(?=[.])"
        highlightInternal(regexp, getText(), METHOD_CALL_HL_ATTRIBUTE)
    }
}
