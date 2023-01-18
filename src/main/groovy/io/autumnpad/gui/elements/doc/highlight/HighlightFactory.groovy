package io.autumnpad.gui.elements.doc.highlight

import io.autumnpad.gui.elements.doc.highlight.resource.Cpp17KeywordResource
import io.autumnpad.gui.elements.doc.highlight.resource.PythonKeywordResource

class HighlightFactory {

    private HighlightFactory() {

    }

    static final HighlightStrategy createHighlighter(String fileExt, document) {
        if (fileExt == ".py") {
            def resource = new PythonKeywordResource()
            return new PythonHighlightStrategy(document, resource)
        }

        if (fileExt in ['.cpp', '.cc', '.cxx', '.c']) {
            def resource = new Cpp17KeywordResource()
            return new CppHighlightStrategy(document, resource)
        }

        return null
    }
}
