package chingdim.bbmcord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("bbmcord")
public class BBMcord {

    public BBMcord() {
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }


    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        DiscordRPC.discordInitialize("869624084311445564", new DiscordEventHandlers(), true);
        DiscordRichPresence rpc = new DiscordRichPresence.Builder("Astraea")
                .setStartTimestamps(System.currentTimeMillis() / 1000)
                .build();
        DiscordRPC.discordUpdatePresence(rpc);
    }
}