package io.autumnpad.gui.elements.doc.highlight

import io.autumnpad.gui.elements.doc.highlight.resource.KeywordResource

import javax.swing.text.AttributeSet
import javax.swing.text.DefaultStyledDocument
import javax.swing.text.StyleConstants
import java.awt.*

class CppHighlightStrategy extends HighlightStrategy {


    final AttributeSet PREPROCESSOR_HL_ATTRIBUTE = context.addAttribute(context.getEmptySet(),
            StyleConstants.Foreground,
            Color.ORANGE)

    CppHighlightStrategy(DefaultStyledDocument document,
                         KeywordResource keywordResource) {
        super(document, keywordResource)
    }

    @Override
    def highlight() {
        resetColours()
        highlightKeywords()
        highlightObjectsInMethodCalls()
        highlightPreprocessorDirectives()
        highlightOneLineComments()
        highlightMultilineComments()
    }

    // TODO: rewrite regexps
    @Override
    protected Object highlightMultilineComments() {
        return null
    }

    @Override
    def highlightKeywords() {
        String regexp = "(" + keywords.getKeywords().stream().reduce((a, b) -> a + "\\s+|\\s+" + b).get().toString() + ")"
        highlightInternal(regexp, getText(), KEYWORD_HL_ATTRIBUTE);
    }

    @Override
    protected Object highlightOneLineComments() {
        String regexp = "\\/\\/(.*)+\n"
        return highlightInternal(regexp, getText(), COMMENT_HL_ATTRIBUTE);
    }

    @Override
    protected Object highlightObjectsInMethodCalls() {
        String regexp = "(?:[A-Za-z0-9_^.]+)(?=[.])"
        highlightInternal(regexp, getText(), METHOD_CALL_HL_ATTRIBUTE)
    }

    def highlightPreprocessorDirectives() {
        def regex = '#(.*)+\n'
        highlightInternal(regex, getText(), PREPROCESSOR_HL_ATTRIBUTE)
    }
}

