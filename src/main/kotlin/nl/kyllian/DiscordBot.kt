package nl.kyllian

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import nl.kyllian.config.BotConfiguration
import nl.kyllian.event.JDAEventListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Path

object DiscordBot {

    val logger: Logger = LoggerFactory.getLogger(DiscordBot::class.java)
    val dataDir: File = Path.of(".").toFile()
    lateinit var client: JDA
    lateinit var config: BotConfiguration

    internal fun onEnable() {

    }

    internal fun onDisable() {

    }

}

fun main() {
    DiscordBot.config = BotConfiguration.createDefault()
    DiscordBot.client = JDABuilder.createDefault(
        DiscordBot.config.token, GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS)
    ).addEventListeners(JDAEventListener).build()
    DiscordBot.client.awaitReady()
    Runtime.getRuntime().addShutdownHook(Thread {
        DiscordBot.onDisable()
    })
    DiscordBot.onEnable()
}