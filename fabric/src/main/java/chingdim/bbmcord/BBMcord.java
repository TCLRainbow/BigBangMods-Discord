package chingdim.bbmcord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.fabricmc.api.ModInitializer;

public class BBMcord implements ModInitializer {
    @Override
    public void onInitialize() {
        DiscordRPC.discordInitialize("869624084311445564", new DiscordEventHandlers(), true);
        DiscordRichPresence rpc = new DiscordRichPresence.Builder("Mythra Aegis")
                .setStartTimestamps(System.currentTimeMillis() / 1000)
                .build();
        DiscordRPC.discordUpdatePresence(rpc);
    }
}
