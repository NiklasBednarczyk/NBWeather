package de.niklasbednarczyk.nbweather.test.common.utils

import kotlin.test.*

fun <T> assertListIsNotEmpty(
    actual: List<T>
) {
    assertTrue(actual.isNotEmpty())
}

fun <T> assertListHasSize(
    actual: List<T>,
    size: Int
) {
    assertEquals(actual.size, size)
}

fun <T> assertListsContainsItemInOrder(
    actual: List<T>,
    vararg items: T
) {
    assertEquals(actual, items.toList())
}

fun <T> assertListsContainSameItems(
    list1: List<T>,
    list2: List<T>
) {
    assertTrue(list1.containsAll(list2))
}

fun <T> assertListDoesNotContain(
    actual: List<T>,
    vararg items: T
) {
    assertFalse(actual.containsAll(items.toList()))
}

fun assertNullOrEmpty(
    actual: Any?
) {
    if(actual is List<*>?) {
        assertTrue(actual == null || actual.isEmpty())
    } else {
        assertNull(actual)
    }
}