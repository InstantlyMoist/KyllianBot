package nl.kyllian

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import java.time.Instant

fun String.wrapAndPost(channel: TextChannel) {
    val builder = EmbedBuilder()
        .setTimestamp(Instant.now())
        .setColor(0x00ff00)
        .setDescription(this)
    channel.sendMessageEmbeds(builder.build()).queue()
}

