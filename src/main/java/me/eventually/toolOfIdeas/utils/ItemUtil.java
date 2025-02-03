package me.eventually.toolOfIdeas.utils;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadableNBT;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemUtil{
    public static final String[] TOI_ITEM_TYPES = {"VAMPIRE_EFFECT_GENERATOR"};
    public static final Map<String, List<String>> TOI_ITEM_META = Map.of(
            "VAMPIRE_EFFECT_GENERATOR", List.of("&c&l吸血发生器", "&c使用后, 在10秒内对对手造成的伤害将恢复为自己的生命值。")
    );
    public static Boolean isPluginItem(ItemStack item){
        ReadableNBT nbt = NBT.readNbt(item);
        if (!nbt.hasTag("TOIItemType")){
            return false;
        }
        for (String _type : TOI_ITEM_TYPES){
            if (nbt.getString("TOIItemType").equals(_type)){
                return true;
            }
        }
        return false;
    }
    public static String getPluginItemType(ItemStack item){
        ReadableNBT nbt = NBT.readNbt(item);
        return nbt.getString("TOIItemType");
    }
    public static void setPluginItemType(@NotNull ItemStack item, @NotNull String type){
        if (!Arrays.asList(TOI_ITEM_TYPES).contains(type)){
            throw new IllegalArgumentException("Invalid TOIItemType");
        }
        NBT.modify(item, nbt -> {
            nbt.setString("TOIItemType", type);
        });
        modifyItem(item, TOI_ITEM_META.get(type).get(0), TOI_ITEM_META.get(type).get(1), type);
    }
    public static void givePlayerPluginItem(@NotNull Player p, @NotNull ItemStack item, @NotNull String type, int amount){
        setPluginItemType(item, type);
        item.setAmount(amount);
        if (p.getInventory().firstEmpty() == -1){
            p.getWorld().dropItemNaturally(p.getLocation(), item);
        }
        else{
            p.getInventory().addItem(item);
        }
    }
    public static void modifyItem(@NotNull ItemStack item, @NotNull String name, @NotNull String lore, @NotNull String id){
        ItemMeta meta = item.getItemMeta();
        meta.setItemName(name.replace("&", "§"));
        meta.setLore(List.of(lore.replace("&", "§"), "§7TOI Item ID: " + id ));
        item.setItemMeta(meta);
    }
}
