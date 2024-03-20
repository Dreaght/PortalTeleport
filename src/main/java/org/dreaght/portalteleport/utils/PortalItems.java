package org.dreaght.portalteleport.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class PortalItems {
    public static final ItemStack BLUE_POINT = createItemStack(Material.LAPIS_LAZULI, 1,
            (ChatColor.BLUE + "Blue Point" + ChatColor.GRAY + " (Left Click)"),
            new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Use this to indicate where portal begins.")));

    public static final ItemStack RED_POINT = createItemStack(Material.RED_DYE, 1,
            (ChatColor.RED + "Red Point" + ChatColor.GRAY + " (Right Click)"),
            new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Use this to indicate where portal ends.")));

    public static final ItemStack REGION_INFO = createItemStack(Material.BOOK, 1,
            (ChatColor.GREEN + "Region Information"),
            new ArrayList<>(Arrays.asList("")));

    public static final ItemStack REGION_CONFIRM = createItemStack(Material.LIME_WOOL, 1,
            (ChatColor.GREEN + "Confirm"),
            new ArrayList<>(Arrays.asList(
                    (ChatColor.GRAY + "Finish portal creation processing."),
                    (""),
                    (ChatColor.YELLOW + "Click to confirm!"))));

    public static final ItemStack REGION_DELETE = createItemStack(Material.REDSTONE_BLOCK, 1,
            (ChatColor.RED + "Delete"),
            new ArrayList<>(Arrays.asList(
                    (ChatColor.GRAY + "Delete this portal."),
                    (""),
                    (ChatColor.YELLOW + "Click to delete!"))));

    public static final ItemStack WINDOW_CLOSE = createItemStack(Material.BARRIER, 1,
            (ChatColor.RED + "Close"),null);

    public static final ItemStack REGION_SETTINGS = createItemStack(Material.COMPARATOR, 1,
            (ChatColor.GREEN + "Action"),
            new ArrayList<>(Arrays.asList(
                    (ChatColor.GRAY + "Set command this portal should run."),
                    (""))));

    private static ItemStack createItemStack(Material material, int amount, String displayName, ArrayList<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
