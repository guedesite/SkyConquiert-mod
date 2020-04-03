package fr.neocraft.main.Proxy.Client.Render.ScrollGui;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;

public abstract class GuiInterface 
	extends GuiScreen {
		  public EntityClientPlayerMP player;
		  public boolean drawDefaultBackground;
		  private HashMap<Integer, GuiButton> buttons;
		  private HashMap<Integer, GuiCustomScroll> scrolls;
		  public HashMap<Integer, GuiTextField> TextField;
		  
		  public GuiInterface() {
		    this.drawDefaultBackground = true;
		    
		    this.buttons = new HashMap();
		    this.scrolls = new HashMap();
		    this.TextField = new HashMap();
		    
		    this.background = null;
		    this.closeOnEsc = false;



		    
		    this.bgScale = 1.0F;


		    
		    this.player = (Minecraft.getMinecraft()).thePlayer;
		    this.title = "";
		    this.xSize = 200;
		    this.ySize = 222;
		  }
		  
		  public String title;
		  private ResourceLocation background; 
		  public boolean closeOnEsc; 
		  public int guiLeft; 
		  public int guiTop; 
		  public int xSize; 
		  public int ySize; 
		  public int mouseX; 
		  public int mouseY; 
		  public float bgScale;
		
		  
		  public void setBackground(String texture) { this.background = new ResourceLocation(neoreference.MOD_ID, "textures/gui/" + texture); }

		  
		  public ResourceLocation getResource(String texture) { return new ResourceLocation(neoreference.MOD_ID, "textures/gui/" + texture); }

		  
		  public void initGui() {
		    super.initGui();

		    this.guiLeft = (this.width - this.xSize) / 2;
		    this.guiTop = (this.height - this.ySize) / 2;
		    this.buttonList.clear();

		    this.TextField.clear();
		    this.buttons.clear();
		    this.scrolls.clear();
		    Keyboard.enableRepeatEvents(true);
		  }
		  
		  public void updateScreen() {
		
		      super.updateScreen();
		  }


		  
		  public void mouseClicked(int i, int j, int k) {

		    
		      mouseEvent(i, j, k);
		      super.mouseClicked(i, j, k);
		  }
		    
		  

		  
		  public void mouseEvent(int i, int j, int k) {}
		  
		  protected void actionPerformed(GuiButton guibutton) {
		
		      buttonEvent(guibutton);
		    
		  }
		  
		  public void buttonEvent(GuiButton guibutton) {}
		  
		  public void keyTyped(char c, int i) {
		
		  }
		  
		  public void onGuiClosed() {  }
		  
		  public void close() {
		    displayGuiScreen(null);
		    this.mc.setIngameFocus();
		    save();
		  }
		  public void addButton(GuiButton button) {
		    this.buttons.put(Integer.valueOf(button.id), button);
		    this.buttonList.add(button);
		  }


		  
		  public GuiButton getButton(int i) { return (GuiButton)this.buttons.get(Integer.valueOf(i)); }


	
		  public void addScroll(GuiCustomScroll scroll) {
		    scroll.setWorldAndResolution(this.mc, 350, 250);
		    this.scrolls.put(Integer.valueOf(scroll.id), scroll);
		  }
		  
		  public GuiCustomScroll getScroll(int id) { return (GuiCustomScroll)this.scrolls.get(Integer.valueOf(id)); }

		  
		  public abstract void save();

		  
		  public void drawScreen(int i, int j, float f) {
		
			  
			  
		    this.mouseX = i;
		    this.mouseY = j;
		    if (this.drawDefaultBackground) {
		      drawDefaultBackground();
		    }
		    if (this.background != null && this.mc.renderEngine != null) {
		      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		      GL11.glPushMatrix();
		      GL11.glTranslatef(this.guiLeft, this.guiTop, 0.0F);
		      GL11.glScalef(this.bgScale, this.bgScale, this.bgScale);
		      this.mc.renderEngine.bindTexture(this.background);
		      if (this.xSize > 256) {
		        drawTexturedModalRect(0, 0, 0, 0, 250, this.ySize);
		        drawTexturedModalRect(250, 0, 256 - this.xSize - 250, 0, this.xSize - 250, this.ySize);
		      } else {
		        
		        drawTexturedModalRect(0, 0, 0, 0, this.xSize, this.ySize);
		      }  GL11.glPopMatrix();
		    } 
		    
		    drawCenteredString(this.fontRendererObj, this.title, this.width / 2, this.guiTop- 15, 16777215);
		
		    for (Object scroll : this.scrolls.values())
		    {
		      ((GuiCustomScroll)scroll).drawScreen(i, j, f,Mouse.getDWheel()); 
		    }
		
		    
		    for (Object scroll : this.TextField.values())
		    {
		      ((GuiTextField)scroll).drawTextBox();
		    }
		    super.drawScreen(i, j, f);
		  
		  }
		  
		  public FontRenderer getFontRenderer() { return this.fontRendererObj; }

		  
		  public void elementClicked() {
		
		  }
		  
		  public boolean doesGuiPauseGame() { return false; }


		  
		  public void doubleClicked() {}

		  
		  public boolean isInventoryKey(int i) { return (i == this.mc.gameSettings.keyBindInventory.getKeyCode()); }


		  
		  public void drawDefaultBackground() { super.drawDefaultBackground(); }


		  
		  public void displayGuiScreen(GuiScreen gui) { this.mc.displayGuiScreen(gui); }

	}


