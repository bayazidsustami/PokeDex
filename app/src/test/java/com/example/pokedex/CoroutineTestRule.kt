package com.example.pokedex

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class CoroutineTestRule: TestRule {

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    private val testCoroutineScope = TestScope(testCoroutineDispatcher)

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                Dispatchers.setMain(testCoroutineDispatcher)
                base.evaluate()

                Dispatchers.resetMain()
            }
        }
    }

    fun runBlockingTest(bloc: suspend CoroutineTestRule.() -> Unit) {
        testCoroutineScope.runTest { bloc() }
    }
}