package com.example.service;

// Import de l'entité Salle
import com.example.model.Salle;

// Imports JPA pour la gestion de la persistance
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * Service métier pour l'entité Salle.
 * Hérite des opérations CRUD génériques depuis AbstractCrudService.
 */
public class SalleService extends AbstractCrudService<Salle, Long> {

    /**
     * Constructeur du service Salle.
     * @param emf EntityManagerFactory utilisée pour créer les EntityManager
     */
    public SalleService(EntityManagerFactory emf) {
        super(emf); // Appel du constructeur de la classe parente
    }

    /**
     * Recherche les salles selon leur disponibilité.
     * @param disponible true pour les salles disponibles, false sinon
     * @return liste des salles correspondant au critère
     */
    public List<Salle> findByDisponible(boolean disponible) {

        // Création d'un EntityManager pour accéder à la base de données
        EntityManager em = emf.createEntityManager();

        try {
            // Création d'une requête JPQL filtrant sur la disponibilité
            TypedQuery<Salle> query = em.createQuery(
                    "SELECT s FROM Salle s WHERE s.disponible = :disponible",
                    Salle.class
            );

            // Passage du paramètre disponible à la requête
            query.setParameter("disponible", disponible);

            // Exécution de la requête et retour de la liste des salles trouvées
            return query.getResultList();

        } finally {
            // Fermeture de l'EntityManager pour libérer les ressources
            em.close();
        }
    }

    /**
     * Recherche les salles ayant une capacité minimale donnée.
     * @param capaciteMin capacité minimale souhaitée
     * @return liste des salles dont la capacité est >= capaciteMin
     */
    public List<Salle> findByCapaciteMinimum(int capaciteMin) {

        // Création d'un EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            // Requête JPQL pour récupérer les salles avec une capacité suffisante
            TypedQuery<Salle> query = em.createQuery(
                    "SELECT s FROM Salle s WHERE s.capacite >= :capaciteMin",
                    Salle.class
            );

            // Passage du paramètre capaciteMin à la requête
            query.setParameter("capaciteMin", capaciteMin);

            // Exécution de la requête et retour du résultat
            return query.getResultList();

        } finally {
            // Fermeture de l'EntityManager (bonne pratique obligatoire)
            em.close();
        }
    }
}