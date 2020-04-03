
package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.CarteContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.CarteSlot;
import fr.neocraft.main.Proxy.Serveur.TileEntity.CarteTileEntity;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CarteGui extends GuiContainer
{
    private static final ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/CarteBackground.png");
    private CarteTileEntity NeoTile;
    private IInventory playerInv;
    public CarteItemRender itemRender = new CarteItemRender();
    private boolean stat = false, perform = false;
    

    
    public CarteGui(CarteTileEntity tile, InventoryPlayer inventory, World w, EntityPlayer p)
    {
        super(new CarteContainer(tile, inventory, w, p));
        
        this.NeoTile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 256;
        this.xSize = 256;
    }

    @Override
    public void onGuiClosed()
    {
    	 SoundManager.PlaySound(EnumSound.NeoMClose.getSound());
        if (this.mc.thePlayer != null)
        {
            this.inventorySlots.onContainerClosed(this.mc.thePlayer);
        }
    }
    @Override
    public void initGui()
    {
     super.initGui();
     SoundManager.PlaySound(EnumSound.NeoMOpen.getSound());
    }
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	this.mc.thePlayer.closeScreen();
    
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {

    }

    private int debutx =0, debuty = 0;
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	this.mc.getTextureManager().bindTexture(textures);
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            GL11.glPushMatrix();
            float scale = 1.5F, scaleprop = 1F / 1.5F;
           GL11.glScalef(scale, scale, 1F);
           this.debutx = Math.round(k *(1f/scale)-47);
           this.debuty = Math.round( l *(1f/scale)-20);
            this.drawTexturedModalRect(this.debutx,this.debuty, 0, 0, this.xSize, this.ySize);
            GL11.glTranslatef(this.debutx, this.debuty, 0);
 
           
  
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
        {
            this.mc.thePlayer.closeScreen();
        }

    }

    private CarteSlot theSlot;
    /** Used when touchscreen is enabled. */
    private CarteSlot clickedSlot;
    /** Used when touchscreen is enabled. */
    private boolean isRightMouseClick;
    /** Used when touchscreen is enabled */
    private ItemStack draggedStack;
    private int field_147011_y;
    private int field_147010_z;
    private CarteSlot returningStackDestSlot;
    private long returningStackTime;
    /** Used when touchscreen is enabled */
    private ItemStack returningStack;
    private CarteSlot field_146985_D;
    private long field_146986_E;
    protected final Set field_147008_s = new HashSet();
    protected boolean field_147007_t;
    private int field_146987_F;
    private int field_146988_G;
    private boolean field_146995_H;
    private int field_146996_I;
    private long field_146997_J;
    private CarteSlot field_146998_K;
    private int field_146992_L;
    private boolean field_146993_M;
    private ItemStack field_146994_N;
   
    private boolean isMouseOverSlot(CarteSlot p_146981_1_, int p_146981_2_, int p_146981_3_)
    {
    	float scale = 1f/1.5F;
    	return this.func_146978_c(p_146981_1_.xDisplayPosition, p_146981_1_.yDisplayPosition, p_146981_1_.idStageWidth, p_146981_1_.idStageWidth,Math.round( p_146981_2_ * scale),Math.round( p_146981_3_* scale));
    }
    protected boolean func_146978_c(int p_146978_1_, int p_146978_2_, int p_146978_3_, int p_146978_4_, int p_146978_5_, int p_146978_6_)
    {
    	
        int k1 =(int) (this.debutx);
        int l1 =(int)(this.debuty);
        p_146978_5_ -= k1;
        p_146978_6_ -= l1;
        boolean t=  p_146978_5_ >= p_146978_1_ - 1 && p_146978_5_ < p_146978_1_ + p_146978_3_ + 1 && p_146978_6_ >= p_146978_2_ - 1 && p_146978_6_ < p_146978_2_ + p_146978_4_ + 1;
        return t;
    }
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {

        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawGuiContainerBackgroundLayer(p_73863_3_, p_73863_1_, p_73863_2_);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        int k2;

        for (k2 = 0; k2 < this.buttonList.size(); ++k2)
        {
            ((GuiButton)this.buttonList.get(k2)).drawButton(this.mc, p_73863_1_, p_73863_2_);
        }

        for (k2 = 0; k2 < this.labelList.size(); ++k2)
        {
            ((GuiLabel)this.labelList.get(k2)).func_146159_a(this.mc, p_73863_1_, p_73863_2_);
        }
        
        RenderHelper.enableGUIStandardItemLighting();
     
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.theSlot = null;
        short short1 = 240;
        short short2 = 240;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)short1 / 1.0F, (float)short2 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int k1;

        for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1)
        {
        	CarteSlot slot = (CarteSlot)this.inventorySlots.inventorySlots.get(i1);
            this.func_146977_a(slot);

            if (this.isMouseOverSlot(slot, p_73863_1_, p_73863_2_) && slot.func_111238_b())
            {
                this.theSlot = slot;
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                int j1 = slot.xDisplayPosition, facteur = Math.round(slot.idStageWidth/3);
                k1 = slot.yDisplayPosition;
                GL11.glColorMask(true, true, true, false);
              int wi = 2;
                // this.drawGradientRect(j1, k1, j1 + slot.idStageWidth, k1 + slot.idStageWidth, -2130706433, -2130706433);
                
                this.drawGradientRect(j1, k1, j1 + facteur, k1 + wi, Integer.MIN_VALUE, Integer.MIN_VALUE);
                this.drawGradientRect(j1, k1+wi, j1 + wi, k1 + facteur, Integer.MIN_VALUE, Integer.MIN_VALUE);
                
                this.drawGradientRect(j1 + slot.idStageWidth - facteur, k1, j1 + slot.idStageWidth-wi, k1 + wi, Integer.MIN_VALUE, Integer.MIN_VALUE);
                this.drawGradientRect(j1 + slot.idStageWidth - wi, k1, j1 + slot.idStageWidth, k1 + facteur, Integer.MIN_VALUE, Integer.MIN_VALUE);
                
                this.drawGradientRect(j1 + slot.idStageWidth - wi, k1+ slot.idStageWidth - facteur, j1 + slot.idStageWidth, k1 + slot.idStageWidth, Integer.MIN_VALUE, Integer.MIN_VALUE);
                this.drawGradientRect(j1 + slot.idStageWidth - facteur, k1 + slot.idStageWidth-wi, j1 + slot.idStageWidth-wi, k1 + slot.idStageWidth, Integer.MIN_VALUE, Integer.MIN_VALUE);
                
                this.drawGradientRect(j1, k1+ slot.idStageWidth - facteur, j1 + wi,  k1+ slot.idStageWidth, Integer.MIN_VALUE, Integer.MIN_VALUE);
                this.drawGradientRect(j1+wi, k1 + slot.idStageWidth - wi, j1 + facteur, k1+ slot.idStageWidth, Integer.MIN_VALUE, Integer.MIN_VALUE);
                
                
                GL11.glColorMask(true, true, true, true);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            }
        }
        
        //Forge: Force lighting to be disabled as there are some issue where lighting would
        //incorrectly be applied based on items that are in the inventory.
        InventoryPlayer inventoryplayer = this.mc.thePlayer.inventory;
        ItemStack itemstack = this.draggedStack == null ? inventoryplayer.getItemStack() : this.draggedStack;

        if (inventoryplayer.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack())
        {
            ItemStack itemstack1 = this.theSlot.getStack();
            this.renderToolTip(itemstack1, this.theSlot.xDisplayPosition, this.theSlot.yDisplayPosition, this.theSlot.idStageWidth);
        }

        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawGuiContainerForegroundLayer(p_73863_1_, p_73863_2_);
        GL11.glEnable(GL11.GL_LIGHTING);


        GL11.glPopMatrix();


        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
    }
    protected void renderToolTip(ItemStack p_146285_1_, int x, int y, int w)
    {
        List list = p_146285_1_.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

        for (int k = 0; k < list.size(); ++k)
        {
            if (k == 0)
            {
                list.set(k, p_146285_1_.getRarity().rarityColor + (String)list.get(k));
            }
            else
            {
                list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
            }
        }

        FontRenderer font = p_146285_1_.getItem().getFontRenderer(p_146285_1_);
        drawHoveringText(list, x, y,w, (font == null ? fontRendererObj : font));
    }

    protected void drawHoveringText(List p_146283_1_, int x, int y,int w, FontRenderer font)
    {
        if (!p_146283_1_.isEmpty())
        {
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glScalef(1F/1.5F, 1F/1.5F, 1F);
            int k = 0;

            Iterator iterator = p_146283_1_.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if (l > k)
                {
                    k = l;
                }
            }


            int i1 = 8;

            
            if (p_146283_1_.size() > 1)
            {
                i1 += 2 + (p_146283_1_.size() - 1) * 10;
            }
            
      
            
            int j2 =Math.round( ((x-2+(w/2)-(k/3)))*1.5F);
           int  k2 = Math.round((y*1.5F)-i1-5);
           
           if(k2   < 2)
           {
        	   k2 = Math.round((y+w+3)*1.5F);  
           }

            
            this.zLevel = 300.0F;
            itemRender.zLevel = 300.0F;
            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            for (int i2 = 0; i2 < p_146283_1_.size(); ++i2)
            {
                String s1 = (String)p_146283_1_.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);

                if (i2 == 0)
                {
                    k2 += 2;
                }

                k2 += 10;
            }

            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;
            GL11.glScalef(1.5F, 1.5F, 1F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
        }
    }
   

    private void func_146977_a(CarteSlot p_146977_1_)
    {
        int i = p_146977_1_.xDisplayPosition;
        int j = p_146977_1_.yDisplayPosition;
        ItemStack itemstack = p_146977_1_.getStack();
        boolean flag = false;
        boolean flag1 = p_146977_1_ == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
        ItemStack itemstack1 = this.mc.thePlayer.inventory.getItemStack();
        String s = null;

        if (p_146977_1_ == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && itemstack != null)
        {
            itemstack = itemstack.copy();
            itemstack.stackSize /= 2;
        }
        else if (this.field_147007_t && this.field_147008_s.contains(p_146977_1_) && itemstack1 != null)
        {
            if (this.field_147008_s.size() == 1)
            {
                return;
            }

            if (Container.func_94527_a(p_146977_1_, itemstack1, true) && this.inventorySlots.canDragIntoSlot(p_146977_1_))
            {
                itemstack = itemstack1.copy();
                flag = true;
                Container.func_94525_a(this.field_147008_s, this.field_146987_F, itemstack, p_146977_1_.getStack() == null ? 0 : p_146977_1_.getStack().stackSize);

                if (itemstack.stackSize > itemstack.getMaxStackSize())
                {
                    s = EnumChatFormatting.YELLOW + "" + itemstack.getMaxStackSize();
                    itemstack.stackSize = itemstack.getMaxStackSize();
                }

                if (itemstack.stackSize > p_146977_1_.getSlotStackLimit())
                {
                    s = EnumChatFormatting.YELLOW + "" + p_146977_1_.getSlotStackLimit();
                    itemstack.stackSize = p_146977_1_.getSlotStackLimit();
                }
            }
            else
            {
                this.field_147008_s.remove(p_146977_1_);
                this.func_146980_g();
            }
        }

        this.zLevel = 100.0F;
        itemRender.zLevel = 100.0F;

   
        if (!flag1)
        {
           

            GL11.glEnable(GL11.GL_DEPTH_TEST);

           
            itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack,i, j, p_146977_1_.idStageWidth);
        
        }

        itemRender.zLevel = 0.0F;
        this.zLevel = 0.0F;
    }
    private void func_146980_g()
    {
        ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();

        if (itemstack != null && this.field_147007_t)
        {
            this.field_146996_I = itemstack.stackSize;
            ItemStack itemstack1;
            int i;

            for (Iterator iterator = this.field_147008_s.iterator(); iterator.hasNext(); this.field_146996_I -= itemstack1.stackSize - i)
            {
            	CarteSlot slot = (CarteSlot)iterator.next();
                itemstack1 = itemstack.copy();
                i = slot.getStack() == null ? 0 : slot.getStack().stackSize;
                Container.func_94525_a(this.field_147008_s, this.field_146987_F, itemstack1, i);

                if (itemstack1.stackSize > itemstack1.getMaxStackSize())
                {
                    itemstack1.stackSize = itemstack1.getMaxStackSize();
                }

                if (itemstack1.stackSize > slot.getSlotStackLimit())
                {
                    itemstack1.stackSize = slot.getSlotStackLimit();
                }
            }
        }
    }

    /**
     * Returns the slot at the given coordinates or null if there is none.
     */
    private CarteSlot getSlotAtPosition(int p_146975_1_, int p_146975_2_)
    {
        for (int k = 0; k < this.inventorySlots.inventorySlots.size(); ++k)
        {
        	CarteSlot slot = (CarteSlot)this.inventorySlots.inventorySlots.get(k);

            if (this.isMouseOverSlot(slot, p_146975_1_, p_146975_2_))
            {
                return slot;
            }
        }

        return null;
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        boolean flag = p_73864_3_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100;
        CarteSlot slot = this.getSlotAtPosition(p_73864_1_, p_73864_2_);
        long l = Minecraft.getSystemTime();
        this.field_146993_M = this.field_146998_K == slot && l - this.field_146997_J < 250L && this.field_146992_L == p_73864_3_;
        this.field_146995_H = false;

        if (p_73864_3_ == 0 || p_73864_3_ == 1 || flag)
        {
            int i1 = this.guiLeft;
            int j1 = this.guiTop;
            boolean flag1 = p_73864_1_ < i1 || p_73864_2_ < j1 || p_73864_1_ >= i1 + this.xSize || p_73864_2_ >= j1 + this.ySize;
            int k1 = -1;

            if (slot != null)
            {
                k1 = slot.slotNumber;
            }

            if (flag1)
            {
                k1 = -999;
            }

            if (this.mc.gameSettings.touchscreen && flag1 && this.mc.thePlayer.inventory.getItemStack() == null)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
                return;
            }

            if (k1 != -1)
            {
                if (this.mc.gameSettings.touchscreen)
                {
                    if (slot != null && slot.getHasStack())
                    {
                        this.clickedSlot = slot;
                        this.draggedStack = null;
                        this.isRightMouseClick = p_73864_3_ == 1;
                    }
                    else
                    {
                        this.clickedSlot = null;
                    }
                }
                else if (!this.field_147007_t)
                {
                    if (this.mc.thePlayer.inventory.getItemStack() == null)
                    {
                        if (p_73864_3_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100)
                        {
                            this.handleMouseClick(slot, k1, p_73864_3_, 3);
                        }
                        else
                        {
                            boolean flag2 = k1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
                            byte b0 = 0;

                            if (flag2)
                            {
                                this.field_146994_N = slot != null && slot.getHasStack() ? slot.getStack() : null;
                                b0 = 1;
                            }
                            else if (k1 == -999)
                            {
                                b0 = 4;
                            }

                            this.handleMouseClick(slot, k1, p_73864_3_, b0);
                        }

                        this.field_146995_H = true;
                    }
                    else
                    {
                        this.field_147007_t = true;
                        this.field_146988_G = p_73864_3_;
                        this.field_147008_s.clear();

                        if (p_73864_3_ == 0)
                        {
                            this.field_146987_F = 0;
                        }
                        else if (p_73864_3_ == 1)
                        {
                            this.field_146987_F = 1;
                        }
                    }
                }
            }
        }

        this.field_146998_K = slot;
        this.field_146997_J = l;
        this.field_146992_L = p_73864_3_;
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    protected void mouseClickMove(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_)
    {
    	CarteSlot slot = this.getSlotAtPosition(p_146273_1_, p_146273_2_);
        ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();

        if (this.clickedSlot != null && this.mc.gameSettings.touchscreen)
        {
            if (p_146273_3_ == 0 || p_146273_3_ == 1)
            {
                if (this.draggedStack == null)
                {
                    if (slot != this.clickedSlot)
                    {
                        this.draggedStack = this.clickedSlot.getStack().copy();
                    }
                }
                else if (this.draggedStack.stackSize > 1 && slot != null && Container.func_94527_a(slot, this.draggedStack, false))
                {
                    long i1 = Minecraft.getSystemTime();

                    if (this.field_146985_D == slot)
                    {
                        if (i1 - this.field_146986_E > 500L)
                        {
                            this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                            this.handleMouseClick(slot, slot.slotNumber, 1, 0);
                            this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                            this.field_146986_E = i1 + 750L;
                            --this.draggedStack.stackSize;
                        }
                    }
                    else
                    {
                        this.field_146985_D = slot;
                        this.field_146986_E = i1;
                    }
                }
            }
        }
        else if (this.field_147007_t && slot != null && itemstack != null && itemstack.stackSize > this.field_147008_s.size() && Container.func_94527_a(slot, itemstack, true) && slot.isItemValid(itemstack) && this.inventorySlots.canDragIntoSlot(slot))
        {
            this.field_147008_s.add(slot);
            this.func_146980_g();
        }
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_)
    {
        super.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_); //Forge, Call parent to release buttons
        CarteSlot slot = this.getSlotAtPosition(p_146286_1_, p_146286_2_);
        int l = this.guiLeft;
        int i1 = this.guiTop;
        boolean flag = p_146286_1_ < l || p_146286_2_ < i1 || p_146286_1_ >= l + this.xSize || p_146286_2_ >= i1 + this.ySize;
        int j1 = -1;

        if (slot != null)
        {
            j1 = slot.slotNumber;
        }

        if (flag)
        {
            j1 = -999;
        }

        CarteSlot slot1;
        Iterator iterator;

        if (this.field_146993_M && slot != null && p_146286_3_ == 0 && this.inventorySlots.func_94530_a((ItemStack)null, slot))
        {
            if (isShiftKeyDown())
            {
                if (slot != null && slot.inventory != null && this.field_146994_N != null)
                {
                    iterator = this.inventorySlots.inventorySlots.iterator();

                    while (iterator.hasNext())
                    {
                        slot1 = (CarteSlot)iterator.next();

                        if (slot1 != null && slot1.canTakeStack(this.mc.thePlayer) && slot1.getHasStack() && slot1.inventory == slot.inventory && Container.func_94527_a(slot1, this.field_146994_N, true))
                        {
                            this.handleMouseClick(slot1, slot1.slotNumber, p_146286_3_, 1);
                        }
                    }
                }
            }
            else
            {
                this.handleMouseClick(slot, j1, p_146286_3_, 6);
            }

            this.field_146993_M = false;
            this.field_146997_J = 0L;
        }
        else
        {
            if (this.field_147007_t && this.field_146988_G != p_146286_3_)
            {
                this.field_147007_t = false;
                this.field_147008_s.clear();
                this.field_146995_H = true;
                return;
            }

            if (this.field_146995_H)
            {
                this.field_146995_H = false;
                return;
            }

            boolean flag1;

            if (this.clickedSlot != null && this.mc.gameSettings.touchscreen)
            {
                if (p_146286_3_ == 0 || p_146286_3_ == 1)
                {
                    if (this.draggedStack == null && slot != this.clickedSlot)
                    {
                        this.draggedStack = this.clickedSlot.getStack();
                    }

                    flag1 = Container.func_94527_a(slot, this.draggedStack, false);

                    if (j1 != -1 && this.draggedStack != null && flag1)
                    {
                        this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, p_146286_3_, 0);
                        this.handleMouseClick(slot, j1, 0, 0);

                        if (this.mc.thePlayer.inventory.getItemStack() != null)
                        {
                            this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, p_146286_3_, 0);
                            this.field_147011_y = p_146286_1_ - l;
                            this.field_147010_z = p_146286_2_ - i1;
                            this.returningStackDestSlot = this.clickedSlot;
                            this.returningStack = this.draggedStack;
                            this.returningStackTime = Minecraft.getSystemTime();
                        }
                        else
                        {
                            this.returningStack = null;
                        }
                    }
                    else if (this.draggedStack != null)
                    {
                        this.field_147011_y = p_146286_1_ - l;
                        this.field_147010_z = p_146286_2_ - i1;
                        this.returningStackDestSlot = this.clickedSlot;
                        this.returningStack = this.draggedStack;
                        this.returningStackTime = Minecraft.getSystemTime();
                    }

                    this.draggedStack = null;
                    this.clickedSlot = null;
                }
            }
            else if (this.field_147007_t && !this.field_147008_s.isEmpty())
            {
                this.handleMouseClick((CarteSlot)null, -999, Container.func_94534_d(0, this.field_146987_F), 5);
                iterator = this.field_147008_s.iterator();

                while (iterator.hasNext())
                {
                    slot1 = (CarteSlot)iterator.next();
                    this.handleMouseClick(slot1, slot1.slotNumber, Container.func_94534_d(1, this.field_146987_F), 5);
                }

                this.handleMouseClick((CarteSlot)null, -999, Container.func_94534_d(2, this.field_146987_F), 5);
            }
            else if (this.mc.thePlayer.inventory.getItemStack() != null)
            {
                if (p_146286_3_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100)
                {
                    this.handleMouseClick(slot, j1, p_146286_3_, 3);
                }
                else
                {
                    flag1 = j1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));

                    if (flag1)
                    {
                        this.field_146994_N = slot != null && slot.getHasStack() ? slot.getStack() : null;
                    }

                    this.handleMouseClick(slot, j1, p_146286_3_, flag1 ? 1 : 0);
                }
            }
        }

        if (this.mc.thePlayer.inventory.getItemStack() == null)
        {
            this.field_146997_J = 0L;
        }

        this.field_147007_t = false;
    }

 
   
}