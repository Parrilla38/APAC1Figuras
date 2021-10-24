package com.ieseljust.ad.figures;

// Llibreríes per a poder dibuixar 
import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Definim la classe Línia a partir de la classe figura
// Heretarem per tant, la posició i el color
class Linia extends Figura {

    // Té un nou atribut que serà el radi
    private Punt vector;

    // Constructors
    Linia() {
        // Sense paràmetres:
        super(); // Invoca al constructor del pare
        this.vector = new Punt(0, 0);
    }

    Linia(int x, int y, int x2, int y2, String color) {
        // Amb paràmetres:
        super(x, y, color); // Invoquem al constructor pare
        this.vector = new Punt(x2, y2); // Indiquem el vector de la línia
    }

    public void renderText() {
        // Escriu les propietats de la figura
        System.out.println("Linia de (" + this.posicio.getX() + "," + this.posicio.getY() + ") fins a (" + this.vector.getX() + ", " + this.vector.getY() + ") i color " + this.color);
    }

    ;

    public void render(GraphicsContext gc) {
        // Dibuixem la linia amb el seu color 
        Color color = Color.web(this.color);
        gc.setLineWidth(3); // Grossor per defecte de la línia: 3
        gc.setStroke(color);
        gc.strokeLine(this.posicio.getX(), this.posicio.getY(), this.vector.getX(), this.vector.getY());

    }
    
    public String getAsText()
    {
 
        String almacen = "linia" + " " + this.posicio.getX() + " " +  this.posicio.getY() + " " + this.vector.getX() + " " + this.vector.getY() + " " + this.color;
        
        return almacen;
        
    }
    
    
    @Override
    public JSONObject getAsJSON()
    {
 
        JSONObject linia = new JSONObject();
        
        linia.put("x", this.posicio.getX());
        linia.put("y", this.posicio.getY());
        linia.put("x2", this.vector.getX());
        linia.put("y2", this.vector.getY());
        linia.put("stroke", this.color);
        linia.put("stroke-width", 3);
        
        JSONObject linia2 = new JSONObject();
        linia2.put("linia", linia);
        
        return linia2;
        
    }
    
    public Element getAsXML(Document doc)
    {
        
        Element linia = doc.createElement("line");
        linia.setAttribute("x", String.valueOf(this.posicio.getX()));
        linia.setAttribute("y", String.valueOf(this.posicio.getY()));
        linia.setAttribute("x2", String.valueOf(this.vector.getX()));
        linia.setAttribute("y2", String.valueOf(this.vector.getY()));
        linia.setAttribute("stroke", String.valueOf(this.color));
        linia.setAttribute("stroke-width", String.valueOf(3));
        
        return linia;
        
    }

}
