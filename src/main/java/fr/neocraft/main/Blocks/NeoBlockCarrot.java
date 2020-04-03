package fr.neocraft.main.Blocks;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.ServeurManager.DailyManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;

public class NeoBlockCarrot extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private IIcon[] field_149868_a;
    private static final String __OBFID = "CL_00000212";

    private int temps = 1;
    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_2_ < 7)
        {
            if (p_149691_2_ == 6)
            {
                p_149691_2_ = 5;
            }

            return this.field_149868_a[p_149691_2_ >> 1];
        }
        else
        {
            return this.field_149868_a[3];
        }
    }

    protected Item func_149866_i()
    {
        return null;
    }

    protected Item func_149865_P()
    {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_149868_a = new IIcon[4];

        for (int i = 0; i < this.field_149868_a.length; ++i)
        {
            this.field_149868_a[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
    @Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
		this.dropBlockAsItemWithChance(p_149749_1_, p_149749_2_,p_149749_3_,  p_149749_4_, p_149749_6_, 0F, 0);
    }
    @Override
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);

        if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9)
        {
            int l = p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);

            if (l < 7)
            {
                float f = this.func_149864_n(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);

                if (p_149674_5_.nextInt((int)(25.0F / f) + 1) == 0)
                {
                	if(temps != 1 & p_149674_5_.nextInt(temps) == 0)
                	{
	                    ++l;
	                    p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, l, 2);
                	}
                }
            }
        }
    }
    private float func_149864_n(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_)
    {
        float f = 1.0F;
        Block block = p_149864_1_.getBlock(p_149864_2_, p_149864_3_, p_149864_4_ - 1);
        Block block1 = p_149864_1_.getBlock(p_149864_2_, p_149864_3_, p_149864_4_ + 1);
        Block block2 = p_149864_1_.getBlock(p_149864_2_ - 1, p_149864_3_, p_149864_4_);
        Block block3 = p_149864_1_.getBlock(p_149864_2_ + 1, p_149864_3_, p_149864_4_);
        Block block4 = p_149864_1_.getBlock(p_149864_2_ - 1, p_149864_3_, p_149864_4_ - 1);
        Block block5 = p_149864_1_.getBlock(p_149864_2_ + 1, p_149864_3_, p_149864_4_ - 1);
        Block block6 = p_149864_1_.getBlock(p_149864_2_ + 1, p_149864_3_, p_149864_4_ + 1);
        Block block7 = p_149864_1_.getBlock(p_149864_2_ - 1, p_149864_3_, p_149864_4_ + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for (int l = p_149864_2_ - 1; l <= p_149864_2_ + 1; ++l)
        {
            for (int i1 = p_149864_4_ - 1; i1 <= p_149864_4_ + 1; ++i1)
            {
                float f1 = 0.0F;

                if (p_149864_1_.getBlock(l, p_149864_3_ - 1, i1).canSustainPlant(p_149864_1_, l, p_149864_3_ - 1, i1, ForgeDirection.UP, this))
                {
                    f1 = 1.0F;

                    if (p_149864_1_.getBlock(l, p_149864_3_ - 1, i1).isFertile(p_149864_1_, l, p_149864_3_ - 1, i1))
                    {
                        f1 = 3.0F;
                    }
                }

                if (l != p_149864_2_ || i1 != p_149864_4_)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }

        return f;
    }
	 @Override
	    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	    {
	        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

	        if (metadata >= 7)
	        {
	        	
	        	ret.add(new ItemStack(Items.carrot, 1, 0));
	        	if(world.rand.nextInt(2) == 0)
	        	{
	        		ret.add(new ItemStack(Items.carrot, 1, 0));
	        	}
	            for (int i = 0; i < 3 + fortune; ++i)
	            {
	                if (world.rand.nextInt(15) <= metadata)
	                {
	                    ret.add(new ItemStack(Items.carrot, 1, 0));
	                }
	            }
	            if(world.getBlock(x, y-1, z) == BlockMod.FarmLand)
	            {
	            	for (int i = 0; i < 2 + fortune; ++i)
		            {
		                if (world.rand.nextInt(10) <= metadata)
		                {
		                    ret.add(new ItemStack(Items.carrot, 1, 0));
		                }
		            }
	            }
	            if(DailyManager.getIdDayByName() == 2)
	            {
	            	Chunk c = world.getChunkFromBlockCoords(x, z);
	            	Stage stage = RegisterStage.getStageAtXY(c.xPosition, c.zPosition, world);
	            	EntityPlayer pl = RegisterStage.getPlayer(stage.getOwner());
	            	if(stage != null && pl != null)
	            	{
	            		int rat =(int) (ret.size() * PlayerStats.get(pl).bonusday) - ret.size();
	            		if(rat != 0)
	            		{
		            		for (int i = 0; i < rat; ++i)
				            {
				                    ret.add(new ItemStack(Items.carrot, 1, 0));
				            }
	            		}
	            	}
	            }
	        } else {
	        	ret.add(new ItemStack(Items.carrot, 1, 0));
	        }

	        return ret;
	    }
	@Override
	public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }
	@Override
	protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.farmland || p_149854_1_ == BlockMod.FarmLand;
    }
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }
}