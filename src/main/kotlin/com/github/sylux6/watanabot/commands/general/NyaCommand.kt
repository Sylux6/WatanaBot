package com.github.sylux6.watanabot.commands.general

import com.github.sylux6.watanabot.commands.picture.PictureCommandModule.getImage
import com.github.sylux6.watanabot.internal.commands.AbstractCommand
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.kodehawa.lib.imageboards.entities.Rating

object NyaCommand : AbstractCommand("nya") {
    override val template: String
        get() = ""
    override val description: String
        get() = "Send a neko picture."

    override fun runCommand(event: MessageReceivedEvent, args: List<String>) {
        getImage(event, listOf("nekomimi"), if (event.textChannel.isNSFW) Rating.EXPLICIT else Rating.SAFE)
    }
}