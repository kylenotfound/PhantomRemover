package kylenotfound.kylenotfound;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class PhantomRemover extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginDescriptionFile pdf = getDescription();
        getServer().getLogger().info("Removing all Phantoms. Loaded " + pdf.getName() + " " + pdf.getVersion());
        getServer().getPluginManager().registerEvents(new PhantomListener(), this);
        getServer().getPluginManager().registerEvents(new DrownedAndStriderListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
