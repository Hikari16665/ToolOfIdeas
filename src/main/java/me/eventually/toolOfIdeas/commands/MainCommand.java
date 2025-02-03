package me.eventually.toolOfIdeas.commands;

import me.eventually.toolOfIdeas.ToolOfIdeas;
import me.eventually.toolOfIdeas.utils.ItemUtil;
import me.eventually.toolOfIdeas.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class MainCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player) commandSender;
        if (strings.length == 0){
            MessageUtil.sendMessage(p, "&c命令不能没有参数.");
            return false;
        }
        if (!p.isOp()){
            MessageUtil.sendMessage(p, "&c你没有权限使用这个命令.");
            return false;
        }
        if (strings[0].equalsIgnoreCase("give")){
            ItemStack item = switch (strings[2].toLowerCase()) {
                case "vampire_effect_generator" -> new ItemStack(Material.WITHER_SKELETON_SKULL);
                default -> throw new IllegalArgumentException("Invalid TOIItemType");
            };
            // /toi give <player> <item> <amount>
            // check if the player is online
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (player.getName().equals(strings[1])){
                    ItemUtil.givePlayerPluginItem(player, item, strings[2], Integer.parseInt(strings[3]));
                    MessageUtil.sendMessage(player, "&a你获得了 " + strings[2] + " x" + strings[3] + ".");
                }
            });
            return true;
        }
        if (strings[0].equalsIgnoreCase("version")){
            MessageUtil.sendMessage(p, "&aToolOfIdeas v" + ToolOfIdeas.instance.getDescription().getVersion());
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return switch (strings.length) {
            case 0, 1 -> List.of("give", "version");
            case 2 -> Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
            case 3 -> Arrays.stream(ItemUtil.TOI_ITEM_TYPES).toList();
            case 4 -> Arrays.asList("1", "64", "请填写数量");
            default -> List.of();
        };
    }
}
