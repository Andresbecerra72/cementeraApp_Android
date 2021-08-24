package app.absoftware.cementera.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.absoftware.cementera.R;
import app.absoftware.cementera.models.Informe;

public class InformeAdapter extends  RecyclerView.Adapter<InformeAdapter.ViewHolder>{

    // variables
    private List<Informe> informeList;
    private Context context;

    public InformeAdapter(List<Informe> informeList, Context context) {
        this.informeList = informeList;
        this.context = context;
    }

    // metodos de la extencion del  RecyclerView.Adapter<>

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // se crea la vista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fuentes, parent, false);

        return new ViewHolder(view);
    }

    // le pone datos a los objetos
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewItem_Title.setText(informeList.get(position).getLabel());
        holder.tv_item_riesgo.setText(informeList.get(position).getCalculo_2());

        holder.tv_item_riesgo.setTextColor(0xffFFFFFF); // color del texto Blanco

        if (informeList.get(position).getCalculo_2().equals("Muy Alto"))
            holder.tv_item_riesgo.setBackgroundColor(0xffDF4646);
        if (informeList.get(position).getCalculo_2().equals("Alto"))
            holder.tv_item_riesgo.setBackgroundColor(0xffECA203);
        if (informeList.get(position).getCalculo_2().equals("Moderado"))
            holder.tv_item_riesgo.setBackgroundColor(0xffDEC422);
        if (informeList.get(position).getCalculo_2().equals("Bajo"))
            holder.tv_item_riesgo.setBackgroundColor(0xff1EA422);

        holder.tv_item_exp_material_particulado.setText(informeList.get(position).getExp_material_particulado()); // calculo1 y calculo3
        holder.tv_item_control_polvo.setText(informeList.get(position).getControl_polvo_fugitivo()); // Control recomendado de control de polvo fugitivo
        holder.tv_resul_0.setText(informeList.get(position).getCalculo_2_num());
        holder.tv_level_0.setText(informeList.get(position).getCalculo_2());
        holder.tv_level_0.setVisibility(View.GONE);
        holder.tv_resul_1.setText(informeList.get(position).getCalculo_3_num());
        holder.tv_level_1.setText(informeList.get(position).getCalculo_3());
        holder.tv_level_1.setVisibility(View.GONE);
        holder.tv_resul_2.setText(informeList.get(position).getCalculo_5_num()); // Puntaje de riesgo del derrame
        holder.tv_level_2.setText(informeList.get(position).getCalculo_5()); // Puntaje de riesgo del derrame
        holder.tv_level_2.setVisibility(View.GONE);

        String controles = informeList.get(position).getControles_num() + "%";
        holder.tv_item_opciones_control.setText(controles); //sumatoria en % controles existentes

        if (informeList.get(position).isQ10_chk_1().equals("false"))
            holder.tv_opc_0.setVisibility(View.GONE);
        if (informeList.get(position).isQ10_chk_2().equals("false"))
            holder.tv_opc_1.setVisibility(View.GONE);
        if (informeList.get(position).isQ10_chk_3().equals("false"))
            holder.tv_opc_2.setVisibility(View.GONE);
        if (informeList.get(position).isQ10_chk_4().equals("false"))
            holder.tv_opc_3.setVisibility(View.GONE);
        if (informeList.get(position).isQ10_chk_5().equals("false"))
            holder.tv_opc_4.setVisibility(View.GONE);
        if (informeList.get(position).isQ10_chk_6().equals("false"))
            holder.tv_opc_5.setVisibility(View.GONE);
        if (informeList.get(position).isQ10_chk_7().equals("false"))
            holder.tv_opc_6.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return informeList.size();
    }

    // clase para enlazar los componenetes del Card_informe
    public class  ViewHolder extends RecyclerView.ViewHolder{

        // objetos
        private ImageView icon_0, icon_1, icon_2;
        private TextView textViewItem_Title, tv_item_riesgo, tv_item_exp_material_particulado,  tv_item_control_polvo;
        private TextView tv_resul_0, tv_level_0, tv_resul_1, tv_level_1, tv_resul_2, tv_level_2;
        private TextView tv_item_opciones_control, tv_opc_0, tv_opc_1, tv_opc_2, tv_opc_3, tv_opc_4, tv_opc_5, tv_opc_6;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            // relacion con Objetos del Card_fuente
            icon_0 = itemView.findViewById(R.id.icon_0);
            icon_1 = itemView.findViewById(R.id.icon_1);
            icon_2 = itemView.findViewById(R.id.icon_2);

            textViewItem_Title = itemView.findViewById(R.id.textViewItem_Title);
            tv_item_riesgo = itemView.findViewById(R.id.tv_item_riesgo);
            tv_item_exp_material_particulado = itemView.findViewById(R.id.tv_item_exp_material_particulado);
            tv_item_control_polvo = itemView.findViewById(R.id.tv_item_control_polvo);
            tv_resul_0 = itemView.findViewById(R.id.tv_resul_0);
            tv_level_0 = itemView.findViewById(R.id.tv_level_0);
            tv_resul_1 = itemView.findViewById(R.id.tv_resul_1);
            tv_level_1 = itemView.findViewById(R.id.tv_level_1);
            tv_resul_2 = itemView.findViewById(R.id.tv_resul_2);
            tv_level_2 = itemView.findViewById(R.id.tv_level_2);

            tv_item_opciones_control = itemView.findViewById(R.id.tv_item_opciones_control);
            tv_opc_0 = itemView.findViewById(R.id.tv_opc_0);
            tv_opc_1 = itemView.findViewById(R.id.tv_opc_1);
            tv_opc_2 = itemView.findViewById(R.id.tv_opc_2);
            tv_opc_3 = itemView.findViewById(R.id.tv_opc_3);
            tv_opc_4 = itemView.findViewById(R.id.tv_opc_4);
            tv_opc_5 = itemView.findViewById(R.id.tv_opc_5);
            tv_opc_6 = itemView.findViewById(R.id.tv_opc_6);


        }
    }



} // END class
