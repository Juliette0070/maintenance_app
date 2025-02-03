/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Administrateur
 */
public abstract class Objet {
    
    protected int posX;
    protected int posY;
    protected Label label;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Label getLabel() {
        return label;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
        
    public boolean toucheObjet(Objet obj)
    {
        if(obj.label==null) return false;
        else
        {
            if (obj.label.getjLabel()==null) return false;
            else
            {
                int l_obj = obj.label.getjLabel().getWidth();
                int h_obj = obj.label.getjLabel().getHeight();
                int l_this = this.label.getjLabel().getWidth();
                int h_this = this.label.getjLabel().getHeight();
                
                return (!((this.posX+l_this<obj.posX || this.posX>obj.posX+l_obj) || (this.posY+h_this<obj.posY || this.posY>obj.posY+h_obj)));
                
            }
        }
    }
}
