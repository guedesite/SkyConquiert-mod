package fr.neocraft.main.Proxy.Client.Render.ScrollGui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiWarpScroll extends GuiCustomScroll{
  public static final ResourceLocation resource = new ResourceLocation(neoreference.MOD_ID, "textures/gui/misc.png");


	  
	  public int guiLeft = 0;
	  public int guiTop = 0;


	  
	  private boolean multipleSelection = false;


	  
	  private boolean isSorted = true;


	  
	  public boolean visible = true;


	  
	  private boolean selectable = true;


	  
	  private int xSize = 176;
	  private int ySize = 159;
	  public int selected = 0;
	  private int hover = -1;
	  private HashSet<String> selectedList = new HashSet();
	  private int listHeight = 0;
	  private int scrollY = 0;
	  private int scrollHeight = 0; 
	  private boolean isScrolling = false; 
	  private List<String> list;
	  
	 
	  public int id; private int maxScrollY; 
	  private ICustomScrollListener listener;
	  
	  public GuiWarpScroll(GuiScreen parent, int id, boolean multipleSelection) {
	    super(parent, id, multipleSelection);
	    
	  }
	  public GuiWarpScroll(GuiScreen parent, int id) {
		  super(parent, id);
	  }
	  
	  private HashMap<String, String> map = new HashMap<String, String>();
	  
	  public void setSize(int x, int y) {
		  this.map.put("Warp Dragon", "dragon");
			this.map.put("Warp Nether", "nether");
			this.map.put("Warp Portail", "portail");
			this.map.put("Warp Shop", "shop");
			this.map.put(I18n.format("neo.spec.d"), "dieu");
			this.map.put(I18n.format("neo.spec.r"), "roi");
			this.map.put("Warp Staff", "staff");
			this.map.put("Warp Ultime Box", "ultime");
			this.map.put("Warp Vote Box", "vote");
			this.map.put("Warp Minage", "minage");
	    this.ySize = y;
	    this.xSize = x;
	    this.listHeight = 14 * this.list.size();
	    
	    if (this.listHeight > 0) {
	      this.scrollHeight = (int)((this.ySize - 8) / this.listHeight * (this.ySize - 8));
	    } else {
	      this.scrollHeight = Integer.MAX_VALUE;
	    } 
	    this.maxScrollY = this.listHeight - this.ySize - 8 - 1;
	  }
	  
	  public void drawScreen(int i, int j, float f, int mouseScrolled) {
	    if (!this.visible)
	    {
	      return; 
	    }
	    
	    if(getSelected() == null)
	    {
	    	drawGradientRect(this.guiLeft, this.guiTop, this.xSize + this.guiLeft, this.ySize + this.guiTop, -1072689136, -804253680);
	    } else {
	    	
	    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    	GL11.glPushMatrix();

	    	this.mc.renderEngine.bindTexture(getWarpBack());
	    	
	    	 drawTexturedModalRect(this.guiLeft, this.guiTop,0,0, this.xSize, this.ySize);
	    	 GL11.glPopMatrix();
	    }
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.mc.renderEngine.bindTexture(resource);
	    
	    if (this.scrollHeight < this.ySize - 8) {
	      drawScrollBar();
	    }
	    GL11.glPushMatrix();
	    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glTranslatef(this.guiLeft, this.guiTop, 0.0F);
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    
	    if (this.selectable) {
	      this.hover = getMouseOver(i, j);
	    }
	    drawItems();
	    
	    GL11.glPopMatrix();
	    if (this.scrollHeight < this.ySize - 8) {
	      i -= this.guiLeft;
	      j -= this.guiTop;
	      if (Mouse.isButtonDown(0)) {
	        if (i >= this.xSize - 11 && i < this.xSize - 6 && j >= 4 && j < this.ySize) {
	          this.isScrolling = true;
	        }
	      } else {
	        
	        this.isScrolling = false;
	      } 
	      if (this.isScrolling) {
	        this.scrollY = (j - 8) * this.listHeight / (this.ySize - 8) - this.scrollHeight;
	        if (this.scrollY < 0) {
	          this.scrollY = 0;
	        }
	        if (this.scrollY > this.maxScrollY) {
	          this.scrollY = this.maxScrollY;
	        }
	      } 
	      
	      if (mouseScrolled != 0) {
	        this.scrollY += ((mouseScrolled > 0) ? -14 : 14);
	        if (this.scrollY > this.maxScrollY)
	          this.scrollY = this.maxScrollY; 
	        if (this.scrollY < 0) {
	          this.scrollY = 0;
	        }
	      } 
	    } 
	  }
	  
	  HashMap<String,ResourceLocation> cache = new HashMap<String,ResourceLocation>();
	  
	private ResourceLocation getWarpBack() {
		  if(!cache.containsKey(this.getSelected()))
		  {
			  cache.put(this.getSelected(), new ResourceLocation(neoreference.MOD_ID, "textures/gui/stage/warps/"+map.get(this.getSelected())+".png"));
		  }
		return cache.get(this.getSelected());
	}
	public boolean mouseInOption(int i, int j, int k) {
	    int l = 4;
	    int i1 = 14 * k + 4 - this.scrollY;
	    return (i >= l - 1 && i < l + this.xSize - 11 && j >= i1 - 1 && j < i1 + 8);
	  }

	  
	  protected void drawItems() {
	    for (int i = 0; i < this.list.size(); i++) {
	      
	      int j = 4;
	      int k = 14 * i + 4 - this.scrollY;
	      if (k >= 4 && k + 12 < this.ySize) {
	        
	        int xOffset = (this.scrollHeight < this.ySize - 8) ? 0 : 10;
	        String displayString = StatCollector.translateToLocal((String)this.list.get(i));
	        
	        String text = "";
	        float maxWidth = (this.xSize + xOffset - 8) * 0.8F;
	        if (this.fontRendererObj.getStringWidth(displayString) > maxWidth) {
	          for (int h = 0; h < displayString.length(); h++) {
	            char c = displayString.charAt(h);
	            text = text + c;
	            if (this.fontRendererObj.getStringWidth(text) > maxWidth)
	              break; 
	          } 
	          if (displayString.length() > text.length()) {
	            text = text + "...";
	          }
	        } else {
	          text = displayString;
	        }  if ((this.multipleSelection && this.selectedList.contains(text)) || (!this.multipleSelection && this.selected == i)) {
	          drawVerticalLine(j - 2, k - 4, k + 10, -1);
	          drawVerticalLine(j + this.xSize - 18 + xOffset, k - 4, k + 10, -1);
	          drawHorizontalLine(j - 2, j + this.xSize - 18 + xOffset, k - 3, -1);
	          drawHorizontalLine(j - 2, j + this.xSize - 18 + xOffset, k + 10, -1);
	          this.fontRendererObj.drawString(text, j, k, 16777215);
	        }
	        else if (i == this.hover) {
	          this.fontRendererObj.drawString(text, j, k, 65280);
	        } else {
	          this.fontRendererObj.drawString(text, j, k, 16777215);
	        } 
	      } 
	    } 
	  }
	  public String getSelected() {
	    if (this.selected == -1 || this.selected >= this.list.size())
	      return null; 
	    return (String)this.list.get(this.selected);
	  }
	  private int getMouseOver(int i, int j) {
	    i -= this.guiLeft;
	    j -= this.guiTop;
	    
	    if (i >= 4 && i < this.xSize - 4 && j >= 4 && j < this.ySize)
	    {
	      for (int j1 = 0; j1 < this.list.size(); ) {
	        
	        if (!mouseInOption(i, j, j1)) {
	          j1++;
	          
	          continue;
	        } 
	        return j1;
	      } 
	    }

	    
	    return -1;
	  }

	  
	  public void mouseClicked(int i, int j, int k) {
	    if (k != 0 || this.hover < 0)
	      return; 
	    if (this.multipleSelection) {
	      if (this.selectedList.contains(this.list.get(this.hover))) {
	        this.selectedList.remove(this.list.get(this.hover));
	      } else {
	        this.selectedList.add(this.list.get(this.hover));
	      } 
	    } else {
	      if (this.hover >= 0)
	        this.selected = this.hover; 
	      this.hover = -1;
	    } 
	    if (this.listener != null) {
	      this.listener.customScrollClicked(i, j, k, this);
	    }
	  }
	  
	  private void drawScrollBar() {
	    int i = this.guiLeft + this.xSize - 9;
	    int j = this.guiTop + (int)(this.scrollY / this.listHeight * (this.ySize - 8)) + 4;
	    int k = j;
	    drawTexturedModalRect(i, k, this.xSize, 9, 5, 1);
	    for (; ++k < j + this.scrollHeight - 1; k++)
	    {
	      drawTexturedModalRect(i, k, this.xSize, 10, 5, 1);
	    }
	    
	    drawTexturedModalRect(i, k, this.xSize, 11, 5, 1);
	  }

	  
	  public boolean hasSelected() { return (this.selected >= 0); }
	  
	  public void setList(List<String> list) {
	    this.isSorted = true;
	    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
	    this.list = list;
	    setSize(this.xSize, this.ySize);
	  }
	  public void setUnsortedList(List<String> list) {
	    this.isSorted = false;
	    this.list = list;
	    setSize(this.xSize, this.ySize);
	  }
	  public void replace(String old, String name) {
	    String select = getSelected();
	    this.list.remove(old);
	    this.list.add(name);
	    if (this.isSorted)
	      Collections.sort(this.list, String.CASE_INSENSITIVE_ORDER); 
	    if (old.equals(select)) {
	      select = name;
	    }
	    this.selected = this.list.indexOf(select);
	    setSize(this.xSize, this.ySize);
	  }
	  
	  public void setSelected(String name) { this.selected = this.list.indexOf(name); }
	  
	  public void clear() {
	    this.list = new ArrayList();
	    this.selected = -1;
	    this.scrollY = 0;
	    setSize(this.xSize, this.ySize);
	  }
	  
	  public List<String> getList() { return this.list; }

	  
	  public HashSet<String> getSelectedList() { return this.selectedList; }

	  
	  public void setSelectedList(HashSet<String> selectedList) { this.selectedList = selectedList; }
	  
	  public GuiCustomScroll setUnselectable() {
	    this.selectable = false;
	    return this;
	  }
	
}
