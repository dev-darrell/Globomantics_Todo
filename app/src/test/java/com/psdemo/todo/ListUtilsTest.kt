package com.psdemo.todo

import com.psdemo.todo.list.determineCardColor
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ListUtilsTest(
    private val expected: Int,
    private val dueDate: Long?,
    private val done: Boolean,
    private val scenario: String
) {
    companion object{
        val now = System.currentTimeMillis()
        val day = 1000 * 60 * 60 * 24

        @JvmStatic
        @Parameterized.Parameters(name = "determineCardColor: {3}")
        fun todos() = listOf(
            arrayOf(R.color.todoDone, null, true, "Done, Not Due"),
            arrayOf(R.color.todoNotDue, null, false, "Not Done, Not Due"),
            arrayOf(R.color.todoOverDue, now - day, false, "Not Done, Due Yesterday"),
            arrayOf(R.color.todoDueStrong, now + day*3, false, "Not Done, Due in 3 Days"),
            arrayOf(R.color.todoDueMedium, now + day * 15, false, "Not Done, Due in 15 days"),
            arrayOf(R.color.todoDueLight, now + day * 20, false, "Not Done, Due in 25 days")
        )
    }

    @Test
    fun test_determineCardColor() {
        val actual = determineCardColor(dueDate, done)

        assertEquals(expected, actual)
    }
}