package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.NeoTextField;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperTileEntity;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerShopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ShopperGui extends GuiContainer
{
    private static final ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/shopper/background.png");
    private ShopperTileEntity NeoTile;
    private NeoTextField commandTextField;
    private String OldText = "";
    private IInventory playerInv;
    private int tim = 0;
    
    public ShopperGui(ShopperTileEntity tile, InventoryPlayer inventory, World w, EntityPlayer p)
    {
        super(new ShopperContainer(tile, inventory, w, p));
        this.NeoTile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 172;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	this.commandTextField.drawTextBox();
    	String tileName = "Shopper";
         this.fontRendererObj.drawString(tileName, (this.xSize - this.fontRendererObj.getStringWidth(tileName)) / 2, 4, 0);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise
        this.mc.getTextureManager().bindTexture(textures); // affiche la texture
        int k = (this.width - this.xSize) / 2; // on calcul la coordonn�e x du point en haut � gauche
        int l = (this.height - this.ySize) / 2; // on calcul la coordonn�e y du point en haut � gauche
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize); // on desine la texture, la fonction � pour argument point x de d�part, point y de d�part, vecteur u, vecteur v, largeur, hauteur)
    }
    @Override
    public void initGui()
    {
    	super.initGui();
    	int k = (this.width - this.xSize) / 2; // on calcul la coordonn�e x du point en haut � gauche
        int l = (this.height - this.ySize) / 2;
    	Keyboard.enableRepeatEvents(true);
    	 this.commandTextField = new NeoTextField(this.fontRendererObj, 7, 42, 161, 17);
         this.commandTextField.setMaxStringLength(6);
         this.commandTextField.setFocused(true);
         this.commandTextField.setText(NeoTile.getValue()+"");
         this.OldText = NeoTile.getValue()+"";
         this.buttonList.add(new GuiButton( 1, k + 57, l+ 67, 62, 20, "Valider"));
    }
    
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	this.mc.thePlayer.closeScreen();
    	commandTextField.getText();
    	int nb;
        if(this.commandTextField.getText() == "" || this.commandTextField.getText() == " " || this.commandTextField.getText() == null)
        {
        	nb = 1;
        }
        else
        {	try {
        		nb = Integer.parseInt(commandTextField.getText());
        	} catch(NumberFormatException e)
        	{
        		nb = 1;
        	}
        }
        this.NeoTile.setValue(nb);
        main.getNetWorkClient().sendToServer(new NetWorkClient(new ServerShopper(nb, this.NeoTile.xCoord, this.NeoTile.yCoord, this.NeoTile.zCoord, Minecraft.getMinecraft().theWorld.provider.dimensionId)));

    	
    	
    }
    @Override
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.commandTextField.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }
    @Override
    protected void keyTyped(char p_73869_1_, int p)
    {
        if(this.commandTextField.isFocused())
        {
        	 this.commandTextField.textboxKeyTyped(p_73869_1_, p);
        }
    }
    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        this.commandTextField.updateCursorCounter();
        if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
        {
            this.mc.thePlayer.closeScreen();
        }
        
    }
    
    
}