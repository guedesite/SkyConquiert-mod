package fr.neocraft.main.Blocks;

import com.tmtravlr.jaff.JAFFMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockNoelPresent extends Block
{
	@SideOnly(Side.CLIENT)
    private IIcon top;
    
	public BlockNoelPresent(Material p_i45394_1_)
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
           int r = world.rand.nextInt(11);
           world.setBlock(x, y, z, Blocks.air);
   			ItemStack[] stack = null;
           if(r == 1)
           {
        	   stack = new ItemStack[] {
						new ItemStack(Item.getItemById(4600), 1),
						new ItemStack(Item.getItemById( 4124), 4),
						new ItemStack(Item.getItemById(4153), 1),
						new ItemStack(Item.getItemById(399), 1),
						new ItemStack(Item.getItemById(4603),1000000, 1),
						new ItemStack(Item.getItemById(4596),72, 1),
						new ItemStack(Item.getItemById(354), 1),
						new ItemStack(Item.getItemById(340), 32),
						
						new ItemStack(Item.getItemById(352), 64),
						new ItemStack(Item.getItemById(352), 64),
						new ItemStack(Item.getItemById(369), 64),
						new ItemStack(Item.getItemById(369), 64),
						new ItemStack(Item.getItemById(349), 64),
						new ItemStack(Item.getItemById(349), 64),
						new ItemStack(Item.getItemById(265), 64),
						new ItemStack(Item.getItemById(265), 64),
	
				};
        
           } else if(r == 2 | r == 3 |r == 4){
        	   stack = new ItemStack[] {
						new ItemStack(Item.getItemById(403), 1),
						new ItemStack(Item.getItemById(4107), 3),
						new ItemStack(Item.getItemById(594),6, 5),
						new ItemStack(Item.getItemById(4112), 1),
						new ItemStack(Item.getItemById(4603),500000, 1),
						new ItemStack(Item.getItemById(4596),24, 1),
						new ItemStack(Item.getItemById(354), 1),
						new ItemStack(Item.getItemById(138), 3),
						
						new ItemStack(Item.getItemById(352), 64),
						new ItemStack(Item.getItemById(352), 64),
						new ItemStack(Item.getItemById(369), 64),
						new ItemStack(Item.getItemById(369), 64),
						new ItemStack(Item.getItemById(349), 64),
						new ItemStack(Item.getItemById(349), 64),
						new ItemStack(Item.getItemById(265), 64),
						new ItemStack(Item.getItemById(265), 64),
	
				};
           } else {
        	   
        	   stack = new ItemStack[] {
						new ItemStack(Item.getItemById(264), 64),
						new ItemStack(Item.getItemById(12), 64),
						new ItemStack(Item.getItemById(594), 3),
						new ItemStack(Item.getItemById(322), 1, 6),
						new ItemStack(Item.getItemById(4603),100000, 1),
						new ItemStack(Item.getItemById(4596),12, 1),
						new ItemStack(Item.getItemById(354), 1),
						new ItemStack(Item.getItemById(340), 32),
						
						new ItemStack(Item.getItemById(352), 64),
						new ItemStack(Item.getItemById(352), 64),
						new ItemStack(Item.getItemById(369), 64),
						new ItemStack(Item.getItemById(369), 64),
						new ItemStack(Item.getItemById(349), 64),
						new ItemStack(Item.getItemById(349), 64),
						new ItemStack(Item.getItemById(265), 64),
						new ItemStack(Item.getItemById(265), 64),
	
				};
        	   
        	
           }
           for(ItemStack e:stack)
   		{
   			this.dropBlockAsItem(world, x, y, z, e);
   		}
            return true;
        }
    }
    
}