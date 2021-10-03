package com.github.sylux6.watanabot.modules.anime_quote.entities

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import org.json.JSONObject

class AnimeQuote(json: JSONObject) {
    private val anime: String
    private val character: String
    private val quote: String

    init {
        this.anime = json.getString("anime")
        this.character = json.getString("character")
        this.quote = json.getString("quote")
    }

    fun toEmbedMessage(): MessageEmbed {
        return EmbedBuilder()
            .setDescription(this.quote)
            .setFooter("- ${this.character}\n${this.anime}")
            .build()
    }
}
