package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import fr.neocraft.main.Item.HammerData;
import fr.neocraft.main.Item.ItemArmorData;
import fr.neocraft.main.Item.ItemAxeData;
import fr.neocraft.main.Item.ItemPickaxeData;
import fr.neocraft.main.Item.ItemShovelData;
import fr.neocraft.main.Item.ItemSwordData;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class CommandRepair extends CommandBase {


    @Override
    public String getCommandName() {
        return "neorepair";
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.GRAY + "";
    }
    
    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel()
    {
        return 4;
    }
    /**
     * Return the required permission level for this command.
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
    	return true;
    }

    private int[] coef = new int[] {30, 20, 15, 7};
	@Override
	public void processCommand(ICommandSender ic, String[] arg) {
		EntityPlayer p = RegisterStage.getPlayer(arg[0]);
		int lvl = Integer.parseInt(arg[1]);
		ItemStack stack = p.getCurrentEquippedItem();
		if(stack != null)
		{
			if(stack.getItem() instanceof ItemTool 
					|| stack.getItem() instanceof HammerData 
					||stack.getItem() instanceof ItemAxeData
					|| stack.getItem() instanceof ItemPickaxeData
					|| stack.getItem() instanceof ItemShovelData
					|| stack.getItem() instanceof ItemSwordData
					|| stack.getItem() instanceof ItemBow
					|| stack.getItem() instanceof ItemTool
					|| stack.getItem() instanceof ItemSword
					|| stack.getItem() instanceof ItemArmorData
					|| stack.getItem() instanceof ItemHoe
					|| stack.getItem() instanceof noppes.npcs.items.ItemNpcWeaponInterface
					|| stack.getItem() instanceof noppes.npcs.items.ItemNpcArmor
					|| stack.getItem() instanceof noppes.npcs.items.ItemNpcInterface  )
			{
				if(stack.isItemDamaged())
				{
					int prix = stack.getItemDamage() * coef[lvl];
					PlayerStats stat = PlayerStats.get(p);
					if(stat.AsMoney(prix))
					{
						stat.removeMoney(prix, p);
						stack.setItemDamage(0);
					}
					else
					{
						p.addChatMessage(new ChatComponentTranslation("neo.DENY_NO_MONEY"));
						p.addChatMessage(new ChatComponentTranslation("neo.DENY_NO_MONEY_ALSO", EnumChatFormatting.RED+""+prix));
					}
				}else
				{
					p.addChatMessage(new ChatComponentTranslation("neo.cantfull.stack"));
				}
			}
			else
			{
				p.addChatMessage(new ChatComponentTranslation("neo.cant.stack"));
			}
		}else {
			p.addChatMessage(new ChatComponentTranslation("neo.no.stack"));
		}
		
	}

	
	
}
