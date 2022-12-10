package ray.mintcat.rayitemscript.impl.actions

data class ConsumeData(
    val amount: Int,
    val take: Boolean = false,
    val message: String? = null
)