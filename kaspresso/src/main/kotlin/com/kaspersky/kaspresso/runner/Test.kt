package com.kaspersky.kaspresso.runner


data class Test(
    val pkg: String,
    val clazz: String,
    val method: String,
    val metaProperties: Collection<MetaProperty>
) {
    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true

            javaClass != other?.javaClass -> false

            else -> {
                other as Test
                when {
                    pkg != other.pkg -> false
                    clazz != other.clazz -> false
                    method != other.method -> false
                    else -> true
                }
            }
        }
    }

    override fun hashCode(): Int {
        var result = pkg.hashCode()
        result = 31 * result + clazz.hashCode()
        result = 31 * result + method.hashCode()
        return result
    }
}

fun Test.toTestName(): String = "$pkg.$clazz#$method"
fun Test.toSimpleSafeTestName(): String = "$clazz.$method"
fun Test.toSafeTestName(): String = "$pkg.$clazz.$method"
