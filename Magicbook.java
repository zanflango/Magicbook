    /**
     * Magic book for VampireZ on Hypixel by zanflango
     * if a player holds a written book in their hand for 20-25 seconds it puts an effect on all players within 5 blocks
     * if the player left clicks with the book on anything\hits something it reset the count. 
     * currently the player can right click the book to see the counter and can right click on doors and similar. 
     * The idea is that it's a holy book and the player is reading from it to provide a boost to nearby players
     * The title of the book must be "Book of ..." for the code to key off of
     * The first page of the book is used as a counter, the second page contains the name of the potion effect
     * the third page is the is the durration of the effect, and the fourth is the potion level
* /give zanf    lango written_book 1 0 {title:"Book of Healing",pages:["0","REGENERATION","100","0"]}
* /give zanflango written_book 1 0 {title:"Book of Speed",pages:["0","SPEED","100","2"]}
* /give zanflango written_book 1 0 {title:"Book of Strength",pages:["0","INCREASE_DAMAGE","100","2"]}
    */

package zanflango.magicbook;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;


/**
 *
 * @author zanflango
 */
public class MagicBook extends JavaPlugin implements Listener{
    
        @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        
      BukkitRunnable runnable = new BukkitRunnable(){
          @Override
          public void run(){     
          for(Player player:Bukkit.getOnlinePlayers()){  
               if(player.getItemInHand() != null && player.getItemInHand().getType().equals(Material.WRITTEN_BOOK)) 
               {
                    ItemStack book = player.getItemInHand();
                     BookMeta bm = (BookMeta)book.getItemMeta();
                     if(bm.getTitle().startsWith("Book of")){
                    String pg = bm.getPage(1);
                     Integer cnt=0;
                    if (pg==null || pg.isEmpty()) {
                        bm.setPage(1, "0");
                    }else{
                        cnt= Integer.valueOf(pg)+1;
                        bm.setPage(1, cnt.toString() );
                       }  
                    book.setItemMeta(bm);
                    /**
                    This is the counter check. currently the counter updates ever 5 seconds. 
                    * so >5 should be 20-25 seconds.
                     */    
                    if(cnt > 5){
                    for(Player pother:Bukkit.getOnlinePlayers()){
                        /**
                         * Below value is the distance of the effect. currently set to 5 blocks.
                         */
                        if(player.getLocation().distance(pother.getLocation())<6){
                            String effect = bm.getPage(2);
                            int dur= Integer.parseInt(bm.getPage(3));
                            int lvl= Integer.parseInt(bm.getPage(4));
                            pother.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect), dur, lvl));
                            /**
                            if(bm.getTitle().equalsIgnoreCase("Book of Healing")){
                                 pother.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));   
                            }
                            if(bm.getTitle().equalsIgnoreCase("Book of Speed")){
                                 pother.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));   
                            }
                            if(bm.getTitle().equalsIgnoreCase("Book of Strength")){
                                 pother.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 2));   
                            } 
                            * **/
                        }
                        }
                    }
                    }
               }
                }
            }          
      };
      runnable.runTaskTimer(this,0,100); //* 100 ticks = 5 seconds
    }
 
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType().equals(Material.WRITTEN_BOOK)) {
                ItemStack book = event.getPlayer().getItemInHand();
                BookMeta bm = (BookMeta)book.getItemMeta();
                String pg = bm.getPage(1);
                bm.setPage(1, "0");
                 book.setItemMeta(bm);
            }
        }
    }
     @EventHandler
     public void onEntityDamage(PlayerInteractEvent event) {
                 if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType().equals(Material.WRITTEN_BOOK)) {
                ItemStack book = event.getPlayer().getItemInHand();
                BookMeta bm = (BookMeta)book.getItemMeta();
                String pg = bm.getPage(1);
                bm.setPage(1, "0");
                 book.setItemMeta(bm);
            }
        }
     }
    
       @EventHandler
     public void onPlayerScroll (PlayerItemHeldEvent  event) {

            if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType().equals(Material.WRITTEN_BOOK)) {
                ItemStack book = event.getPlayer().getItemInHand();
                BookMeta bm = (BookMeta)book.getItemMeta();
                String pg = bm.getPage(1);
                bm.setPage(1, "0");
                 book.setItemMeta(bm);  
            }
     }
    
    }

