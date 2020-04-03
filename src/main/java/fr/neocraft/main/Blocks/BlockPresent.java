package fr.neocraft.main.Blocks;

import com.tmtravlr.jaff.JAFFMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPresent extends Block
{
	@SideOnly(Side.CLIENT)
    private IIcon top;
    
	public BlockPresent(Material p_i45394_1_)
    {
        super(p_i45394_1_);
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(neoreference.MOD_ID + ":present");
        this.top = iiconRegister.registerIcon(neoreference.MOD_ID + ":present_top");
    }
	@SideOnly(Side.CLIENT)
	@Override
    public IIcon getIcon(int side, int metadata)
    {
        if(side == 1)
        {
            return this.top;
        }
        return this.blockIcon;

    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
        {
            return true;
        }
        else
        {
            Stage stage = RegisterStage.getStageAtXY(world.getChunkFromBlockCoords(x, z).xPosition, world.getChunkFromBlockCoords(x, z).zPosition, world);
            if(stage != null)
            {
            	if(stage.isOwner(player.getCommandSenderName()))
            	{
            		world.setBlock(x, y, z, Blocks.air);
            		ItemStack[] stack = null;
            		if(stage.getIdStage() == 0)
            		{
            			stack = new ItemStack[] {
        						new ItemStack(Items.water_bucket, 1),
        						new ItemStack(Items.water_bucket, 1),
        						new ItemStack(Items.lava_bucket, 1),
        						new ItemStack(Items.bread, 4),
        						new ItemStack(Items.wheat_seeds, 2),
        						new ItemStack(Items.reeds, 2),
        						new ItemStack(Items.potato, 1),
        						new ItemStack(Items.carrot, 1),
        						new ItemStack(Items.wooden_pickaxe, 1),
        						new ItemStack(Blocks.sand, 8),
        						new ItemStack(JAFFMod.woodFishingRod, 2)};
            		}else if(stage.getIdStage() == 1)
            		{
            			stack = new ItemStack[] {
        						new ItemStack(Items.melon_seeds, 3),
        						new ItemStack(Items.coal, 5),
        						new ItemStack(ItemMod.tuteur, 6),
        						new ItemStack(ItemMod.Tomate_Seeds, 2),
        						new ItemStack(ItemMod.Corn_Seeds, 4),
        						new ItemStack(ItemMod.Laitue, 2)};
            		} else if(stage.getIdStage() == 2)
            		{
            			stack = new ItemStack[] {
        						new ItemStack(ItemMod.Mythrile_Pickaxe, 1),
        						new ItemStack(Blocks.brown_mushroom_block, 3),
        						new ItemStack(Blocks.red_mushroom_block, 2),
        						new ItemStack(ItemMod.Aubergine_Seed, 7),
        						new ItemStack(Items.coal, 14),
        						new ItemStack(Items.iron_ingot, 7)};
            		} else if(stage.getIdStage() == 3)
            		{
            			stack = new ItemStack[] {
            					new ItemStack(Items.nether_wart, 4),
        						new ItemStack(ItemMod.Radis_Seed, 6),
        						new ItemStack(BlockMod.Shopper, 8)};
            		} else
            		{
            			stack = new ItemStack[] {
        						new ItemStack(ItemMod.Copyright_NeoCraft, 1)
        				};
            		}
            		
            		for(ItemStack e:stack)
            		{
            			this.dropBlockAsItem(world, x, y, z, e);
            		}
            		
            	}
            }
            return false;
        }
    }
    
}