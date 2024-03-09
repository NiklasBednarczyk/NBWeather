enum class NBProductFlavorDimension {

    MODE;

    override fun toString(): String {
        return name.lowercase()
    }

    companion object {

        fun getFlavorDimensionStrings(): List<String> {
            return values().map { flavorDimension ->
                flavorDimension.toString()
            }
        }

    }

}