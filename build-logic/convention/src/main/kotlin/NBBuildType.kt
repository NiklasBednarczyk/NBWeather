enum class NBBuildType {

    DEBUG,
    RELEASE;

    val applicationIdSuffix: String?
        get() = when (this) {
            DEBUG -> ".debug"
            RELEASE -> null
        }

}