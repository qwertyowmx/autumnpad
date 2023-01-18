package io.autumnpad.gui.elements.doc.highlight.resource


class Cpp17KeywordResource implements KeywordResource {

    private Set<String> keywords;

    Cpp17KeywordResource() {

        def resource = getClass()
                .getClassLoader()
                .getResource('keywords/cpp17.lst')

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
