package com.wemade.kmp.rocket

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform