package nl.kyllian.commands

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import nl.kyllian.DiscordBot
import nl.kyllian.wrapAndPost

object CommandManager {
    private val commands: Set<Command> = setOf(
        SetPrefixCommand,
        GameBoyCommand,
        LogsCommand,
        NesCommand,
        RomsCommand,
        RatingCommand
    )
    fun getCommand(name: String): Command? = commands.firstOrNull { it.name == name || it.aliases.contains(name) }
}

private object SetPrefixCommand : Command("setprefix", arrayOf()) {
    override fun execute(member: Member, channel: TextChannel, args: Array<out String>) {
        if (args.isEmpty()) {
            channel.sendMessage("Please provide a prefix").queue()
            return
        }
        val prefix = args[0]
        DiscordBot.config.prefix = prefix
        channel.sendMessage("Prefix set to $prefix").queue()
    }
}

private object GameBoyCommand : Command("gameboy", arrayOf("gb")) {
    override fun execute(member: Member, channel: TextChannel, args: Array<out String>) {
        ("It seems you're having issues with my GameBoy plugin, please confirm the following things:\n" +
                "1. You have not RELOADED, but restarted your server. Reloading tends to break plugins.\n" +
                "\n" +
                "2. You use the latest version of ProtocolLib available (This means the dev build when using 1.19+)").wrapAndPost(channel)
    }
}

private object LogsCommand : Command("logs", arrayOf()) {
    override fun execute(member: Member, channel: TextChannel, args: Array<out String>) {
        ("It seems that you have uploaded a screenshot and/or only a part of your log file. Please upload it completely to https://paste.kyllian.nl/ or download my UploadLogs plugin (https://www.spigotmc.org/resources/uploadlogs.108516/) and type /ul to upload your complete log automatically").wrapAndPost(channel)
    }
}

private object NesCommand : Command("nes", arrayOf()) {
    override fun execute(member: Member, channel: TextChannel, args: Array<out String>) {
        (
            "It seems you're having issues with my NES plugin, please confirm the following things:\n" +
                    "1. You have not RELOADED, but restarted your server. Reloading tends to break plugins.\n" +
                    "\n" +
                    "2. You use the latest version of ProtocolLib available (This means the dev build when using 1.19+)"
        ).wrapAndPost(channel)
    }
}

private object RomsCommand : Command("roms", arrayOf()) {
    override fun execute(member: Member, channel: TextChannel, args: Array<out String>) {
        (
            "It seems you're looking to download roms for my NES/GB plugin, we can not legally distribute those roms. A simple google search should suffice. Please note that:\n" +
            "\n" +
            "1. For Gameboy only .gb and .gbc are supported, not .gba\n" +
            "2. You need to reload the plugin (/(nes/gb) reload) in order for the roms to get recognized\n"
                ).wrapAndPost(channel)
    }
}

private object RatingCommand : Command("rating", arrayOf()) {
    override fun execute(member: Member, channel: TextChannel, args: Array<out String>) {
        ("I hope I helped you, and you are happy with my plugins. Please make sure to rate the plugin on SpigotMC as this helps me a lot. This is not a requirement, so please don't feel forced ❤️").wrapAndPost(channel)
    }
}