package fr.neocraft.main.Blocks;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Init.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSeed2UpCorn extends BlockCrops{
	@SideOnly(Side.CLIENT)
    private IIcon[] field_149867_a;
	private Block blockdown = BlockMod.Corn_Culture_Down;
	private int stat = 0;
	public BlockSeed2UpCorn() {
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.disableStats();
	}

	@Override
	protected Item func_149866_i()
    {
        return null;
    }
	
	@Override
    protected Item func_149865_P()
    {
		return null;
    }
	@Override
	protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return true;
    }
	@Override
	 public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	    {
	        return true;
	    }

	@Override
	public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        
    }
	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
       return null;
    }

  
    public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_2_ < 0 || p_149691_2_ > 7)
        {
            p_149691_2_ = 7;
        }

        return this.field_149867_a[p_149691_2_];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_149867_a = new IIcon[8];

        for (int i = 0; i < this.field_149867_a.length; ++i)
        {
            this.field_149867_a[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_up_" + i);
        }
    }
	 @Override
	    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	    {
	       
	        return null;
	    }
	 @Override
	 public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	    {
	      if(p_149695_1_.getBlock(p_149695_2_,p_149695_3_ -1, p_149695_4_) != this.blockdown)
	       {
	    	  p_149695_1_.setBlock(p_149695_2_,p_149695_3_, p_149695_4_, Blocks.air, 0,2);
	       } 
	    }
	 @Override
	 public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	    {
		 int meta =  p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_ - 1, p_149674_4_);
		 if(p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_) != meta)
		 {
		 	p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, meta, 2);
		 }
		 }
	 @Override
	 public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
	    {
	        return  true;
	    }
	 @Override
	 public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
	    {
		 p_149749_1_.getBlock(p_149749_2_, p_149749_3_- 1, p_149749_4_).breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	    }
}
