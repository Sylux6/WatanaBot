package com.github.sylux6.watanabot.commands.general

import com.github.sylux6.watanabot.internal.commands.AbstractCommand
import com.github.sylux6.watanabot.utils.EmbedUtils
import com.github.sylux6.watanabot.utils.MessageUtils.sendMessage
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

object LewdCommand : AbstractCommand("lewd") {
    override val template: String
        get() = ""
    override val description: String
        get() = "I'm not LEWD."

    override fun runCommand(event: MessageReceivedEvent, args: List<String>) {
        sendMessage(event.channel, EmbedUtils.buildEmbedImageOnly("https://i.imgur.com/VGXNX82.jpg"))
    }
}