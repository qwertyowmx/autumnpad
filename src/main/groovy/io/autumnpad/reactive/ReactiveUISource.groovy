package io.autumnpad.reactive

import rx.subjects.PublishSubject

class ReactiveUISource {
    static final PublishSubject<File> dirOpeningSubject = PublishSubject.create()
    static final PublishSubject<File> fileOpeningSubject = PublishSubject.create()
    static final PublishSubject<Integer> linesNumberStateSubject = PublishSubject.create()
    static final PublishSubject<Object> fileSaveSubject = PublishSubject.create()
    static final PublishSubject<String> statusBarSubject = PublishSubject.create()
}
