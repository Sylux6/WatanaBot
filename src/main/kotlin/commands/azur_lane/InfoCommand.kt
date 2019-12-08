package commands.azur_lane

import com.github.azurapi.azurapikotlin.api.Atago
import com.github.azurapi.azurapikotlin.internal.exceptions.ApiException
import com.github.azurapi.azurapikotlin.internal.exceptions.ShipNotFoundException
import internal.commands.AbstractCommand
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import utils.MessageUtils.sendMessage

object InfoCommand : AbstractCommand("info", 1) {
    override val template: String
        get() = "<name>"
    override val description: String
        get() = "Get details about a ship."

    override fun runCommand(event: MessageReceivedEvent, args: List<String>) {
        try {
            val ship = Atago.getShipByName(args.joinToString(" "))
            sendMessage(event.channel, AzurLaneCommandModule.shipToEmbed(ship))
        } catch (e: ShipNotFoundException) {
            sendMessage(event.channel, "Not found")
            return
        }
    }
}