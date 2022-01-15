package kylenotfound.kylenotfound;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import java.util.Random;

public class DrownedAndStriderListener implements Listener {

    Random random = new Random();

    @EventHandler
    public void drownedOrStriderDied(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.DROWNED) {
            Drowned drowned = (Drowned) event.getEntity();
            Player killer = drowned.getKiller();
            if (killer != null) {
                ItemStack item = killer.getInventory().getItemInMainHand();
                doLoot(item, drowned);
            }
        }

        if (event.getEntityType() == EntityType.STRIDER) {
            Strider strider = (Strider) event.getEntity();
            Player killer = strider.getKiller();
            if (killer != null) {
                ItemStack item = killer.getInventory().getItemInMainHand();
                doLoot(item, strider);
            }
        }
    }

    private void doLoot(ItemStack item, Entity entity) {
        if (item == null) {
            return;
        }

        //this isnt the best way of doing random chance but oh well
        if (item.getEnchantments().containsKey(Enchantment.LOOT_BONUS_MOBS)) {
            int level = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
            int chance = random.nextInt(11) + 1; //bound 1 through 10
            int amount = 0;
            if (level == 1) {
                //10% chance of 1 with looting 1
                if (chance <= 1) {
                    amount = 1;
                }
            }
            if (level == 2) {
                //20% chance of 2 with looting 2
                if (chance <= 2) {
                    amount = 2;
                } else if (chance == 3){
                    amount = 1;
                }
            }
            if (level == 3) {
                //30% chance of 3 with looting 3
                if (chance <= 3) {
                    amount = 3;
                } else if (chance == 4) {
                    amount = 2;
                } else if (chance == 5) {
                    amount = 1;
                }
            }
            entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.PHANTOM_MEMBRANE, amount));
        } else {
            //5% chance with no looting
            if ((random.nextInt(101) + 1) <= 5) {
                entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.PHANTOM_MEMBRANE, 1));
            }
        }
    }

}
