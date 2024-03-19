package org.dreaght.portalteleport.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class PortalItems {
    public static final ItemStack BLUE_POINT = createItemStack(Material.LAPIS_LAZULI, 1,
            (ChatColor.BLUE + "Blue Point" + ChatColor.GRAY + "(Left Click)"),
            new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Use this to indicate where portal begins.")));

    public static final ItemStack RED_POINT = createItemStack(Material.RED_DYE, 1,
            (ChatColor.RED + "Red Point" + ChatColor.GRAY + "(Right Click)"),
            new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Use this to indicate where portal ends.")));

    private static ItemStack createItemStack(Material material, int amount, String displayName, ArrayList<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }



}
