package fr.neocraft.main.Proxy.Serveur.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerCodeFuturistInput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class FuturistInputTileEntity extends TileEntity{

	public String code = "";
	
	@Override
    public void readFromNBT(NBTTagCompound c)
    {
        super.readFromNBT(c); // exécute ce qui se trouve dans la fonction readFromNBT de la classe mère (lecture de la position du tile entity)
        this.code = c.getString("code");
    }

    @Override
    public void writeToNBT(NBTTagCompound c)
    {
        super.writeToNBT(c); // exécute se qui se trouve dans la fonction writeToNBT de la classe mère (écriture de la position du tile entity)
      
      c.setString("code", this.code);
    }
    

    
}
