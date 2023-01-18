package io.autumnpad.gui.elements.doc.highlight

import io.autumnpad.gui.elements.doc.highlight.resource.KeywordResource

import javax.swing.text.AttributeSet
import javax.swing.text.DefaultStyledDocument
import javax.swing.text.StyleConstants
import javax.swing.text.StyleContext
import java.awt.*
import java.util.regex.Matcher
import java.util.regex.Pattern

abstract class HighlightStrategy {

    final StyleContext context = StyleContext.getDefaultStyleContext();

    final AttributeSet COMMENT_HL_ATTRIBUTE = context.addAttribute(context.getEmptySet(),
            StyleConstants.Foreground,
            Color.GREEN)

    final AttributeSet METHOD_CALL_HL_ATTRIBUTE = context.addAttribute(context.getEmptySet(),
            StyleConstants.Foreground,
            Color.ORANGE)

    final AttributeSet DEFAULT_TEXT_ATTR = context.addAttribute(context.getEmptySet(),
            StyleConstants.Foreground,
            Color.WHITE)

    final AttributeSet KEYWORD_HL_ATTRIBUTE = context.addAttribute(context.getEmptySet(),
            StyleConstants.Foreground,
            Color.YELLOW)

    protected DefaultStyledDocument document
    protected KeywordResource keywords

    HighlightStrategy(DefaultStyledDocument document, KeywordResource keywords) {
        this.keywords = keywords
        this.document = document
    }

    def highlight() {
        resetColours()
        highlightKeywords()
        highlightObjectsInMethodCalls()
        highlightOneLineComments()
        highlightMultilineComments()
    }

    def resetColours() {
        def text = getText()
        document.setCharacterAttributes(0, text.length(), DEFAULT_TEXT_ATTR, true)
    }

    protected def getText() {
        return document.getText(0, document.getLength())
    }

    protected abstract def highlightMultilineComments()

    protected abstract def highlightKeywords()

    protected abstract def highlightOneLineComments()

    protected abstract def highlightObjectsInMethodCalls()

    protected def highlightInternal(String regex, String targetText, AttributeSet colour) {
        Pattern pattern = Pattern.compile(regex)
        Matcher matcher = pattern.matcher(targetText)
        while (matcher.find()) {
            document.setCharacterAttributes(matcher.start(), (matcher.end() - matcher.start()), colour, true);
        }
    }
}
