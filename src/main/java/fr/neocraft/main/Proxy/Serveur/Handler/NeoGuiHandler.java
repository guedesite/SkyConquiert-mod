package fr.neocraft.main.Proxy.Serveur.Handler;


import cpw.mods.fml.common.network.IGuiHandler;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.BoxUltimeGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.BoxVoteGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.BuilderGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.CarteGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.DragonQuestGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.FuturistInputGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.PermissionQuestGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.PlanteurGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.ShopperGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.ShopperPnjGui;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.hdvGuiPerso;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.hdvGuiPublic;
import fr.neocraft.main.Proxy.Serveur.Stage.Shopper.ShopperData;
import fr.neocraft.main.Proxy.Serveur.Stage.Shopper.ShopperRegister;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxVoteContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxVoteTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BuilderContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BuilderTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.CarteContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.CarteTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.DragonQuestContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.FuturistInputTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.NeoDragonTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.NeoPermissionTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.PermissionQuestContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.PlanterTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.PlanteurContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperPnjContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperPnjTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvContainerPerso;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvContainerPublic;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvTileEntityPerso;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvTileEntityPublic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class NeoGuiHandler implements IGuiHandler

{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(ID == 0)
        {
        	return new DragonQuestContainer(new NeoDragonTileEntity(player, world.isRemote, world, x, y, z,0), player.inventory, world, player,0);
        }
        else if(ID == 1)
        {
        	return new DragonQuestContainer(new NeoDragonTileEntity(player, world.isRemote, world, x, y, z,1), player.inventory, world, player,1);
        }
        else if(ID == 2)
        {
        	return new PermissionQuestContainer(new NeoPermissionTileEntity(player, world.isRemote, world, x, y, z, 0), player.inventory, world, player, 0);
        }
        else if(ID == 3)
        {
        	return new PermissionQuestContainer(new NeoPermissionTileEntity(player, world.isRemote, world, x, y, z, 1), player.inventory, world, player, 1);
        }
        else if(ID == 4 & tile instanceof ShopperTileEntity)
        {
        	return new ShopperContainer((ShopperTileEntity)tile, player.inventory, world, player);
        }else if(ID == 5)
        {
        	return new BoxVoteContainer(new BoxVoteTileEntity(player, world.isRemote, world), player.inventory, world, player);
        }else if(ID == 6)
	    {
        	ShopperData Entity = ShopperRegister.getEntityShopperByPos(world.provider.dimensionId, x, y, z);
        	if(Entity != null)
        	{
        		return new ShopperPnjContainer(new ShopperPnjTileEntity(player, world.isRemote, world, Entity ,x,y,z), player.inventory, world, player);
        	}
        	else
        	{
        		System.out.println("nulll");
        	}
        }else if(ID == 7)
	    {
        	return new CarteContainer(new CarteTileEntity(player, world.isRemote, world), player.inventory, world, player);
	    }else if(ID == 8)
        {
        	return new BoxUltimeContainer(new BoxUltimeTileEntity(player, world.isRemote, world), player.inventory, world, player);
        }else if(ID == 9)
        {
        	return new hdvContainerPerso(new hdvTileEntityPerso(player, world.isRemote, world), player.inventory, world, player);
        }else if(ID == 10)
        {
        	return new hdvContainerPublic(new hdvTileEntityPublic(player, world.isRemote, world, 0), player.inventory, world, player);
        }else if(ID == 11)
        {
        	return new hdvContainerPublic(new hdvTileEntityPublic(player, world.isRemote, world, 1), player.inventory, world, player);
        } else if(ID == 12)
        {
        	return new PermissionQuestContainer(new NeoPermissionTileEntity(player, world.isRemote, world, x, y, z, 2), player.inventory, world, player, 2);
        }else if(ID == 13)
        {
            return new PlanteurContainer((PlanterTileEntity)tile, player.inventory);
        }
        else if(ID == 14)
        {
            return new BuilderContainer((BuilderTileEntity)tile, player.inventory);
        } else if(ID == 15) {
        	
        }
        return null;

    }

 

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)

    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(ID == 0)
        {
        	return new DragonQuestGui(new NeoDragonTileEntity(player, world.isRemote, world, x, y, z,0), player.inventory, world, player,0);
        }
        else if(ID == 1)
        {
        	return new DragonQuestGui(new NeoDragonTileEntity(player, world.isRemote, world, x, y, z,1), player.inventory, world, player,1);
        }
        else if(ID == 2)
        {
        	return new PermissionQuestGui(new NeoPermissionTileEntity(player, world.isRemote, world, x, y, z, 0), player.inventory, world, player, 0);
        }
        else if(ID == 3)
        {
        	return new PermissionQuestGui(new NeoPermissionTileEntity(player, world.isRemote, world, x, y, z, 1), player.inventory, world, player, 1);
        }
        else if(ID == 4 & tile instanceof ShopperTileEntity)
        {
        	return new ShopperGui((ShopperTileEntity)tile, player.inventory, world, player);
        }
        else if(ID == 5)
        {
        	return new BoxVoteGui(new BoxVoteTileEntity(player, world.isRemote, world), player.inventory, world, player);
        }
        else if(ID == 6)
        {
        	return new ShopperPnjGui(new ShopperPnjTileEntity(player, world.isRemote, world), player.inventory, world, player);
        }else if(ID == 7)
	    {
        	return new CarteGui(new CarteTileEntity(player, world.isRemote, world), player.inventory, world, player);
	    } else if(ID == 8)
        {
        	return new BoxUltimeGui(new BoxUltimeTileEntity(player, world.isRemote, world), player.inventory, world, player);
        }else if(ID == 9)
        {
        	return new hdvGuiPerso(new hdvTileEntityPerso(player, world.isRemote, world), player.inventory, world, player);
        }else if(ID == 10)
        {
        	return new hdvGuiPublic(new hdvTileEntityPublic(player, world.isRemote, world, 0), player.inventory, world, player);
        }else if(ID == 11)
        {
        	return new hdvGuiPublic(new hdvTileEntityPublic(player, world.isRemote, world, 1), player.inventory, world, player);
        } else if(ID == 12)
        {
        	return new PermissionQuestGui(new NeoPermissionTileEntity(player, world.isRemote, world, x, y, z, 2), player.inventory, world, player, 2);
        } else if(ID == 12)
        {
        	return new PermissionQuestGui(new NeoPermissionTileEntity(player, world.isRemote, world, x, y, z, 2), player.inventory, world, player, 2);
        } else if(ID == 13)
        {
        	return new PlanteurGui((PlanterTileEntity)tile, player.inventory);
        } else if(ID == 14)
        {
        	return new BuilderGui((BuilderTileEntity)tile, player.inventory);
        } else if(ID == 15) {
        	return new FuturistInputGui(((FuturistInputTileEntity)tile), x, y, z, world.provider.dimensionId);
        }
       
        return null;

    }

}
