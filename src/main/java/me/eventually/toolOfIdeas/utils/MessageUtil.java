package me.eventually.toolOfIdeas.utils;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MessageUtil {

    public static void sendMessage(@NotNull Player p, @NotNull String message)
    {
        message = "&8[&cTOI&8] " + message;
        p.sendMessage(message.replace("&", "§"));
    }
}
