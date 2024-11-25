package fr.isimed.app;


import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import java.util.Map;

public class ProjectManager {

    private final FirebaseFirestore firestore;
    private final CollectionReference projectsCollection;
    public ProjectManager() {
        firestore = FirebaseFirestore.getInstance();
        projectsCollection = firestore.collection("projects");
    }

    // Ajouter un projet à un portfolio
    public void addProject(Project project, OnCompleteListener<DocumentReference> onCompleteListener) {
        // Ajoute un projet à la collection et attache le listener correct
        projectsCollection.add(project).addOnCompleteListener(onCompleteListener);
    }


    // Récupérer les projets d'un portfolio spécifique
    public void getProjectsByPortfolio(String portfolioId, OnCompleteListener<QuerySnapshot> onCompleteListener) {
        projectsCollection.whereEqualTo("portfolioId", portfolioId)
                .get()
                .addOnCompleteListener(onCompleteListener);
    }

    // Mettre à jour un projet
    public void updateProject(String projectId, Map<String, Object> updatedData, OnCompleteListener<Void> onCompleteListener) {
        projectsCollection.document(projectId)
                .update(updatedData)
                .addOnCompleteListener(onCompleteListener);
    }

    // Supprimer un projet
    public void deleteProject(String projectId, OnCompleteListener<Void> onCompleteListener) {
        projectsCollection.document(projectId)
                .delete()
                .addOnCompleteListener(onCompleteListener);
    }
}
