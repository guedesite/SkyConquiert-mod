package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class CommandFurnace extends CommandBase {


    @Override
    public String getCommandName() {
        return "neofurnace";
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

	@Override
	public void processCommand(ICommandSender ic, String[] arg) {
		EntityPlayer p = RegisterStage.getPlayer(arg[0]);
		ItemStack stack = p.getCurrentEquippedItem();
		if(stack != null)
		{
			ItemStack fur = FurnaceRecipes.smelting().getSmeltingResult(stack).copy();
			if(fur != null)
			{
				fur.stackSize = stack.stackSize;
				p.setCurrentItemOrArmor(0, fur);
			}
			else
			{
				p.addChatMessage(new ChatComponentTranslation("neo.cant.stackfur"));
			}
		}else {
			p.addChatMessage(new ChatComponentTranslation("neo.no.stack"));
		}
		
	}

	
	
}

