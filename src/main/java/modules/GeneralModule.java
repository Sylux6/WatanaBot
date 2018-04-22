package modules;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import core.CommandHandler;
import modules.picture.PictureModule;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.utils.IOUtil;
import net.kodehawa.lib.imageboards.entities.Rating;
import utils.BotUtils;
import utils.EmbedUtils;

public class GeneralModule extends AbstractModule {

    @Override
    public void populate() {
	commands.put("test", (event, args) -> {
	    BotUtils.sendMessage(event.getChannel(), "You ran the test command with args: " + args);
	});

	commands.put("help", (event, args) -> {
	    if (args.size() < 2) {
		BotUtils.sendMessage(event.getChannel(), getHelp());
	    } else {
		AbstractModule module = CommandHandler.moduleMap.get(args.get(1));
		if (module == null)
		    BotUtils.sendMessage(event.getChannel(), args.get(1) + " module not found");
		else
		    BotUtils.sendMessage(event.getChannel(), module.getHelp());
	    }
	});

	commands.put("module", (event, args) -> {
	    StringBuilder help = new StringBuilder("**Module list:**\n");
	    for (String s : CommandHandler.moduleMap.keySet()) {
		help.append("- `" + s + "`\n");
	    }
	    BotUtils.sendMessage(event.getChannel(), help.toString());
	});

	commands.put("roll", (event, args) -> {
	    if (BotUtils.yousoroEmojiExists(event.getGuild()))
		BotUtils.sendMessage(event.getChannel(), BotUtils.mentionAt(event.getAuthor()) + " " + roll() + " "
			+ BotUtils.getEmojiMessage(event.getGuild(), "yousoro"));
	    else
		BotUtils.sendMessage(event.getChannel(), BotUtils.mentionAt(event.getAuthor()) + " " + roll() + "");
	});

	commands.put("lewd", (event, args) -> {
	    BotUtils.sendMessage(event.getChannel(),
		    EmbedUtils.buildEmbedImageOnly("https://puu.sh/yWilT/c92b12e8cd.png"));
	});
	
	commands.put("hentai", (event, args) -> {
	    BotUtils.sendMessage(event.getChannel(),
		    EmbedUtils.buildEmbedImageOnly("http://puu.sh/zTTKM/266bfeb83c.png"));
	});

	commands.put("nya", (event, args) -> {
	    PictureModule.getImage(event, "nekomimi", event.getTextChannel().isNSFW() ? Rating.EXPLICIT : Rating.SAFE);
	});

	commands.put("mukyu", (event, args) -> {
	    PictureModule.getImage(event, "patchouli_knowledge",
		    event.getTextChannel().isNSFW() ? Rating.EXPLICIT : Rating.SAFE);
	});

	commands.put("yousoro", (event, args) -> {
	    PictureModule.getImage(event, "watanabe_you",
		    event.getTextChannel().isNSFW() ? Rating.EXPLICIT : Rating.SAFE);
	});

	commands.put("kanan", (event, args) -> {
	    PictureModule.getImage(event, "matsuura_kanan",
		    event.getTextChannel().isNSFW() ? Rating.EXPLICIT : Rating.SAFE);
	});

	commands.put("zura", (event, args) -> {
	    PictureModule.getImage(event, "kunikida_hanamaru",
		    event.getTextChannel().isNSFW() ? Rating.EXPLICIT : Rating.SAFE);
	});

	commands.put("ganbaruby", (event, args) -> {
	    PictureModule.getImage(event, "kurosawa_ruby",
		    event.getTextChannel().isNSFW() ? Rating.EXPLICIT : Rating.SAFE);
	});
	
	commands.put("test", (event, args) -> {
	    MessageEmbed m = new EmbedBuilder()
		    .setTitle("Birthday")
		    .setDescription("TODAY IS MY BIRTHDAY! "+BotUtils.getEmojiMessage(event.getGuild(), "poiBuki")+BotUtils.getYousoro(event.getGuild()))
		    .setImage("https://puu.sh/A3DA1/de62cafe1a.jpg")
		    .build();
	    BotUtils.sendMessage(event.getChannel(), m);
	});
	
	commands.put("choose", (event, args) -> {
	    String p[] = {"Obviously", "I take", "The best would be"};
	    int r1 = BotUtils.random.nextInt(p.length), r2 = BotUtils.random.nextInt(args.size()-1)+1;
	    BotUtils.sendMessage(event.getChannel(), p[r1]+" **"+args.get(r2)+"**");
	});
	
    }

    // Auxiliar functions:

    private int roll() {
	BotUtils.random.setSeed(System.currentTimeMillis());
	return Math.abs((BotUtils.random.nextInt() % 100)) + 1;
    }

    @Override
    public String getName() {
	return "general";
    }

}
