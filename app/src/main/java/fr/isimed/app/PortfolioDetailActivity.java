package fr.isimed.app;


import fr.isimed.app.ProjectManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import fr.isimed.app.ProjectDetailActivity;


import androidx.appcompat.app.AppCompatActivity;
import fr.isimed.app.PortfolioManager;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PortfolioDetailActivity extends AppCompatActivity {

    private TextView txtPortfolioTitle;
    private ListView listViewProjects;
    private RecyclerView recyclerViewProjects;
    private Button btnAddProject, btnDeletePortfolio;
    private ProjectManager projectManager;
    private PortfolioManager portfolioManager;
    private List<Project> projectList;
    private ProjectAdapter projectAdapter;
    private String portfolioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_detail);

        txtPortfolioTitle= findViewById(R.id.editProjectTitle);
        recyclerViewProjects = findViewById(R.id.recyclerViewProjects);
        btnAddProject = findViewById(R.id.btnAddProject);
        btnDeletePortfolio = findViewById(R.id.btnDeletePortfolio);

        portfolioId = getIntent().getStringExtra("portfolioId");
        String portfolioName = getIntent().getStringExtra("portfolioName");
        txtPortfolioTitle.setText(portfolioName);

        projectManager = new ProjectManager();
        portfolioManager = new PortfolioManager();
        projectList = new ArrayList<>();

        btnAddProject.setOnClickListener(view -> openAddProjectActivity());
        btnDeletePortfolio.setOnClickListener(view -> deletePortfolio());

        loadProjects();
    }

    private void loadProjects() {
        projectManager.getProjectsByPortfolio(portfolioId, task -> handleProjectResult(task));
    }

    private void handleProjectResult(Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            projectList.clear();
            task.getResult().forEach(documentSnapshot -> {
                Project project = documentSnapshot.toObject(Project.class);
                project.setId(documentSnapshot.getId());
                projectList.add(project);
            });

            projectAdapter = new ProjectAdapter(this, projectList);
            recyclerViewProjects.setAdapter(projectAdapter);

            listViewProjects.setOnItemClickListener((parent, view, position, id) -> {
                Project selectedProject = projectList.get(position);
                openProjectDetailActivity(selectedProject);
            });
        } else {
            Toast.makeText(this, "Erreur lors du chargement des projets.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openAddProjectActivity() {
        Intent intent = new Intent(this, ProjectDetailActivity.class);
        intent.putExtra("portfolioId", portfolioId);
        startActivity(intent);
    }

    private void openProjectDetailActivity(Project project) {
        Intent intent = new Intent(this, ProjectDetailActivity.class);
        intent.putExtra("projectId", project.getId());
        intent.putExtra("portfolioId", portfolioId);
        startActivity(intent);
    }

    private void deletePortfolio() {
        portfolioManager.deletePortfolio(portfolioId, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Portfolio supprimé avec succès.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erreur lors de la suppression.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
