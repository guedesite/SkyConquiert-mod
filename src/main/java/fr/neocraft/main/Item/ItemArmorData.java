package fr.neocraft.main.Item;

import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import cpw.mods.fml.common.FMLCommonHandler;
import fr.neocraft.main.neoreference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;

public class ItemArmorData extends ItemArmor
{

    public ItemArmorData(ArmorMaterial material, int metaData)
    {
        super(material, 0, metaData);
    }
    
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
    	if(stack.getItem() == ItemMod.NeoDium_Leggings)
        {
            return neoreference.MOD_ID + ":textures/models/armor/NeoDium_layer_2.png";
        }
        
        else if(stack.getItem() == ItemMod.NeoDium_Helmet || stack.getItem() == ItemMod.NeoDium_ChestPlate || stack.getItem() == ItemMod.NeoDium_Boot)
        {
            return neoreference.MOD_ID + ":textures/models/armor/NeoDium_layer_1.png";
        }
        
        else if(stack.getItem() == ItemMod.OnyxR_Helmet || stack.getItem() == ItemMod.OnyxR_ChestPlate || stack.getItem() == ItemMod.OnyxR_Boot)
        {
            return neoreference.MOD_ID + ":textures/models/armor/OnyxR_layer_1.png";
        }
        
        else if(stack.getItem() == ItemMod.Onyx_Helmet || stack.getItem() == ItemMod.Onyx_ChestPlate || stack.getItem() == ItemMod.Onyx_Boot)
        {
            return neoreference.MOD_ID + ":textures/models/armor/Onyx_layer_1.png";
        }
        
        else if(stack.getItem() == ItemMod.God_Leggings)
        {
            return neoreference.MOD_ID + ":textures/models/armor/God_layer_2.png";
        }
        
        else if(stack.getItem() == ItemMod.God_Helmet || stack.getItem() == ItemMod.God_ChestPlate || stack.getItem() == ItemMod.God_Boot)
        {
            return neoreference.MOD_ID + ":textures/models/armor/God_layer_1.png";
        }
        
        else if(stack.getItem() == ItemMod.Silithium_Leggings)
        {
            return neoreference.MOD_ID + ":textures/models/armor/Silithium_layer_2.png";
        }
        
        else if(stack.getItem() == ItemMod.Silithium_Helmet || stack.getItem() == ItemMod.Silithium_ChestPlate || stack.getItem() == ItemMod.Silithium_Boot)
        {
            return neoreference.MOD_ID + ":textures/models/armor/Silithium_layer_1.png";
        }
        
        

        
        
        else if(stack.getItem() == ItemMod.Titane_Leggings)
        {
            return neoreference.MOD_ID + ":textures/models/armor/Titane_layer_2.png";
        }
        
        else if(stack.getItem() == ItemMod.Titane_Helmet || stack.getItem() == ItemMod.Titane_ChestPlate || stack.getItem() == ItemMod.Titane_Boot)
        {
            return neoreference.MOD_ID + ":textures/models/armor/Titane_layer_1.png";
        }
        
        
        
        else if(stack.getItem() == ItemMod.Mythrile_Leggings)
        {
            return neoreference.MOD_ID + ":textures/models/armor/Mythrile_layer_2.png";
        }
        
        else if(stack.getItem() == ItemMod.Mythrile_Helmet || stack.getItem() == ItemMod.Mythrile_ChestPlate || stack.getItem() == ItemMod.Mythrile_Boot)
        {
            return neoreference.MOD_ID + ":textures/models/armor/Mythrile_layer_1.png";
        }
        
        if(stack.getItem() == ItemMod.OnyxR_Leggings)
        {
            return neoreference.MOD_ID + ":textures/models/armor/OnyxR_layer_2.png";
        }
        if(stack.getItem() == ItemMod.Onyx_Leggings)
        {
            return neoreference.MOD_ID + ":textures/models/armor/Onyx_layer_2.png";
        }
        
        return null;
    }
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        if(this == ItemMod.Silithium_Helmet)
        {
            player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 300, 0));
        }
        else if(this == ItemMod.NeoDium_Helmet)
        {
            player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 300, 0));
        }
        else  if(this == ItemMod.Mythrile_Boot)
        {
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 40, 0));
        }
        else if(this == ItemMod.OnyxR_ChestPlate)
        {
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 300, 0));
        }else if(this == ItemMod.Onyx_Boot && player.worldObj.getBlock((int)player.posX, (int)player.posY-1,(int) player.posZ) == Blocks.soul_sand)
        {
        	player.motionX /= 0.4;
        	player.motionZ /= 0.4;
        }else if(this == ItemMod.OnyxR_Boot && player.worldObj.getBlock((int)player.posX, (int)player.posY-1,(int) player.posZ) == Blocks.soul_sand)
        {
        	player.motionX /= 0.4;
        	player.motionZ /= 0.4;
        }
        else if(this == ItemMod.God_Boot) {
        	if(!player.isSneaking() && !PlayerStats.get(player).isInAttack() && player.worldObj.getBlock((int) player.posX, (int)player.posY-3,(int)player.posZ) == Blocks.air&& player.worldObj.getBlock((int) player.posX, (int)player.posY-2,(int)player.posZ) == Blocks.air)
        	{
        		player.motionY = -0.01;
        	}
        }else if(this == ItemMod.God_Helmet)
        {
        	if(player.isInWater() && !PlayerStats.get(player).isInAttack())
        	{
        	
        	}
        } else if(this == ItemMod.God_Leggings)
        {
        	if(!PlayerStats.get(player).isInAttack())
        	{
        		 player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 300, 2));
        	}
           
        } else if(this == ItemMod.God_ChestPlate)
        {
        	if(!PlayerStats.get(player).isInAttack())
        	{
        		 player.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 300, 2));
        	}
        }
    
      
    }
    
}