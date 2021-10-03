package com.github.sylux6.watanabot.modules.anime_quote.api

import com.github.sylux6.watanabot.modules.anime_quote.AnimeQuoteCommandModule
import com.github.sylux6.watanabot.modules.anime_quote.entities.AnimeQuote
import com.github.sylux6.watanabot.utils.getRequest
import com.github.sylux6.watanabot.utils.stringToJsonObject
import okhttp3.OkHttpClient

fun getAnimeQuote(): AnimeQuote {
    val client = OkHttpClient()
    val json = stringToJsonObject(getRequest(client, "${AnimeQuoteCommandModule.ANIME_CHAN_API}/random"))
    return AnimeQuote(json)
}
