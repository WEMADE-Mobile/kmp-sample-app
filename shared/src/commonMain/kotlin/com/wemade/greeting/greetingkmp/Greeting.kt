package com.wemade.greeting.greetingkmp

import kotlinx.coroutines.flow.Flow
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

class Greeting {
    private val platform = getPlatform()

    private val rocketComponent = RocketComponent()


    // 1
//    fun greet(): String {
//        val firstWord = if (Random.nextBoolean()) "Hi!" else "Hello!"
//        return "$firstWord Guess what this is! > ${platform.name.reversed()}!"
//    }

    //2
//    fun greet(): List<String> = buildList {
//        add(if (Random.nextBoolean()) "Hi!" else "Hello!")
//        add("Guess what this is! > ${platform.name.reversed()}!")
//        add(daysPhrase())
//    }

    // 3
    fun greet(): Flow<String> = flow {
        emit(if (Random.nextBoolean()) "Hi!" else "Hello!")
        delay(1.seconds)
        emit("Guess what this is! > ${platform.name.reversed()}")
        delay(1.seconds)
        emit(daysPhrase())
        emit(rocketComponent.launchPhrase())
    }
}

