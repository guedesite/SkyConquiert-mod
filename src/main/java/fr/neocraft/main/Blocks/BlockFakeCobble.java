package fr.neocraft.main.Blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockFakeCobble extends Block{

	public BlockFakeCobble() {
		super(Material.rock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypePiston);
		this.setBlockName("Fake stonebrick");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockTextureName("cobblestone");
	}
	
	@Override
	public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }
	
        

}
