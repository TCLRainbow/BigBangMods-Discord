package chingdim.bbmcord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("bbmcord")
public class BBMcord {

    public BBMcord() {
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(EventPriority.LOWEST, this::onBooting);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(EventPriority.LOWEST, this::onBooted);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, BBMcord::onJoin);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, BBMcord::onLeave);
    }

    private void onBooting(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        DiscordRPC.discordInitialize("869624084311445564", new DiscordEventHandlers(), true);
        updateRPCDetails("Booting");
    }

    private void onBooted(final FMLLoadCompleteEvent event) {
        updateRPCDetails("Main menu");
    }

    @SubscribeEvent
    public static void onJoin(ClientPlayerNetworkEvent.LoggedInEvent event) {
        updateRPCDetails(
                event.getNetworkManager().channel().remoteAddress().toString().startsWith("local:E") ? "Singleplayer"
                        : "Multiplayer"
        );
    }

    @SubscribeEvent
    public static void onLeave(ClientPlayerNetworkEvent.LoggedOutEvent event) {
        updateRPCDetails("Main menu");
    }


    private static void updateRPCDetails(String details) {
        String codename = "Pandora";
        DiscordRichPresence rpc = new DiscordRichPresence.Builder(codename + " (Forge 1.16.5)")
                .setDetails(details)
                .setStartTimestamps(System.currentTimeMillis() / 1000)
                .setBigImage(codename.toLowerCase(), codename)
                .build();
        DiscordRPC.discordUpdatePresence(rpc);
    }
}