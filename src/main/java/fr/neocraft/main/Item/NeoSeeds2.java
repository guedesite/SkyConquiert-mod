package fr.neocraft.main.Item;

import fr.neocraft.main.Init.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class NeoSeeds2 extends ItemSeeds
{
	 private Block field_150925_a, blockup;
	 private Block soilBlockID;
   public NeoSeeds2(Block down, Block up, Block p_i45352_2_)
   {
       super(down, p_i45352_2_);
       this.field_150925_a = down;
       this.blockup = up;
       this.soilBlockID = p_i45352_2_;
   }
   
   @Override
   public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
   {
	   if(p_77648_3_.isRemote) { return true;}
       if (p_77648_7_ != 1)
       {
           return false;
       }
       else if (p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_ + 1, p_77648_6_, p_77648_7_, p_77648_1_))
       {
           if (canSustainPlant(p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_), p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, ForgeDirection.UP, this) && p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + 1, p_77648_6_) & p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_+2, p_77648_6_))
           {
        	   p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + 2, p_77648_6_, this.blockup);
               p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + 1, p_77648_6_, this.field_150925_a);
               p_77648_1_.stackSize--;
               return true;
           }
           else
           {
               return false;
           }
       }
       else
       {
           return false;
       }
   }
   public boolean canSustainPlant(Block block, IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
   {
      if(block == Blocks.farmland || block == BlockMod.FarmLand)
      {
   	   return true;
      }
      return false;
   }
}

