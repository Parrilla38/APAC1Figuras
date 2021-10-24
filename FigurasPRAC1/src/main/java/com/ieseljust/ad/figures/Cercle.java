package com.ieseljust.ad.figures;

// Llibreríes per a poder dibuixar 
import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Definim la classe cercle a partir de la classe figura
// Heretarem per tant, la posició i el color
class Cercle extends Figura implements Serializable {

    // Té un nou atribut que serà el radi
    private Integer radi;

    // Constructors
    Cercle() {
        // Sense paràmetres:
        super(); // Invoca al constructor del pare
        this.radi = 0;
    }

    Cercle(int x, int y, int radi, String color) {
        // Amb paràmetres:
        super(x - radi, y - radi, color); // Invoquem al constructor pare
        this.radi = radi; // Indiquem el valor del rado
        // Nota: La posició del cercle serà el seu punt central, per
        // aquest motiu restem el radi a X i a Y
    }

    public void renderText() {
        // Escriu les propietats de la figura
        System.out.println("Cercle en (" + this.posicio.getX() + "," + this.posicio.getY() + ") de radi " + this.radi + " i color " + this.color);
    }

    

    public void render(GraphicsContext gc) {
        // Dibuixem el cercle amb el seu color, la posició i el radi
        Color color = Color.web(this.color);
        gc.setFill(color);

        // Per dibuixar al canvas de JavaFX no hi ha una primitiva per dibuixar cercles,
        // per tant, hem de dibuixar un òval. Aquesta figura espera que li indiquem els diàmetres major i menor, 
        // pel que caldrà multiplicar per 2 el radi.
        gc.fillOval(this.posicio.getX(), this.posicio.getY(), this.radi * 2, this.radi * 2);
    }
    
    public String getAsText()
    {
 
        String almacen = "cercle" + " " + (this.posicio.getX() + this.radi)  + " " +  (this.posicio.getY() + this.radi) + " " +  this.radi + " " + this.color;
        
        return almacen;
        
    }
    
    @Override
    public JSONObject getAsJSON()
    {
 
        JSONObject cerc = new JSONObject();
        
        cerc.put("cx", (this.posicio.getX() + this.radi));
        cerc.put("cy", (this.posicio.getY() + this.radi));
        cerc.put("r", this.radi);
        cerc.put("fill", this.color);
        
        JSONObject cerc2 = new JSONObject();
        cerc2.put("cercle", cerc);
        
        return cerc2;
        
    }
    
    public Element getAsXML(Document doc)
    {
        
        Element cercle = doc.createElement("circle");
        cercle.setAttribute("cx", String.valueOf(this.posicio.getX() + this.radi));
        cercle.setAttribute("cy", String.valueOf(this.posicio.getY() + this.radi));
        cercle.setAttribute("fill", String.valueOf(this.color));
        cercle.setAttribute("r", String.valueOf(this.radi));
        
        return cercle;
        
    }

   
}
