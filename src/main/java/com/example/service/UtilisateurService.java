package com.example.service;

// Import de la classe Utilisateur (entité JPA)
import com.example.model.Utilisateur;

// Imports JPA nécessaires pour gérer la persistance
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service métier pour l'entité Utilisateur.
 * Hérite des opérations CRUD génériques depuis AbstractCrudService.
 */
public class UtilisateurService extends AbstractCrudService<Utilisateur, Long> {

    /**
     * Constructeur du service.
     * @param emf EntityManagerFactory utilisée pour créer des EntityManager
     */
    public UtilisateurService(EntityManagerFactory emf) {
        super(emf); // Appel du constructeur de la classe parente
    }

    /**
     * Recherche un utilisateur par son email.
     * @param email l'email de l'utilisateur à rechercher
     * @return Optional contenant l'utilisateur s'il existe, sinon Optional.empty()
     */
    public Optional<Utilisateur> findByEmail(String email) {

        // Création d'un EntityManager pour interagir avec la base de données
        EntityManager em = emf.createEntityManager();

        try {
            // Création d'une requête JPQL pour chercher l'utilisateur par email
            TypedQuery<Utilisateur> query = em.createQuery(
                    "SELECT u FROM Utilisateur u WHERE u.email = :email",
                    Utilisateur.class
            );

            // Passage du paramètre email à la requête
            query.setParameter("email", email);

            // Exécution de la requête et récupération du résultat sous forme de liste
            List<Utilisateur> results = query.getResultList();

            // Si aucun résultat, on retourne Optional.empty()
            // Sinon, on retourne le premier utilisateur trouvé
            return results.isEmpty()
                    ? Optional.empty()
                    : Optional.of(results.get(0));

        } finally {
            // Fermeture de l'EntityManager (IMPORTANT pour éviter les fuites de ressources)
            em.close();
        }
    }
}