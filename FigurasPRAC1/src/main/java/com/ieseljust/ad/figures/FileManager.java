package com.ieseljust.ad.figures;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class FileManager {

    public FileManager() {

    }


    private boolean validaInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public Boolean Exists(String file) {
        /**
         * **************************************
         * TO-DO: Mètode a implementar: * Retorna si el fitxer existeix o no *
         * ***************************************
         */
        
        File archivo = new File(file);
        

        // Comentar o elimina aquestes línies quan implementeu el mètode
        return archivo.exists();

    }

    public Escena importFromText(String file) {

        Escena escena = null;
        FileReader fr = null;
        try {
            /**
             * *********************************************************
             * TO-DO: Mètode a implementar: * Llegirà el fitxer indicat, en format
             * text, i importarà * la llista de figures. *
             * **********************************************************
             */
            /*
            dimensions 500 500
            rectangle 10 10 480 480 #ccccee
            cercle 250 250 100 #aaaaaa
            */
            
            escena = new Escena();
            File archivo = new File(file);
            fr = new FileReader(archivo);
            BufferedReader bf = new BufferedReader(fr);
            
            String linea = "";
            
            try {
                while(bf.ready())
                {
                    

                    linea = bf.readLine();
                    
                    String elementos[] = linea.split(" ");
                    
                    Figura fig;
                    
                    switch(elementos[0])
                    {
                        
                        case "dimensions":
                            
                            escena.dimensions(Integer.parseInt(elementos[1]),
                                    Integer.parseInt(elementos[2]));
                            break;
                            
                            
                        case "rectangle":
                            
                            fig = new Rectangle(Integer.parseInt(elementos[1]),
                                    Integer.parseInt(elementos[2]),
                                    Integer.parseInt(elementos[3]),
                                    Integer.parseInt(elementos[4]),
                                    elementos[5]);
                            
                            escena.add(fig);
                            
                            break;
                            
                        case "cercle":
                            
                            fig = new Cercle(Integer.parseInt(elementos[1]),
                                    Integer.parseInt(elementos[2]),
                                    Integer.parseInt(elementos[3]),
                                    elementos[4]);
                            
                            escena.add(fig);
                            break;
                            
                        case "linia":
                            
                            fig = new Linia(Integer.parseInt(elementos[1]),
                                    Integer.parseInt(elementos[2]),
                                    Integer.parseInt(elementos[3]),
                                    Integer.parseInt(elementos[4]),
                                    elementos[5]);
                            
                            escena.add(fig);
                            break;
                    }
                    
                    
                    
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return escena;

    }

    public Escena importFromObj(String file) {

        ObjectInputStream ois = null;
        Escena escena = null;
        
        
        try {
            /**
             * **********************************************************************
             * TO-DO: Mètode a implementar: * Llegirà el fitxer indicat, en format
             * d'objectes seriats, i importa * la llista de figures. *
             * **********************************************************************
             */
            // Comentar o elimina aquestes línies quan implementeu el mètode
            
            escena = new Escena();
            File fichero = new File(file);
            
            ois = new ObjectInputStream(new FileInputStream(fichero));
            
            
            int x  = (Integer) ois.readObject();
            int y  = (Integer) ois.readObject();
            escena.dimensions(x,y);
            
            
            while (ois.available() > 0)
            {
                
                Figura fig = (Figura) ois.readObject();
                escena.add(fig);
                

            }
            
            ois.close();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return escena;

    }

    public Boolean exportText(Escena escena, String file) {

        /**
         * ************************************************
         * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer de
         * text, * en format per poder ser importat posteriorment.*
         * ************************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode


        try {
            File archivo = new File(file);
         if (!archivo.exists()) {
                archivo.createNewFile();
         }
         
         
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            
            
            bw.write("dimensions" + " " + escena.getX() + " " +  escena.getY());
            bw.newLine();
            
            for (Figura f: escena.LlistaFigures)
            {
                
               
                bw.write(f.getAsText());
                bw.newLine();
                    
            }
            
            
            
            bw.close();
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
        return true;

    }

    public static Boolean exportObj(Escena escena, String file) {

        /**
         * **********************************************************
         * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer
         * binari d'objectes, * per poder ser importat posteriorment. *
         * **********************************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode
        File archivo = new File(file);
        
        try {
            
            
            FileOutputStream ficheroSalida = new FileOutputStream(archivo, true);
            ObjectOutputStream objetoSalida = new ObjectOutputStream(ficheroSalida);
            
            
            objetoSalida.writeObject(escena.getX());
            objetoSalida.writeObject(escena.getY());
            for (Figura f: escena.LlistaFigures)
            {
                
               
                objetoSalida.writeObject(f);
                    
                
            }
            
            objetoSalida.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }

    public Boolean exportSVG(Escena escena, String file) {
        /**
         * **********************************************************
         * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer
         * SVG (format XML). * El fitxer s'haurà de poder obrir amb Inkscape. *
         * **********************************************************
         */
        /*
            <?xmlversion="1.0"encoding="UTF-8"standalone="no"?> 2 <svgheight="500"width="500">
            <rect fill="#ccccee" height="480" width="480" x="10" y="10"/>
            <circle cx="250" cy="250" fill="#aaaaaa" r="100"/>  
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="450" y1="250" y2="250"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="50" y1="50" y2="
            450"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="450" x2="450" y1="40" y2= "450"/>
            </svg>
         */

        
        // Comentar o elimina aquestes línies quan implementeu el mètode
        
        try {
            File archivo = new File(file);
         if (!archivo.exists()) {
                archivo.createNewFile();
         }
         
         

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            doc.setXmlVersion("1.0");
            Element arrel = doc.createElement("svg");
            arrel.setAttribute("height", String.valueOf(escena.getX()));
            arrel.setAttribute("width", String.valueOf(escena.getY()));
            doc.appendChild(arrel);
            
            for (Figura f: escena.LlistaFigures)
            {
                
                arrel.appendChild(f.getAsXML(doc));
                
            }

            Transformer trans = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(file));
            
            trans.transform(source, result);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
        return true;

    }

    public Boolean exportJSON(Escena escena, String filename) {

        //
         /* **
         * TO-DO: M�tode a implementar: * exporta l'escena donada a un fitxer
         * JSON. * **
         */
        // Comentar o elimina aquestes l�nies quan implementeu el m�tode

        JSONObject escenaJSON = new JSONObject();
        JSONArray jsArray = new JSONArray();

        for (Figura f: escena.LlistaFigures)
        {
            
            jsArray.put(f.getAsJSON());
            
        }
        
        
        escenaJSON.put("width", escena.getX());
        escenaJSON.put("height", escena.getY());
        escenaJSON.put("figures:", jsArray);
        JSONObject raiz = new JSONObject();
        
        raiz.put("escena", escenaJSON);
        
        try (FileWriter file = new FileWriter(filename)) {
                file.write(raiz.toString(4)); // 4 s�n els espais d'indentaci�
            } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        

        return true;

    }

}
