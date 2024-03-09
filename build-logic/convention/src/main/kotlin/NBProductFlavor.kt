enum class NBProductFlavor {

    DEMO,
    PROD;

    override fun toString(): String {
        return name.lowercase()
    }

    val dimension: NBProductFlavorDimension
        get() = when (this) {
            DEMO, PROD -> NBProductFlavorDimension.MODE
        }

    val applicationIdSuffix: String?
        get() = when (this) {
            DEMO -> ".demo"
            PROD -> null
        }

}