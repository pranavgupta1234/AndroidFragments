package pranav.amazing.apps.test.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import pranav.amazing.apps.test.POJO.Items;
import pranav.amazing.apps.test.R;

public class FragmentMenu extends Fragment {

    @BindView(R.id.list)
    ListView lview;

    private onItemSelectedListener listener;
    private ArrayAdapter<String> adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof onItemSelectedListener){
            this.listener = (onItemSelectedListener) context;
        }else{
            throw new ClassCastException(context.toString() + "Must implement onItemClicklListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, Items.options);
        // Toast.makeText(getContext(), "onCreate called", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, parent, false);
        ButterKnife.bind(this, v);
        // Toast.makeText(getContext(), "onCreateView called ", Toast.LENGTH_SHORT).show();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lview.setAdapter(adapter);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemSelected(position);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public interface onItemSelectedListener{
        public void onItemSelected(int pos);
    }
}

