package com.github.sylux6.watanabot.modules.anime_quote.commands

import com.github.sylux6.watanabot.internal.commands.AbstractCommand
import com.github.sylux6.watanabot.modules.anime_quote.api.getAnimeQuote
import com.github.sylux6.watanabot.modules.anime_quote.entities.AnimeQuote
import com.github.sylux6.watanabot.utils.sendMessage
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

object AnimeQuoteQuoteCommand : AbstractCommand("quote") {
    override val template: String
        get() = ""
    override val description: String
        get() = "Get a random anime quote"

    override fun runCommand(event: MessageReceivedEvent, args: List<String>): AnimeQuote {
        val animeQuote = getAnimeQuote()
        sendMessage(event.channel, animeQuote.toEmbedMessage())
        return animeQuote
    }
}
