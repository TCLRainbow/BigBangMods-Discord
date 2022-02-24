package chingdim.bbmcord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;

public class BBMcord implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        DiscordRPC.discordInitialize("869624084311445564", new DiscordEventHandlers(), true);
        updateRPCDetails("Booting");

        ClientLifecycleEvents.CLIENT_STARTED.register(
                client -> ScreenEvents.afterTick(client.currentScreen).register(
                        screen -> updateRPCDetails("Main menu")
                )
        );

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) ->
                updateRPCDetails(handler.getConnection().isLocal() ? "Singleplayer" : "Multiplayer")
        );

        ClientPlayConnectionEvents.DISCONNECT.register(
                (handler, client) -> updateRPCDetails("Main menu")
        );
    }

    private static void updateRPCDetails(String details) {
        final String assetUrl = "https://i.imgur.com/yAgOBMQ.png";
        DiscordRichPresence rpc = new DiscordRichPresence.Builder("Astraea GN (Fabric 1.17.1)")
                .setStartTimestamps(System.currentTimeMillis() / 1000)
                .setDetails(details)
                .setBigImage(assetUrl, assetUrl)
                .build();
        DiscordRPC.discordUpdatePresence(rpc);
    }
}
