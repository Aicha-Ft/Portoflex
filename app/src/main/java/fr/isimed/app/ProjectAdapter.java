package fr.isimed.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private Context context;
    private List<Project> projectList;

    // Constructeur qui accepte une liste de Project
    public ProjectAdapter(Context context, List<Project> projectList) {
        this.context = context;
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_item, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projectList.get(position);
        holder.txtProjectName.setText(project.getName());
        holder.txtProjectDescription.setText(project.getDescription());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    // Classe ViewHolder pour lier les données à la vue
    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView txtProjectName, txtProjectDescription;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProjectName = itemView.findViewById(R.id.txtProjectName);
            txtProjectDescription = itemView.findViewById(R.id.txtProjectDescription);
        }
    }
    public interface OnItemClickListener {
        void onEditClick(Portfolio portfolio);
        void onDeleteClick(Portfolio portfolio);
        void onItemClick(Portfolio portfolio);
    }
}
