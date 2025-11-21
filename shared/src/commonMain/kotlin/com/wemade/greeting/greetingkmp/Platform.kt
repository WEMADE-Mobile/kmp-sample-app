package com.wemade.greeting.greetingkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform