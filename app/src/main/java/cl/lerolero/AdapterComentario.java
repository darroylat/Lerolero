package cl.lerolero;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

/**
 * Created by Daniel on 29-10-2014.
 */
public class AdapterComentario extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Comentario> items;

    public AdapterComentario(Activity activity, ArrayList<Comentario> items){
        this.activity = activity;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_listview, null);
        }
        Comentario dir = items.get(position);

        ImageView foto = (ImageView)v.findViewById(R.id.imagen_usuario);
        foto.setImageDrawable(dir.getFoto());

        TextView nombre = (TextView)v.findViewById(R.id.nombre_usuario);
        nombre.setText(dir.getNombre());

        TextView fecha = (TextView)v.findViewById(R.id.fecha_usuario);
        fecha.setText(dir.getFecha());

        TextView comentario = (TextView)v.findViewById(R.id.comentario_usuario);
        comentario.setText(dir.getComentario());

        TextView like = (TextView)v.findViewById(R.id.like_usuario);
        like.setText(dir.getLike());

        return v;
    }
}
