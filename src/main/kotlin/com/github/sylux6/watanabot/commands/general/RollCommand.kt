package com.github.sylux6.watanabot.commands.general

import com.github.sylux6.watanabot.internal.commands.AbstractCommand
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import com.github.sylux6.watanabot.utils.BotUtils
import com.github.sylux6.watanabot.utils.MessageUtils
import com.github.sylux6.watanabot.utils.MessageUtils.sendMessage
import kotlin.random.Random

object RollCommand : AbstractCommand("roll") {
    override val template: String
        get() = ""
    override val description: String
        get() = "Roll a random number between 1 and 100."

    override fun runCommand(event: MessageReceivedEvent, args: List<String>) {
        sendMessage(event.channel,
                "${MessageUtils.mentionAt(event.author)} ${Random.nextInt(100) + 1} "
                        + BotUtils.getEmojiMessage(event.guild, "yousoro"))
    }
}