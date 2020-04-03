package fr.neocraft.main.Proxy.Client.Render.SimpleGui.btn;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class NeoButtonCloseExtended extends NeoGuiButton
{
    protected static final ResourceLocation buttonTextures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/widgetWelcome.png");
    /** Button width in pixels */
    public int width;
    /** Button height in pixels */
    public int height;
    /** The x position of this control. */
    public int xPosition;
    /** The y position of this control. */
    public int yPosition;
    /** The string displayed on this control. */
    public String displayString;
    public int id;
    /** True if this control is enabled, false to disable. */
    public boolean enabled;
    /** Hides the button completely if false. */
    public boolean visible;
    protected boolean field_146123_n;
    private static final String __OBFID = "CL_00000668";
    public int packedFGColour;

    public NeoButtonCloseExtended(int p_i1020_1_, int p_i1020_2_, int p_i1020_3_, String p_i1020_4_)
    {
        this(p_i1020_1_, p_i1020_2_, p_i1020_3_, 200, 20, p_i1020_4_);
    }

    public NeoButtonCloseExtended(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_)
    {
    	super(p_i1021_1_, p_i1021_2_,  p_i1021_3_, p_i1021_4_,  p_i1021_5_, p_i1021_6_);
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.visible = true;
        this.id = p_i1021_1_;
        this.xPosition = p_i1021_2_;
        this.yPosition = p_i1021_3_;
        this.width = p_i1021_4_;
        this.height = p_i1021_5_;
        this.displayString = p_i1021_6_;
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    public int getHoverState(boolean p_146114_1_)
    {
        byte b0 = 1;

        if (!this.enabled)
        {
            b0 = 0;
        }
        else if (p_146114_1_)
        {
            b0 = 2;
        }

        return b0;
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_, float scale, int x, int y)
    {
        if (this.visible)
        {
            FontRenderer fontrenderer = p_146112_1_.fontRenderer;
            p_146112_1_.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
          	int kx = Math.round((x+this.xPosition/4)*scale);
        	int ky = Math.round((y+this.yPosition/4)*scale);
            	this.field_146123_n = p_146112_2_ >= kx && p_146112_3_ >= ky && p_146112_2_ < kx + (this.width/4*scale) && p_146112_3_ < ky + (this.height/4*scale);   
               
            int k = this.getHoverState(this.field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glScalef(1F/4F, 1F/4F, 0F);
            if(k == 1)
            {
            	this.drawTexturedModalRect(this.xPosition, this.yPosition,0,167 , this.width, this.height);
            }
            else {
            	this.drawTexturedModalRect(this.xPosition, this.yPosition,39,167 , this.width, this.height);
            }
            GL11.glScalef(4F, 4F, 0F);
            this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
         }
    }


    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_) {}

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int p_146118_1_, int p_146118_2_) {}

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft p_146116_1_, int p_146112_2_, int p_146112_3_, float scale, int x, int y)
    {
    	int kx = Math.round((x+this.xPosition/4)*scale);
    	int ky = Math.round((y+this.yPosition/4)*scale);
    	return this.enabled && this.visible && p_146112_2_ >= kx && p_146112_3_ >= ky && p_146112_2_ < kx + (this.width/4*scale) && p_146112_3_ < ky + (this.height/4*scale);   

    }

    public boolean func_146115_a()
    {
        return this.field_146123_n;
    }

    public void func_146111_b(int p_146111_1_, int p_146111_2_) {}

    public void func_146113_a(SoundHandler p_146113_1_)
    {
        
    }

    public int getButtonWidth()
    {
        return this.width;
    }

    public int func_154310_c()
    {
        return this.height;
    }
}
