package io.autumnpad.gui.elements.doc.highlight.resource

class PythonKeywordResource implements KeywordResource {

    private Set<String> keywords;

    PythonKeywordResource() {

        def resource = getClass()
                .getClassLoader()
                .getResource('keywords/python.lst')

        def strings = resource.getText().split(System.getProperty("line.separator"))

        def keywordsSet = new HashSet<String>()
        keywordsSet.addAll(strings)

        keywords = keywordsSet
    }

    @Override
    Set<String> getKeywords() {
        return keywords
    }
}
