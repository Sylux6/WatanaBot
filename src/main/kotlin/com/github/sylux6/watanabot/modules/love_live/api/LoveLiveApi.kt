package com.github.sylux6.watanabot.modules.love_live.api

import com.github.sylux6.watanabot.modules.love_live.LoveLiveCommandModule
import com.github.sylux6.watanabot.modules.love_live.entities.Card
import com.github.sylux6.watanabot.utils.getRequest
import com.github.sylux6.watanabot.utils.stringToJsonObject
import net.dv8tion.jda.api.entities.User
import okhttp3.OkHttpClient
import java.io.IOException

fun getCardByID(id: Int, user: User): Card? {
    return try {
        val client = OkHttpClient()
        val json = stringToJsonObject(getRequest(client, "${LoveLiveCommandModule.CARD_API_URL}/${Integer.valueOf(id)}"))
        if (json.has("detail")) null else Card(json, user)
    } catch (e: IOException) {
        null
    }
}
