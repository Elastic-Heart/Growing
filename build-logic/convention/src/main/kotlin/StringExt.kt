internal val String.libraryName
    get() = this
        .replace("'", "")
        .replace(":", "")