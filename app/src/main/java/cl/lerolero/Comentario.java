package cl.lerolero;

import android.graphics.drawable.Drawable;

/**
 * Created by Daniel on 29-10-2014.
 */
public class Comentario {
    protected Drawable foto;
    protected String nombre;
    protected String fecha;
    protected String comentario;
    protected String like;
    protected String idc;
    protected int id;

    public Comentario(Drawable foto, String nombre, String fecha, String comentario, String like, String idc){
        super();
        this.foto = foto;
        this.nombre = nombre;
        this.fecha = fecha;
        this.comentario = comentario;
        this.like = like;
        this.idc = idc;
    }
    public Comentario(Drawable foto, String nombre, String fecha, String comentario, String like, String idc, int id){
        super();
        this.foto = foto;
        this.nombre = nombre;
        this.fecha = fecha;
        this.comentario = comentario;
        this.like = like;
        this.idc = idc;
        this.id = id;
    }
    public Drawable getFoto(){
        return foto;
    }
    public void setFoto(Drawable foto){
        this.foto = foto;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getFecha(){
        return fecha;
    }
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    public String getComentario(){
        return comentario;
    }
    public void setComentario(String comentario){
        this.comentario = comentario;
    }
    public String getLike(){
        return like;
    }
    public void setLike(String like){
        this.like = like;
    }
    public String getIdc(){
        return idc;
    }
    public void setIdc(String idc){
        this.idc = idc;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

}
