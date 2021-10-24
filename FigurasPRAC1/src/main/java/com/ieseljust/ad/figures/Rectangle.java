package com.ieseljust.ad.figures;

// Llibreríes per a poder dibuixar 
import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Definim la classe rectangle a partir de la classe figura
// Heretarem per tant, la posició i el color
class Rectangle extends Figura  {

    // Té un nou atribut que serà el radi
    private Integer llarg;
    private Integer alt;

    // Constructors
    Rectangle() {
        // Sense paràmetres:
        super(); // Invoca al constructor del pare
        this.llarg = 0;
        this.llarg = 0;
    }

    Rectangle(int x, int y, int llarg, int alt, String color) {
        // Amb paràmetres:
        super(x, y, color); // Invoquem al constructor pare
        this.llarg = llarg; // Indiquem el valor de la llargària
        this.alt = alt; // Indiquem el valor de l'altura
    }

    public void renderText() {
        // Escriu les propietats de la figura
        System.out.println("Rectangle en (" + this.posicio.getX() + "," + this.posicio.getY() + ") de llarg " + this.llarg + ", altura " + this.alt + " i color " + this.color);
    }

    ;

    public void render(GraphicsContext gc) {
        // Dibuixem el rectangle amb el seu color, la posició i les dimensions
        Color color = Color.web(this.color);
        gc.setFill(color);

        gc.fillRect(this.posicio.getX(), this.posicio.getY(), this.llarg, this.alt);
        //gc.fillOval(this.posicio.getX(), this.posicio.getY(), this.radi*2, this.radi*2);
    }

    
    public String getAsText()
    {
 
        String almacen = "rectangle" + " " + this.posicio.getX() + " " +  this.posicio.getY() + " " + this.llarg + " " + this.alt + " " + this.color;
        
        return almacen;
        
    }
    
    @Override
    public JSONObject getAsJSON()
    {
 
        JSONObject rect = new JSONObject();
        
        rect.put("x", this.posicio.getX());
        rect.put("y", this.posicio.getY());
        rect.put("llarg", this.llarg);
        rect.put("alt", this.alt);
        rect.put("fill", this.color);
        
        JSONObject rect2 = new JSONObject();
        rect2.put("rectangle", rect);
        
        return rect2;
        
    }
    
    
    public Element getAsXML(Document doc)
    {
        
        Element rect = doc.createElement("rect");
        rect.setAttribute("x", String.valueOf(this.posicio.getX()));
        rect.setAttribute("y", String.valueOf(this.posicio.getY()));
        rect.setAttribute("llarg", String.valueOf(this.llarg));
        rect.setAttribute("alt", String.valueOf(this.alt));
        rect.setAttribute("fill", String.valueOf(this.color));
        
        return rect;
        
    }
}
