package nl.kyllian.event

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import nl.kyllian.DiscordBot
import nl.kyllian.commands.CommandManager
import java.util.concurrent.Executors

object JDAEventListener : EventListener {

    private val executor = Executors.newCachedThreadPool()

    override fun onEvent(event: GenericEvent) {
        executor.submit {
            when (event) {
                is MessageReceivedEvent -> handleMessageReceive(event)
            }
        }
    }

    fun handleMessageReceive(event: MessageReceivedEvent) {
        if (event.author.isBot && !event.message.contentRaw.startsWith(DiscordBot.config.prefix)) return

        val split = event.message.contentRaw.split(" ")
        val commandName = split[0].removePrefix(DiscordBot.config.prefix)
        val args = split.drop(1).toTypedArray()
        val command = CommandManager.getCommand(commandName) ?: return
        command.execute(event.member!!, event.channel.asTextChannel(), args)
    }

}