package nl.kyllian.commands

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel


abstract class Command(val name: String, val aliases: Array<String>) {
    abstract fun execute(member: Member, channel: TextChannel, args: Array<out String>)
}