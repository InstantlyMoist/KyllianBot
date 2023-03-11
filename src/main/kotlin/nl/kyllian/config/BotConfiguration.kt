package nl.kyllian.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import nl.kyllian.DiscordBot
import kotlin.system.exitProcess

class BotConfiguration(
    token: String = "token",
    prefix: String = "!",
) {

    val token: String = token
    var prefix: String = prefix
        set(value) {
            field = value
            DiscordBot.logger.info("Prefix set to $value")
        }

    fun save() {
        val file = DiscordBot.dataDir.resolve("config.json")
        file.writeText(gson.toJson(this))
    }

    companion object {

        private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

        fun createDefault(): BotConfiguration {
            val file = DiscordBot.dataDir.resolve("config.json")
            if (!file.exists()) {
                val config = BotConfiguration()
                file.writeText(gson.toJson(config))
                DiscordBot.logger.error("No config file found, creating a default one, please fill in the token and prefix!")
                exitProcess(1)
            }
            return gson.fromJson(file.readText(), BotConfiguration::class.java)
        }

    }


}