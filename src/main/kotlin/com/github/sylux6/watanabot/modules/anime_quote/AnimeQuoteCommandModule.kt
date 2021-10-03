package com.github.sylux6.watanabot.modules.anime_quote

import com.github.sylux6.watanabot.internal.commands.AbstractCommandModule
import com.github.sylux6.watanabot.modules.anime_quote.commands.AnimeQuoteQuoteCommand

object AnimeQuoteCommandModule : AbstractCommandModule(
    "Anime quote",
    "q",
    setOf(AnimeQuoteQuoteCommand)
) {
    override val moduleDescription: String
        get() = "Commands related to anime quote"

    const val ANIME_CHAN_API = "https://animechan.vercel.app/api"
}
