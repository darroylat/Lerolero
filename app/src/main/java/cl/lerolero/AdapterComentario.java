package cl.lerolero;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cl.lerolero.libreria.UserFunctions;

/**
 * Created by Daniel on 29-10-2014.
 */
public class AdapterComentario extends BaseAdapter {

    Comentario comentario;
    UserFunctions userFunctions;
    ComentariosActivity comActivity;

    protected Activity activity;
    protected ArrayList<Comentario> items;
    private Context context;

    public AdapterComentario(Activity activity, ArrayList<Comentario> items){
        this.activity = activity;
        this.items = items;
    }
    public AdapterComentario(Context c){
        this.context = c;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;



        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_listview, null);
        }
        final Comentario dataModel = items.get(position);

        final ImageButton btnlike = (ImageButton)v.findViewById(R.id.btnLike);
        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //btnlike.setText(dataModel.getIdc());

                //JSONObject json = userFunctions.setLike(dataModel.getIdc(),userFunctions.getUserLoggedIn(activity.getApplication()));
                Toast.makeText(activity,"Your Message "+ dataModel.getIdc(), Toast.LENGTH_SHORT).show();

            }


        });

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
        TextView idc = (TextView)v.findViewById(R.id.id_comentario);
        idc.setText(dir.getIdc());

        return v;


    }
}
