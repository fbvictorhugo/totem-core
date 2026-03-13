package dev.fbvictorhugo.totemcore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform